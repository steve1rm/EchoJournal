package me.androidbox.echojournal.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.icerock.moko.permissions.DeniedAlwaysException
import dev.icerock.moko.permissions.DeniedException
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionState
import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.RequestCanceledException
import dev.theolm.record.Record
import kotlinx.collections.immutable.persistentMapOf
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char
import kotlinx.datetime.minus
import kotlinx.datetime.toLocalDateTime
import me.androidbox.echojournal.data.Journal
import me.androidbox.echojournal.domain.CreateJournalUseCase
import me.androidbox.echojournal.domain.CreateTopicUseCase
import me.androidbox.echojournal.domain.FetchEchoJournalsUseCase
import me.androidbox.echojournal.domain.FetchTopicsUseCase
import me.androidbox.echojournal.domain.FetchTopicsWithPrefixUseCase
import me.androidbox.echojournal.presentation.models.EchoJournalUI
import me.androidbox.echojournal.presentation.models.EmotionMoodsFilled
import me.androidbox.echojournal.presentation.models.SelectableEmotion
import me.androidbox.echojournal.presentation.models.SelectableTopic
import me.androidbox.echojournal.presentation.models.emotionList
import me.androidbox.echojournal.presentation.timeAndEmit

class EchoJournalViewModel(
    private val fetchEchoJournalsUseCase: FetchEchoJournalsUseCase,
    private val createJournalUseCase: CreateJournalUseCase,
    private val fetchTopicsUseCase: FetchTopicsUseCase,
    private val fetchTopicsWithPrefixUseCase: FetchTopicsWithPrefixUseCase,
    private val createTopicUseCase: CreateTopicUseCase,
    val permissionsController: PermissionsController
) : ViewModel() {

    private var hasFetched = false
    private var timerJob: Job? = null

    private var _echoEchoJournalState = MutableStateFlow<EchoJournalState>(EchoJournalState())
    val echoJournalState = _echoEchoJournalState.asStateFlow()
        .onStart {
            if (!hasFetched) {
                fetchEchoJournalEntries()
                fetchTopics()
                populateEmotions()
                hasFetched = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = EchoJournalState()
        )

    /** Just testing this using a snapshowflow
    val timerFlow = snapshotFlow {
    echoJournalState.value.isRecording
    }.stateIn(
    scope = viewModelScope,
    started = SharingStarted.Lazily,
    initialValue = false
    )*/

    private val timerFlow = echoJournalState
        .map {
            it.isRecording && !it.isPaused
        }
        .distinctUntilChanged()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = false
        )

    fun startTimer(isRecording: Boolean) {
        timerJob?.cancel()

        if (isRecording) {
            timerJob = viewModelScope.launch {
                val initialDuration = echoJournalState.value.pausedDuration

                println("timer on each $isRecording")
                timeAndEmit(1f)
                    .collect { elapsed ->
                        val duration = initialDuration + (elapsed - (elapsed % 1_000))

                        println("duration $duration")
                        _echoEchoJournalState.update { echoJournalState ->
                            echoJournalState.copy(
                                duration = duration
                            )
                        }
                    }
            }
        }
    }

    init {
        viewModelScope.launch {
            _echoEchoJournalState.update { echoJournalState ->
                echoJournalState.copy(
                    permissionState = permissionsController.getPermissionState(Permission.RECORD_AUDIO)
                )
            }
        }

        timerFlow
            .onEach { canStartRecording ->
                println("timerflow $canStartRecording")
                startTimer(canStartRecording)
            }.launchIn(viewModelScope)
    }

    fun startRecording() {
        if (!echoJournalState.value.isPaused) {
            if (Record.isRecording()) {
                val filePath = Record.stopRecording()
                println("Stop recording $filePath")

                _echoEchoJournalState.update { echoJournalState ->
                    echoJournalState.copy(
                        isRecording = false,
                        isPaused = false,
                        audioFile = filePath
                    )
                }
            } else {
                println("Starting recording")
                Record.startRecording()

                _echoEchoJournalState.update { echoJournalState ->
                    echoJournalState.copy(isRecording = true)
                }
            }
        } else {
            println("resume recording")
            _echoEchoJournalState.update { echoJournalState ->
                echoJournalState.copy(
                    isPaused = false
                )
            }
        }
    }

    fun pauseResumeRecording() {
        println("pause recording")
        /** Not implemented */
        _echoEchoJournalState.update { echoJournalState ->
            echoJournalState.copy(
                isPaused = true,
                pausedDuration = echoJournalState.duration
            )
        }
    }

    fun cancelRecording() {
        /** Discard recording and close button sheet */
        println("cancel recording")
        if (Record.isRecording()) {
            Record.stopRecording()
        }

        _echoEchoJournalState.update { echoJournalState ->
            echoJournalState.copy(
                isPaused = false,
                isRecording = false,
                audioFile = "",
                duration = 0L,
                pausedDuration = 0L)
        }
        timerJob?.cancel()
    }

    fun openAppSettings() {
        permissionsController.openAppSettings()
    }

    fun provideOrRequestRecordAudioPermission() {
        viewModelScope.launch {
            try {
                permissionsController.providePermission(Permission.RECORD_AUDIO)
                _echoEchoJournalState.update { echoJournalState ->
                    echoJournalState.copy(
                        permissionState = PermissionState.Granted
                    )
                }
            } catch (exception: DeniedAlwaysException) {
                _echoEchoJournalState.update { echoJournalState ->
                    echoJournalState.copy(
                        permissionState = PermissionState.DeniedAlways
                    )
                }
            } catch (exception: DeniedException) {
                _echoEchoJournalState.update { echoJournalState ->
                    echoJournalState.copy(
                        permissionState = PermissionState.Denied
                    )
                }
            } catch (exception: RequestCanceledException) {
                exception.printStackTrace()
            }
        }
    }

    fun populateEmotions() {
        _echoEchoJournalState.update { echoJournalState ->
            echoJournalState.copy(emotionList = emotionList)
        }
    }

    fun updateTopicSelection(selectableTopic: SelectableTopic, index: Int) {
        val listOfTopic = echoJournalState.value.listOfTopic

        val listOfUpdatedTopics = listOfTopic.mapIndexed { currentIndex, topic ->
            if (currentIndex == index) {
                selectableTopic
            } else {
                topic
            }
        }

        _echoEchoJournalState.update { echoJournalState ->
            echoJournalState.copy(
                listOfTopic = listOfUpdatedTopics
            )
        }
    }

    fun updateEmotionSelection(selectableEmotion: SelectableEmotion, index: Int) {
        val listOfEmotions = echoJournalState.value.emotionList

        val updatedEmotion = listOfEmotions.mapIndexed { currentIndex, emotion ->
            if (currentIndex == index) {
                selectableEmotion
            } else {
                emotion
            }
        }

        _echoEchoJournalState.update { echoJournalState ->
            echoJournalState.copy(
                emotionList = updatedEmotion
            )
        }

        fetchEchoJournalEntries()
    }

    fun clearAllTopics() {
        val listOfTopic = echoJournalState.value.listOfTopic

        val listOfUpdatedTopics = listOfTopic.map { topic ->
            topic.copy(isSelected = false)
        }

        _echoEchoJournalState.update { echoJournalState ->
            echoJournalState.copy(
                listOfTopic = listOfUpdatedTopics
            )
        }
    }

    fun clearAllEmotions() {
        val listOfEmotions = echoJournalState.value.emotionList

        val listOfUpdatedEmotions = listOfEmotions.map { emotion ->
            emotion.copy(isSelected = false)
        }

        _echoEchoJournalState.update { echoJournalState ->
            echoJournalState.copy(
                emotionList = listOfUpdatedEmotions
            )
        }

        fetchEchoJournalEntries()
    }

    /** TODO Should be fetched from the local cache */
    fun fetchTopics() {
        viewModelScope.launch {
            try {
                val result = fetchTopicsUseCase.execute()
                result.onSuccess { topics ->
                    _echoEchoJournalState.update { echoJournalState ->
                        echoJournalState.copy(
                            listOfTopic = topics
                        )
                    }
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }

    fun fetchFilteredTopics(prefix: String) {
        viewModelScope.launch {
            try {
                val result = fetchTopicsWithPrefixUseCase.execute(prefix)
                result.onSuccess { topics ->
                    _echoEchoJournalState.update { echoJournalState ->
                        echoJournalState.copy(
                            listOfTopicWithPrefix = topics
                        )
                    }
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }

    fun createTopic(topic: String) {
        viewModelScope.launch {
            try {
                val result = createTopicUseCase.execute(topic)
                result.onSuccess { topics ->
                    // TODO HANDLE SUCCESS CASE
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }

    fun resetNewEntryState() {
        _echoEchoJournalState.update { echoJournalState ->
            echoJournalState.copy(
                isRecording = false,
                isPaused = false,
                pausedDuration = 0L,
                audioFile = "",
                duration = 0L
            )
        }
    }

    fun createJournal(journal: EchoJournalUI) {
        _echoEchoJournalState.update { echoJournalState ->
            echoJournalState.copy(
                createJournalState = CreateJournalState.Empty
            )
        }
        viewModelScope.launch {
            try {
                val result = createJournalUseCase.execute(
                    Journal(
                        title = journal.title,
                        audioFilePath = journal.audioFilePath,
                        topics = journal.topics,
                        description = journal.description,
                        emotion = journal.emotion.name,
                        createdAt = journal.date,
                        audioDuration = journal.audioDuration
                    )
                )
                result.onSuccess {
                    _echoEchoJournalState.update { echoJournalState ->
                        echoJournalState.copy(
                            isRecording = false,
                            isPaused = false,
                            pausedDuration = 0L,
                            audioFile = "",
                            duration = 0L,
                            createJournalState = CreateJournalState.Success
                        )
                    }
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }

    fun fetchEchoJournalEntries() {
        viewModelScope.launch {
            try {
                fetchEchoJournalsUseCase.execute().collect { echoJournal ->
                    println("FETCH JOURNAL ENTRIES")
                    val today =
                        Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date

                    val sortByDates = echoJournal.sortedByDescending { it.date }.filter { item ->
                        if (echoJournalState.value.emotionList.find { it.isSelected } == null) true
                        else echoJournalState.value.emotionList.find { (it.emotion as EmotionMoodsFilled) == item.emotion }?.isSelected == true
                    }

                    val groupedJournals = sortByDates.groupBy { journal ->
                        val journalDate = Instant.fromEpochMilliseconds(journal.date)
                            .toLocalDateTime(TimeZone.currentSystemDefault()).date

                        when (journalDate) {
                            today -> "Today"
                            today.minus(1, DateTimeUnit.DAY) -> "Yesterday"
                            else -> {
                                journalDate.format(
                                    LocalDate.Format {
                                        dayOfWeek(DayOfWeekNames.ENGLISH_FULL)
                                        chars(", ")
                                        monthName(MonthNames.ENGLISH_ABBREVIATED)
                                        char(' ')
                                        dayOfMonth()
                                    }
                                )
                            }
                        }
                    }

                    _echoEchoJournalState.update { echoJournalState ->
                        echoJournalState.copy(
                            listOfJournals = groupedJournals,
                            createJournalState = CreateJournalState.Empty
                        )
                    }
                }
            } catch (exception: Exception) {
                _echoEchoJournalState.update { echoJournalState ->
                    echoJournalState.copy(
                        listOfJournals = persistentMapOf(),
                        createJournalState = CreateJournalState.Empty
                    )
                }
                exception.printStackTrace()
            }
        }
    }

    override fun onCleared() {
        timerJob?.cancel()
        println("onCleared")
        super.onCleared()
    }
}