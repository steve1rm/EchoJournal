package me.androidbox.echojournal.presentation.screens

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.PersistentMap
import kotlinx.collections.immutable.persistentMapOf
import me.androidbox.echojournal.presentation.models.EchoJournalUI
import me.androidbox.echojournal.presentation.models.SelectableTopic

data class EchoJournalState(
    val listOfJournals: PersistentMap<String, PersistentList<EchoJournalUI>> = persistentMapOf(),
    val listOfTopic: List<SelectableTopic> = emptyList()
)
