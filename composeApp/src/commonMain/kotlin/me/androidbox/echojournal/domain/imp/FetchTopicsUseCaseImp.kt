package me.androidbox.echojournal.domain.imp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import me.androidbox.echojournal.data.EchoJournalDataSource
import me.androidbox.echojournal.domain.FetchTopicsUseCase
import me.androidbox.echojournal.presentation.models.SelectableTopic

class FetchTopicsUseCaseImp(
    private val dataSource: EchoJournalDataSource
): FetchTopicsUseCase {
    override suspend fun execute(): Result<List<SelectableTopic>> {
        return withContext(Dispatchers.IO) {
            try {
                val topics = dataSource.getAllTopic().map { topic ->
                    // Transformation logic
                    SelectableTopic(topic = topic.title.orEmpty())
                }
                Result.success(topics)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}