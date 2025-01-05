package me.androidbox.echojournal.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.vectorResource

@Composable
fun DropDownEmotionMenu(
    dropDownMenuItems: List<EmotionData>
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }

    val emotionList = remember {
        mutableStateListOf<EmotionData>(
            EmotionData(Emotion.STRESSED, false),
            EmotionData(Emotion.SAD, false),
            EmotionData(Emotion.NEUTRAL, false),
            EmotionData(Emotion.PEACEFUL, false),
            EmotionData(Emotion.EXCITED, false)
        )
    }

    DropdownMenu(
        modifier = Modifier.fillMaxWidth(),
        expanded = !isExpanded,
        onDismissRequest = {
            isExpanded = false
        },
        content = {
            emotionList.forEach { emotion ->
                DropdownMenuItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                        .background(color = Color.LightGray, shape = RoundedCornerShape(16.dp)),
                    onClick = {

                    },
                    content = {
                        DropDownItem(
                            icon = {
                                Icon(
                                    imageVector = vectorResource(resource = emotion.emotion.resource),
                                    contentDescription = emotion.emotion.description,
                                    tint = if(emotion.isSelected) Color.Blue else Color.Green
                                )
                            },
                            description = emotion.emotion.description
                        )
                    }
                )
            }
        }
    )
}


@Composable
fun DropDownItem(
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit,
    description: String
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(space = 16.dp)
        ) {
            icon()


            Text(
                text = description
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = Color.Green
            )
        }
    }
}