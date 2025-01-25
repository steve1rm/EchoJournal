package me.androidbox.echojournal.data

import kotlinx.coroutines.flow.Flow
import me.androidbox.echojournal.presentation.models.EchoJournalUI

interface EchoJournalDataSource {

    suspend fun getAllTopic(): List<Topic>
    suspend fun getTopicWithPrefix(title: String): List<Topic>
    suspend fun insertTopic(topic: Topic)
    fun getAllJournal(): Flow<List<EchoJournalUI>>
    suspend fun insertJournal(journal: Journal)
}