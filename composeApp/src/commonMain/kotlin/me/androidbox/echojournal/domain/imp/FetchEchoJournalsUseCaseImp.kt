package me.androidbox.echojournal.domain.imp

import kotlinx.coroutines.flow.Flow
import me.androidbox.echojournal.data.EchoJournalDataSource
import me.androidbox.echojournal.domain.FetchEchoJournalsUseCase
import me.androidbox.echojournal.presentation.models.EchoJournalUI

class FetchEchoJournalsUseCaseImp(
    private val dataSource: EchoJournalDataSource
) : FetchEchoJournalsUseCase {
    override fun execute(): Flow<List<EchoJournalUI>> {
        return dataSource.getAllJournal()
    }
}