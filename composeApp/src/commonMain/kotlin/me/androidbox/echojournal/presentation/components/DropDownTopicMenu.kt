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
import me.androidbox.echojournal.presentation.models.SelectableTopic


/** TODO Pass in the content rather than using the same code for the DropDownEmotionMenu */
@Composable
fun DropDownTopicMenu(
    modifier: Modifier = Modifier,
    dropDownMenuItems: List<SelectableTopic>,
    onMenuItemClicked: (item: SelectableTopic, index: Int) -> Unit,
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
            dropDownMenuItems.forEachIndexed { index, selectableTopic ->
                DropdownMenuItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                        .then(
                            if(selectableTopic.isSelected) {
                                Modifier.background(color = Color.LightGray.copy(alpha = 0.2f), shape = RoundedCornerShape(16.dp))
                            }
                            else {
                                Modifier.clip(shape = RoundedCornerShape(16.dp))
                            }
                        ),
                    onClick = {
                        onMenuItemClicked(selectableTopic, index)
                    },
                    content = {
                        DropDownItem(
                            description = selectableTopic.topic,
                            isSelected = selectableTopic.isSelected
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
            horizontalArrangement = Arrangement.spacedBy(space = 8.dp)
        ) {
            Text(
                text = "#",
                color = Color.LightGray
            )
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