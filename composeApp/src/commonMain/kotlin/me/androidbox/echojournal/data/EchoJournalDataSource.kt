package me.androidbox.echojournal.data

interface EchoJournalDataSource {

    fun getAllTopic(): List<Topic>
    fun getTopicWithPrefix(title: String): List<Topic>
    suspend fun insertTopic(topic: Topic)
    fun getAllJournal(): List<Journal>
    suspend fun insertJournal(journal: Journal)
}