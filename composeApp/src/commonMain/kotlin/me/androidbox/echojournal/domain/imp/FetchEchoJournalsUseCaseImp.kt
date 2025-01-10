package me.androidbox.echojournal.domain.imp

import me.androidbox.echojournal.domain.FetchEchoJournalsUseCase
import me.androidbox.echojournal.presentation.models.EchoJournalUI
import me.androidbox.echojournal.presentation.models.populate

class FetchEchoJournalsUseCaseImp : FetchEchoJournalsUseCase {
    override suspend fun execute(): Result<List<EchoJournalUI>> {
        return populate()
    }
}