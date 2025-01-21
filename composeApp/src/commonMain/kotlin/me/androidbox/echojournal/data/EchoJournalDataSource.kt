package me.androidbox.echojournal.data

interface EchoJournalDataSource {

    suspend fun getAllTopic(): List<Topic>
    suspend fun getTopicWithPrefix(title: String): List<Topic>
    suspend fun insertTopic(topic: Topic)
    suspend fun getAllJournal(): List<Journal>
    suspend fun insertJournal(journal: Journal)
}