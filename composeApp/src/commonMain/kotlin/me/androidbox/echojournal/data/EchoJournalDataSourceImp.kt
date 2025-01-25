package me.androidbox.echojournal.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.androidbox.echojournal.presentation.models.EchoJournalUI
import me.androidbox.echojournal.presentation.models.getEmotionMoodsFilled

class EchoJournalDataSourceImp(
    private val database: EchoJournalDatabase
): EchoJournalDataSource {


    override suspend fun getAllTopic(): List<Topic> {
        return database.topicDao().getAll()
    }

    override suspend fun getTopicWithPrefix(title: String): List<Topic> {
        return database.topicDao().getTopicWithPrefix(title)
    }

    override suspend fun insertTopic(topic: Topic) {
        database.topicDao().insertAll(topic)
    }

    override fun getAllJournal(): Flow<List<EchoJournalUI>> {
        return database.journalDao().getAll().map { listOfJournals ->
            listOfJournals.map { journal ->
                EchoJournalUI(
                    title = journal.title.orEmpty(),
                    description = journal.description.orEmpty(),
                    audioFilePath = journal.audioFilePath.orEmpty(),
                    topics = journal.topics ?: listOf(),
                    emotion = getEmotionMoodsFilled(journal.emotion.orEmpty()),
                    audioDuration = journal.audioDuration,
                    date = journal.createdAt,
                )
            }
        }
    }

    override suspend fun insertJournal(journal: Journal) {
        database.journalDao().insertAll(journal)
    }
}