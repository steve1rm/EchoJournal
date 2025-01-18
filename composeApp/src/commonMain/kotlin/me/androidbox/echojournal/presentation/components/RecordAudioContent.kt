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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import echojournal.composeapp.generated.resources.Res
import echojournal.composeapp.generated.resources.mic
import echojournal.composeapp.generated.resources.pause
import echojournal.composeapp.generated.resources.stop
import org.jetbrains.compose.resources.imageResource
import org.jetbrains.compose.resources.vectorResource

@Composable
fun RecordAudioContent(
    modifier: Modifier = Modifier,
    title: String,
    duration: String,
    startRecording: () -> Unit,
    finishRecording: () -> Unit,
    pauseResumeRecording: () -> Unit,
    cancelRecording: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis)

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = duration,
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
                icon = vectorResource(resource = Res.drawable.mic),
                onButtonClicked = {
                    pauseResumeRecording()
                }
            )

            IconButton(
                onClick = {
                    pauseResumeRecording()
                }
            ) {
                Icon(
                    imageVector = vectorResource(resource = Res.drawable.pause),
                    contentDescription = "stop",
                    tint = Color.Unspecified
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}