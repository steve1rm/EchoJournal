package me.androidbox.echojournal.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TopicSelectionChip(
    modifier: Modifier = Modifier,
    listOfTopics: List<String>,
    onClearClicked: () -> Unit,
    onClicked: () -> Unit
) {

    Box(
        modifier = modifier
            .height(32.dp)
            .wrapContentWidth()
            .border(width = 1.dp, color = Color.Black, shape = CircleShape)
            .padding(top = 4.dp, bottom = 4.dp, start = 8.dp, end = 8.dp)
            .clickable(onClick = onClicked),
        contentAlignment = Alignment.Center
    ) {
        if(listOfTopics.isEmpty()) {
            Text(text = "All Topics")
        }
        else {
            Row(modifier = Modifier
                .wrapContentWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically) {

                if (listOfTopics.count() > 2) {
                    Text(text = "${listOfTopics.take(2).joinToString { it }} + ${listOfTopics.count() - 2}")
                } else {
                    Text(text = listOfTopics.joinToString { it })
                }

                IconButton(
                    modifier = Modifier
                        .size(16.dp),
                    onClick = onClearClicked
                ) {
                    Icon(
                        modifier = Modifier
                            .size(16.dp)
                            .clickable { onClearClicked() },
                        imageVector = Icons.Default.Close,
                        contentDescription = "close",
                        tint = Color(0xff40434F)
                    )
                }
            }
        }
    }
}