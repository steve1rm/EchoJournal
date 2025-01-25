package me.androidbox.echojournal.domain

import kotlinx.coroutines.flow.Flow
import me.androidbox.echojournal.presentation.models.EchoJournalUI

interface FetchEchoJournalsUseCase {
    fun execute(): Flow<List<EchoJournalUI>>
}