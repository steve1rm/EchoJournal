package me.androidbox.echojournal.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import me.androidbox.echojournal.presentation.models.AudioControl

@Composable
fun EntryCard(
    modifier: Modifier = Modifier,
    title: String,
    time: String,
    start: String,
    end: String,
    description: String,
    backgroundColor: Color,
    onAudioClicked: (audioControl: AudioControl) -> Unit,
    onShowMore: () -> Unit
) {

    var showMore by remember { mutableStateOf(false) }
    var hasOverflow by remember { mutableStateOf(false) }

        Card(
            modifier = modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.fillMaxWidth(),
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
                        text = time,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                }

                /** Audio control */
                 PlayBack(
                     duration = 0L,
                     backgroundColor = backgroundColor,
                     audioFile = ""
                 )

                ExpandableText(description = description)
            }
        }
}
