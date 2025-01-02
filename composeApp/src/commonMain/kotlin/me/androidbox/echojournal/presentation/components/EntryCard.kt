package me.androidbox.echojournal.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

@Composable
fun EntryCard(
    modifier: Modifier = Modifier,
    title: String,
    time: String,
    start: String,
    end: String,
    description: String,
    onAudioClicked: (audioControl: AudioControl) -> Unit,
    onShowMore: () -> Unit
) {

    var showMore by remember { mutableStateOf(false) }
    var hasOverflow by remember { mutableStateOf(false) }

    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {

                Text(text = title, fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 24.sp)

                Text(text = time, fontWeight = FontWeight.Medium, color = Color.Gray, fontSize = 18.sp)
            }

            /** Audio control */

            Text(
                text = description,
                fontWeight = FontWeight.Medium,
                color = Color.Gray,
                fontSize = 18.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 3,
                onTextLayout = {
                    if(it.hasVisualOverflow) {
                        /** show read more */
                    }
                }
            )
        }
    }
}

enum class MoodColors(val mood: String, val color: Color) {
    RED("Stressed", Color.Red),
    BLUE("Sad", Color.Blue),
    GREEN("Neutral", Color.Green),
    PINK("Peaceful", Color( 0xFFFCE4EC)),
    ORANGE("Excited", Color(0xFFFFE8CC))
}

enum class AudioControl(val icon: ImageVector) {
    PLAY(Icons.Default.PlayArrow),
    STOP(Icons.Default.PlayArrow),
    PAUSE(Icons.Default.PlayArrow)
}