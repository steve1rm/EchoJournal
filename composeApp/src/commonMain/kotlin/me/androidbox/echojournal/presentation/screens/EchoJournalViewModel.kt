package me.androidbox.echojournal.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.collections.immutable.persistentMapOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
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
import me.androidbox.echojournal.domain.FetchEchoJournalsUseCase
import me.androidbox.echojournal.presentation.models.SelectableTopic
import me.androidbox.echojournal.presentation.models.emotionList

class EchoJournalViewModel(
    private val fetchEchoJournalsUseCase: FetchEchoJournalsUseCase
) : ViewModel() {

    private var hasFetched = false

    private var _echoEchoJournalState = MutableStateFlow<EchoJournalState>(EchoJournalState())
    val echoJournalState = _echoEchoJournalState.asStateFlow()
        .onStart {
            if(!hasFetched) {
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

    fun populateEmotions() {
        _echoEchoJournalState.update { echoJournalState ->
            echoJournalState.copy(emotionList = emotionList)
        }
    }

    fun updateTopicSelection(selectableTopic: SelectableTopic, index: Int) {
        val listOfTopic = echoJournalState.value.listOfTopic

        val listOfUpdatedTopics = listOfTopic.mapIndexed { currentIndex, topic ->
            if(currentIndex == index) {
                selectableTopic
            }
            else {
                topic
            }
        }

        _echoEchoJournalState.update { echoJournalState ->
            echoJournalState.copy(
                listOfTopic = listOfUpdatedTopics
            )
        }
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

    fun fetchTopics() {
        viewModelScope.launch {
            val topics = listOf(
                SelectableTopic("Work", false),
                SelectableTopic("Life", false),
                SelectableTopic("Relocation", false),
                SelectableTopic("Rest", false),
                SelectableTopic("Travel", false),
                SelectableTopic("Flight Tickets", false)
            )

            _echoEchoJournalState.update { echoJournalState ->
                echoJournalState.copy(
                    listOfTopic = topics
                )
            }
        }
    }

    fun fetchEchoJournalEntries() {
        try {
            viewModelScope.launch {
                val result = fetchEchoJournalsUseCase.execute()
                result.onSuccess { echoJournal ->
                    val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date

                    val groupedJournals = echoJournal.groupBy { journal ->

                        val journalDate = Instant.fromEpochMilliseconds(journal.date).toLocalDateTime(TimeZone.currentSystemDefault()).date

                        when(journalDate) {
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
                            listOfJournals = groupedJournals
                        )
                    }
                }.onFailure {
                    _echoEchoJournalState.update { echoJournalState ->
                        echoJournalState.copy(
                            listOfJournals = persistentMapOf()
                        )
                    }
                }
            }
        }
        catch(exception: Exception) {
            exception.printStackTrace()
        }
    }
}