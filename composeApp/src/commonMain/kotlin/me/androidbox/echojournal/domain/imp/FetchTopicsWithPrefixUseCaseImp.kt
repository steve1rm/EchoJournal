package me.androidbox.echojournal.domain.imp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import me.androidbox.echojournal.data.EchoJournalDataSource
import me.androidbox.echojournal.domain.FetchTopicsWithPrefixUseCase

class FetchTopicsWithPrefixUseCaseImp(
    private val dataSource: EchoJournalDataSource
) : FetchTopicsWithPrefixUseCase {
    override suspend fun execute(prefix: String): Result<List<String>> {
        return withContext(Dispatchers.IO) {
            try {
                val topics = dataSource.getTopicWithPrefix(prefix).map { topic ->
                    topic.title.orEmpty()
                }
                Result.success(topics)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}