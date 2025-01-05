package me.androidbox.echojournal.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import echojournal.composeapp.generated.resources.Res
import echojournal.composeapp.generated.resources.excited
import echojournal.composeapp.generated.resources.neutral
import echojournal.composeapp.generated.resources.peaceful
import echojournal.composeapp.generated.resources.sad
import echojournal.composeapp.generated.resources.stressed
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.vectorResource
import kotlin.Unit

@Composable
fun EmotionContent(
    modifier: Modifier = Modifier,
    emotionList: List<EmotionData>,
    onEmotionClicked: (emotion: EmotionData, index: Int) -> Unit,
    onConfirmClicked: () -> Unit,
    onCancelClicked: () -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {

            itemsIndexed(
                items = emotionList,
                itemContent = { index, emotion ->
                    EmotionItem(
                        icon = {
                            Icon(
                                modifier = Modifier.clickable {
                                    onEmotionClicked(emotion.copy(isSelected = !emotion.isSelected), index)
                                },
                                imageVector = vectorResource(resource = emotion.emotion.resource),
                                contentDescription = emotion.emotion.description,
                                tint = if(emotion.isSelected) Color.Blue else Color.Green
                            )
                        }, description = emotion.emotion.description)
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(space = 16.dp)
        ) {
            Button(
                modifier = Modifier
                    .width(width = 100.dp),
                onClick = onCancelClicked,
                shape = CircleShape
            ) {
                Text(text = "Cancel")
            }

            Button(
                modifier = Modifier
                    .weight(1f),
                onClick = onConfirmClicked,
                shape = CircleShape
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null
                    )
                    Text(text = "Confirm")
                }
            }

        }
    }
}

@Composable
fun EmotionItem(
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit,
    description: String
) {
    Column(
        modifier = modifier
            .wrapContentSize(align = Alignment.Center)
    ) {
        icon()

        Text(
            text = description
        )
    }
}

data class EmotionData(
    val emotion: Emotion,
    val isSelected: Boolean = false
)

enum class Emotion(val description: String, val resource: DrawableResource) {
    STRESSED("Stressed", Res.drawable.stressed),
    SAD("Sad", Res.drawable.sad),
    NEUTRAL("Neutral", Res.drawable.neutral),
    PEACEFUL("Peaceful", Res.drawable.peaceful),
    EXCITED("Excited", Res.drawable.excited)
}
