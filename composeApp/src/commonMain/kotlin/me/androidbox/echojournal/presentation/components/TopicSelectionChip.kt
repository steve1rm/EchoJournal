package me.androidbox.echojournal.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
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
    listOfTopics: List<String>
) {

    Box(
        modifier = modifier
            .wrapContentSize()
            .border(width = 1.dp, color = Color.Black, shape = CircleShape)
            .padding(top = 4.dp, bottom = 4.dp, start = 8.dp, end = 8.dp)
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

                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = Icons.Default.Close,
                    contentDescription = "close",
                    tint = Color.Unspecified
                )
            }
        }
    }
}