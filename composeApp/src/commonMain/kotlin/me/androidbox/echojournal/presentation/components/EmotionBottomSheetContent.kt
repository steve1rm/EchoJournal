package me.androidbox.echojournal.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import me.androidbox.echojournal.presentation.models.EmotionMoodsFilled
import me.androidbox.echojournal.presentation.models.EmotionMoodsOutlined
import me.androidbox.echojournal.presentation.models.getEmotionMoodsFilled
import org.jetbrains.compose.resources.vectorResource

@Composable
fun EmotionBottomSheetContent(
    modifier: Modifier = Modifier,
    onConfirmClicked: (EmotionMoodsFilled) -> Unit,
    onCancelClicked: () -> Unit
) {

    val emotions = remember {
        mutableStateListOf(
            EmotionMoodsOutlined.STRESSED,
            EmotionMoodsOutlined.SAD,
            EmotionMoodsOutlined.NEUTRAL,
            EmotionMoodsOutlined.PEACEFUL,
            EmotionMoodsOutlined.EXCITED
        )
    }

    var selectedIndex = remember { mutableIntStateOf(-1) }

    Column(modifier = modifier.fillMaxWidth()) {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {

            itemsIndexed(
                items = emotions,
                itemContent = { index, emotion ->
                    EmotionItem(
                        icon = {
                            Icon(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clickable(enabled = false) {
                                        selectedIndex.value = index
                                    },
                                imageVector = if (selectedIndex.value == index) vectorResource(
                                    getEmotionMoodsFilled(emotion.name).resource
                                )
                                else vectorResource(emotion.resource),
                                contentDescription = emotion.description,
                                tint = if (selectedIndex.value == index) Color.Unspecified
                                else Color(0xFF9FABCD)
                            )
                        },
                        description = emotion.description,
                        isSelected = selectedIndex.value == index
                    )
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
            ) {
                Text(text = "Cancel")
            }

            Button(
                modifier = Modifier
                    .weight(1f),
                onClick = {
                    onConfirmClicked(getEmotionMoodsFilled(emotions[selectedIndex.value].name))
                },
                shape = CircleShape,
                enabled = (selectedIndex.value >= 0)
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
            .wrapContentSize(align = Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        icon()
        Spacer(Modifier.height(8.dp))
        Text(
            text = description,
            style = TextStyle(
                fontWeight = if (isSelected) FontWeight.W500 else FontWeight.W400,
                color = if (isSelected) Color(0xFF191A20) else Color(0xFF6C7085)
            )
        )
    }
}