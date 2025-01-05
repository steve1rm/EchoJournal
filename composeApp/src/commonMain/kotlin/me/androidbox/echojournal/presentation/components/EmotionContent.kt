package me.androidbox.echojournal.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    onCancel: () -> Unit
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
