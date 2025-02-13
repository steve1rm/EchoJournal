@file:OptIn(ExperimentalMaterial3Api::class)

package me.androidbox.echojournal.presentation.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun RecordAudioBottomSheet(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    containerColor: Color,
    scrimColor: Color,
    sheetState: SheetState,
    isRecording: Boolean,
    isPaused: Boolean,
    duration: Long,
    startRecording: () -> Unit,
    pauseResumeRecording: () -> Unit,
    cancelRecording: () -> Unit,
    onRecordFinished: () -> Unit
) {

    ModalBottomSheet(
        modifier = modifier,
        sheetState = sheetState,
        containerColor = containerColor,
        onDismissRequest = onDismiss,
        scrimColor = scrimColor,
    ) {

        RecordAudioContent(
            duration = duration,
            startRecording = startRecording,
            pauseResumeRecording = pauseResumeRecording,
            cancelRecording = cancelRecording,
            isRecording = isRecording,
            isPaused = isPaused,
            onRecordFinished = onRecordFinished
        )
    }
}
