package me.androidbox.echojournal.domain.imp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import me.androidbox.echojournal.data.EchoJournalDataSource
import me.androidbox.echojournal.data.Topic
import me.androidbox.echojournal.domain.CreateTopicUseCase

class CreateTopicUseCaseImp(
    private val dataSource: EchoJournalDataSource
): CreateTopicUseCase {
    override suspend fun execute(topic: String): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                dataSource.insertTopic(
                    Topic(title = topic)
                )
                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}