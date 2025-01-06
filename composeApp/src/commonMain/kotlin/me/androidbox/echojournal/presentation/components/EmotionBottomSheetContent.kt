package me.androidbox.echojournal.presentation.components

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
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import me.androidbox.echojournal.presentation.components.models.SelectableEmotion
import org.jetbrains.compose.resources.vectorResource
import kotlin.Unit

@Composable
fun EmotionBottomSheetContent(
    modifier: Modifier = Modifier,
    emotionList: List<SelectableEmotion>,
    onEmotionClicked: (emotion: SelectableEmotion, index: Int) -> Unit,
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
                itemContent = { index, selectableEmotion ->
                    EmotionItem(
                        icon = {
                            Icon(
                                modifier = Modifier.clickable {
                                    onEmotionClicked(selectableEmotion.copy(isSelected = !selectableEmotion.isSelected), index)
                                },
                                imageVector = vectorResource(resource = selectableEmotion.emotion.resource),
                                contentDescription = selectableEmotion.emotion.description,
                                tint = if(selectableEmotion.isSelected) selectableEmotion.emotion.color else Color(0xff9FABCD)
                            )
                        },
                        description = selectableEmotion.emotion.description,
                        isSelected = selectableEmotion.isSelected)
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
                onClick = {
                    onCancelClicked()
                },
                shape = CircleShape,
                enabled = emotionList.any { it.isSelected }
            ) {
                Text(text = "Cancel")
            }

            Button(
                modifier = Modifier
                    .weight(1f),
                onClick = onConfirmClicked,
                shape = CircleShape,
                enabled = emotionList.any { it.isSelected }
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
    description: String,
    isSelected: Boolean
) {
    Column(
        modifier = modifier
            .wrapContentSize(align = Alignment.Center)
    ) {
        icon()

        Text(
            text = description,
            color = if(isSelected) Color.Black else Color(0xff6C7085)
        )
    }
}