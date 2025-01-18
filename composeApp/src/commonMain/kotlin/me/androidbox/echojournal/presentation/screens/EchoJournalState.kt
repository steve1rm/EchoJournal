package me.androidbox.echojournal.presentation.screens

import dev.icerock.moko.permissions.PermissionState
import me.androidbox.echojournal.presentation.models.EchoJournalUI
import me.androidbox.echojournal.presentation.models.SelectableEmotion
import me.androidbox.echojournal.presentation.models.SelectableTopic

data class EchoJournalState(
    val listOfJournals: Map<String, List<EchoJournalUI>> = mapOf(),
    val listOfTopic: List<SelectableTopic> = emptyList(),
    val emotionList: List<SelectableEmotion> = emptyList(),
    val permissionState: PermissionState = PermissionState.NotDetermined,
    val isRecording: Boolean = false
)

