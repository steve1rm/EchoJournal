package me.androidbox.echojournal.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import echojournal.composeapp.generated.resources.Res
import echojournal.composeapp.generated.resources.mic
import echojournal.composeapp.generated.resources.recording_tick
import org.jetbrains.compose.resources.vectorResource

@Composable
fun RecordAudioButton(
    modifier: Modifier = Modifier,
    iconSize: Dp = 48.dp,
    isRecording: Boolean,
    isPaused: Boolean,
    onButtonClicked: () -> Unit,
) {

    Box(
        modifier = modifier
            .size(128.dp)
            .clip(CircleShape)
            .background(
                color = if(isRecording && !isPaused) Color(0xffEEF0FF) else Color.Transparent
            ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = modifier
                .size(108.dp)
                .clip(CircleShape)
                .clickable(onClick = onButtonClicked)
                .background(
                    color = if(isRecording && !isPaused) Color(0xffD9E2FF) else Color.Transparent
                ),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(CircleShape)
                    .background(
                        color = Color(0xff1F70F5)
                    )
                    .clickable(onClick = onButtonClicked),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier
                        .size(iconSize),
                    imageVector = if(isRecording && !isPaused) vectorResource(resource = Res.drawable.recording_tick) else vectorResource(resource = Res.drawable.mic),
                    contentDescription = "Record pause audio",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}
