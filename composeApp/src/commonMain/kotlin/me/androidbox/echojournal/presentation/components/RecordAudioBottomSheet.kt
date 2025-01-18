@file:OptIn(ExperimentalMaterial3Api::class)

package me.androidbox.echojournal.presentation.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun RecordAudioBottomSheet(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    containerColor: Color,
    scrimColor: Color,
    sheetState: SheetState,
    startRecording: () -> Unit,
    finishRecording: () -> Unit,
    pauseResumeRecording: () -> Unit,
    cancelRecording: () -> Unit
) {

    ModalBottomSheet(
        modifier = modifier,
        sheetState = sheetState,
        containerColor = containerColor,
        onDismissRequest = onDismiss,
        scrimColor = scrimColor,
    ) {

        RecordAudioContent(
            title = "Your record of memories",
            duration = "12:34",
            startRecording = startRecording,
            finishRecording = finishRecording,
            pauseResumeRecording = pauseResumeRecording,
            cancelRecording = cancelRecording,
        )
    }
}
