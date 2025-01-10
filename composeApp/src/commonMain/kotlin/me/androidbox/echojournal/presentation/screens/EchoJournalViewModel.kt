package me.androidbox.echojournal.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.androidbox.echojournal.domain.FetchEchoJournalsUseCase

class EchoJournalViewModel(
    private val fetchEchoJournalsUseCase: FetchEchoJournalsUseCase
) : ViewModel() {

    private var hasFetched = false

    private var _echoEchoJournalState = MutableStateFlow<EchoJournalState>(EchoJournalState())
    val echoJournalState = _echoEchoJournalState.asStateFlow()
        .onStart {
            if(!hasFetched) {
                fetchEchoJournalEntries()
                hasFetched = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = EchoJournalState()
        )

    fun fetchEchoJournalEntries() {
        try {
            viewModelScope.launch {
                val result = fetchEchoJournalsUseCase.execute()

                result.onSuccess { echoJournal ->
                        _echoEchoJournalState.update { echoJournalState ->
                            echoJournalState.copy(
                                listOfJournals = echoJournal
                            )
                        }
                }.onFailure {
                        _echoEchoJournalState.update { echoJournalState ->
                            echoJournalState.copy(
                                listOfJournals = emptyList()
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