@file:OptIn(ExperimentalLayoutApi::class)

package me.androidbox.echojournal.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.Padding
import kotlinx.datetime.toLocalDateTime
import me.androidbox.echojournal.presentation.models.AudioControl

@Composable
fun EntryCard(
    modifier: Modifier = Modifier,
    title: String,
    duration: Long,
    date: Long,
    audioFile: String,
    description: String,
    backgroundColor: Color,
    topics: List<String>,
    index: Int,
    currentIndex: Int,
    onAudioClicked: (audioControl: AudioControl) -> Unit,
    onShowMore: () -> Unit,
    updatePlayingIndex: (index: Int) -> Unit
) {

    var showMore by remember { mutableStateOf(false) }
    var hasOverflow by remember { mutableStateOf(false) }

        Card(
            modifier = modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(space = 16.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = title,
                        fontWeight = FontWeight.W500,
                        color = Color.Black,
                        fontSize = 16.sp
                    )

                    Text(
                        text = Instant.fromEpochMilliseconds(date)
                            .toLocalDateTime(TimeZone.currentSystemDefault()).time
                            .format(
                                LocalTime.Format {
                                    hour(Padding.NONE)
                                    chars(":")
                                    minute(Padding.ZERO)
                                    chars(" ")
                                    amPmMarker("am", "pm")
                                }
                            ),
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                }

                /** Audio control */
                println("ENTRY CARD $audioFile")
                 PlayBack(
                     duration = duration,
                     backgroundColor = backgroundColor,
                     playingIndex = index,
                     currentIndex = currentIndex,
                     audioFile = audioFile,
                     updatePlayIndex = { index ->
                         updatePlayingIndex(index)
                     }
                 )

                ExpandableText(description = description)

                /** Flow row of topics */
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    topics.forEach { topic ->
                        TopicChip(
                            topic = topic,
                            showCloseButton = false
                        )
                    }
                }
            }
        }
}
