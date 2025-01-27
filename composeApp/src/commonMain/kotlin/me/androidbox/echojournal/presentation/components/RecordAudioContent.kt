package me.androidbox.echojournal.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import echojournal.composeapp.generated.resources.Res
import echojournal.composeapp.generated.resources.pause
import echojournal.composeapp.generated.resources.stop
import echojournal.composeapp.generated.resources.tick
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import org.jetbrains.compose.resources.vectorResource

@Composable
fun RecordAudioContent(
    modifier: Modifier = Modifier,
    duration: Long,
    isRecording: Boolean,
    isPaused: Boolean,
    startRecording: () -> Unit,
    pauseResumeRecording: () -> Unit,
    cancelRecording: () -> Unit,
    onRecordFinished: () -> Unit
) {

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = if(isRecording && !isPaused) "Recording your memories..." else "Recording paused ",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis)

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = formatTime(duration),
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal)

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    cancelRecording()
                }
            ) {
                Icon(
                    imageVector = vectorResource(resource = Res.drawable.stop),
                    contentDescription = "stop",
                    tint = Color.Unspecified
                )
            }

            RecordAudioButton(
                onButtonClicked = {
                    startRecording()
                    if(isRecording && !isPaused) {
                        println("Record button has finished recording")
                        onRecordFinished()
                    }
                },
                isRecording = isRecording,
                isPaused = isPaused
            )

            IconButton(
                onClick = {
                    pauseResumeRecording()
                    if(isRecording && isPaused) {
                        println("Puase bottom has finished recording")
                        onRecordFinished()
                    }
                }
            ) {
                Icon(
                    imageVector = if(isRecording && !isPaused) vectorResource(resource = Res.drawable.pause) else vectorResource(resource = Res.drawable.tick),
                    contentDescription = "stop",
                    tint = Color.Unspecified
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}



fun formatTime(millis: Long): String {
    val totalSeconds = millis / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60

    val minutesStr = if (minutes < 10) "0$minutes" else "$minutes"
    val secondsStr = if (seconds < 10) "0$seconds" else "$seconds"

    return "$minutesStr:$secondsStr"
}