package me.androidbox.echojournal.data

class EchoJournalDataSourceImp(
    private val database: EchoJournalDatabase
): EchoJournalDataSource {


    override fun getAllTopic(): List<Topic> {
        return database.topicDao().getAll()
    }

    override fun getTopicWithPrefix(title: String): List<Topic> {
        return database.topicDao().getTopicWithPrefix(title)
    }

    override suspend fun insertTopic(topic: Topic) {
        database.topicDao().insertAll(topic)
    }

    override fun getAllJournal(): List<Journal> {
        return database.journalDao().getAll()
    }

    override suspend fun insertJournal(journal: Journal) {
        database.journalDao().insertAll(journal)
    }
}