package me.androidbox.echojournal.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.collections.immutable.persistentMapOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.collections.immutable.toPersistentMap
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.toLocalDateTime
import me.androidbox.echojournal.presentation.models.SelectableTopic
import me.androidbox.echojournal.presentation.models.populate

class EchoJournalViewModel(
  //  private val fetchEchoJournalsUseCase: FetchEchoJournalsUseCase
) : ViewModel() {

    private var hasFetched = false

    private var _echoEchoJournalState = MutableStateFlow<EchoJournalState>(EchoJournalState())
    val echoJournalState = _echoEchoJournalState.asStateFlow()

    /* .onStart {
            if(!hasFetched) {
                println("HASFETCHED")
                fetchEchoJournalEntries()
                hasFetched = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = EchoJournalState()
        )*/

    init {
        fetchEchoJournalEntries()
        fetchTopics()
    }

    fun updateTopicSelection(selectableTopic: SelectableTopic, index: Int) {
      /*  val listOfTopic = echoJournalState.value.listOfTopic

        val listOfUpdatedTopics = listOfTopic.toMutableList().apply {
            this[index] = selectableTopic
        }.toList()
*/
        _echoEchoJournalState.update { echoJournalState ->
            echoJournalState.copy(
                listOfTopic = emptyList()
            )
        }
    }

    fun fetchTopics() {
        viewModelScope.launch {
            val topics = listOf(
                SelectableTopic("Work", false),
                SelectableTopic("Life", false),
                SelectableTopic("Relocation", true),
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
                println("LAUNCH")
                val result = populate()
                println("RESULT")
                result.onSuccess { echoJournal ->
                    val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date

                    val groupedJournals = echoJournal.groupBy { journal ->

                        val journalDate = Instant.fromEpochMilliseconds(journal.date).toLocalDateTime(TimeZone.currentSystemDefault()).date

                        when(journalDate) {
                            today -> "Today"
                            today.minus(1, DateTimeUnit.DAY) -> "Yesterday"
                            else -> {
                                journalDate.toString()
                            }
                        }
                    }.mapValues {
                        it.value.toPersistentList()
                    }.toPersistentMap()

                    println("GROUPED $groupedJournals")

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