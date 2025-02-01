package me.androidbox.echojournal.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import me.androidbox.echojournal.presentation.models.SelectableEmotion
import org.jetbrains.compose.resources.vectorResource

@Composable
fun DropDownEmotionMenu(
    modifier: Modifier = Modifier,
    dropDownMenuItems: List<SelectableEmotion>,
    onMenuItemClicked: (item: SelectableEmotion, index: Int) -> Unit,
    onDismissed: () -> Unit
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }

    DropdownMenu(
        modifier = modifier.fillMaxWidth(),
        expanded = !isExpanded,
        onDismissRequest = {
            isExpanded = false
            onDismissed()
        },
        content = {
            dropDownMenuItems.forEachIndexed { index, emotion ->
                DropdownMenuItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                        .then(
                            if(emotion.isSelected) {
                                Modifier.background(color = Color.LightGray.copy(alpha = 0.2f), shape = RoundedCornerShape(16.dp))
                            }
                            else {
                                Modifier.clip(shape = RoundedCornerShape(16.dp))
                            }
                        ),
                    onClick = {
                        onMenuItemClicked(emotion, index)
                    },
                    content = {
                        DropDownItem(
                            icon = {
                                Icon(
                                    imageVector = vectorResource(resource = emotion.emotion.resource),
                                    contentDescription = emotion.emotion.description,
                                    tint = Color.Unspecified
                                )
                            },
                            description = emotion.emotion.description,
                            isSelected = emotion.isSelected
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
    description: String,
    isSelected: Boolean
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

        if(isSelected) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = Color(0xff00419C)
                )
            }
        }
    }
}
