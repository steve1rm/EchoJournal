@file:OptIn(ExperimentalBasicSound::class)

package me.androidbox.echojournal.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import app.lexilabs.basic.sound.Audio
import app.lexilabs.basic.sound.ExperimentalBasicSound
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import me.androidbox.echojournal.presentation.TimeAndEmitPlay

@OptIn(FlowPreview::class)
@Composable
fun PlayBack(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    duration: Long,
    audioFile: String,
    onPlayback: () -> Unit
) {

    var isPlaying by remember {
        mutableStateOf(false)
    }

    var coroutineScope = rememberCoroutineScope()
    var timeAndEmit = remember {
        TimeAndEmitPlay(coroutineScope)
    }

    LaunchedEffect(duration) {
        println("TIME AND EMIT SET")
        timeAndEmit.setDuration(playbackDuration = duration)
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                shape = RoundedCornerShape(100f),
                color = backgroundColor.copy(alpha = 0.2f)
            )
            .padding(horizontal = 8.dp)
            .padding(top = 4.dp, bottom = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        IconButton(
            modifier = Modifier
                .background(color = Color.White, RoundedCornerShape(100f)),
            onClick = {

                onPlayback()
                val audio = Audio(audioFile, true)

                if (!isPlaying) {
//                    audio.play()
                    timeAndEmit.start()
                } else {
                    audio.pause()
                    timeAndEmit.pause()
                }
                isPlaying = !isPlaying
            }
        ) {
            Icon(
                modifier = Modifier
                    .size(32.dp),
                imageVector = if (timeAndEmit.state.value == TimeAndEmitPlay.PlayerState.PLAY) Icons.Default.Close else Icons.Default.PlayArrow,
                contentDescription = "Play back button",
                tint = backgroundColor
            )
        }

        LinearProgressIndicator(
            modifier = Modifier.weight(1f),
            progress = {
                (timeAndEmit.currentTime.value / duration.toFloat()).coerceIn(0.0F..1.0F)

            },
            trackColor = Color.LightGray,
            color = backgroundColor.copy(alpha = 0.6f),
            strokeCap = StrokeCap.Square
        )

        Text(
            text = "${formatTime(timeAndEmit.currentTime.value)}/${formatTime(duration)}"
        )
    }
}