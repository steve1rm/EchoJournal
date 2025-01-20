package me.androidbox.echojournal.domain.imp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import me.androidbox.echojournal.data.EchoJournalDataSource
import me.androidbox.echojournal.domain.FetchEchoJournalsUseCase
import me.androidbox.echojournal.presentation.models.EchoJournalUI
import me.androidbox.echojournal.presentation.models.getEmotionMoodsFilled

class FetchEchoJournalsUseCaseImp(
    private val dataSource: EchoJournalDataSource
) : FetchEchoJournalsUseCase {
    override suspend fun execute(): Result<List<EchoJournalUI>> {
        return withContext(Dispatchers.IO) {
            try {
                val journals = dataSource.getAllJournal().map { journal ->
                    // Transformation logic
                    EchoJournalUI(
                        title = journal.title.orEmpty(),
                        description = journal.description.orEmpty(),
                        audioFilePath = journal.audioFilePath.orEmpty(),
                        topics = journal.topics ?: listOf(),
                        emotion = getEmotionMoodsFilled(journal.emotion.orEmpty()),
                        date = journal.createdAt,
                    )
                }
                Result.success(journals)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}