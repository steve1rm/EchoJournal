package me.androidbox.echojournal.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import echojournal.composeapp.generated.resources.Res
import echojournal.composeapp.generated.resources.excited
import me.androidbox.echojournal.presentation.models.AudioControl
import org.jetbrains.compose.resources.vectorResource

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
        Row(modifier = Modifier.fillMaxWidth()) {

            Column(
                modifier = Modifier.wrapContentWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Icon(
                    imageVector = vectorResource(Res.drawable.excited),
                    contentDescription = null,
                    tint = Color.Unspecified
                )

                Spacer(modifier.weight(1f))

                VerticalDivider(
                    thickness = 4.dp,
                    color = Color.DarkGray
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

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
                    duration = "12:30",
                    progress = 1f
                )

                ExpandableText(description = description)
            }
        }
    }
}
