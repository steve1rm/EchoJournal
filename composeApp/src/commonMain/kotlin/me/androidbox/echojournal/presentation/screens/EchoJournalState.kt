package me.androidbox.echojournal.presentation.screens

import me.androidbox.echojournal.presentation.models.EchoJournalUI

data class EchoJournalState(
    val listOfJournals: List<EchoJournalUI> = emptyList()
)
