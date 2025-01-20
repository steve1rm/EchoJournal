package me.androidbox.echojournal.domain.imp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import me.androidbox.echojournal.data.EchoJournalDataSource
import me.androidbox.echojournal.data.Journal
import me.androidbox.echojournal.domain.CreateJournalUseCase

class CreateJournalUseCaseImp(
    private val dataSource: EchoJournalDataSource
) : CreateJournalUseCase {
    override suspend fun execute(journal: Journal): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                dataSource.insertJournal(journal)
                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}