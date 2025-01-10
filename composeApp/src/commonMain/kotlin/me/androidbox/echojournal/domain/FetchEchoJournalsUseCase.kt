package me.androidbox.echojournal.domain

import me.androidbox.echojournal.presentation.models.EchoJournalUI

interface FetchEchoJournalsUseCase {
    suspend fun execute(): Result<List<EchoJournalUI>>
}