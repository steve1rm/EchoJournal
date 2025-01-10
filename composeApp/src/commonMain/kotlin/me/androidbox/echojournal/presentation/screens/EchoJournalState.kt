package me.androidbox.echojournal.presentation.screens

import me.androidbox.echojournal.presentation.models.EchoJournalUI

data class EchoJournalState(
    val listOfJournals: Map<String, List<EchoJournalUI>> = emptyMap()
)
