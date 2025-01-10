package me.androidbox.echojournal.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import me.androidbox.echojournal.presentation.models.SelectableEmotion
import org.jetbrains.compose.resources.vectorResource

@Composable
fun MoodSelectionChip(
    modifier: Modifier = Modifier,
    listOfMoods: List<SelectableEmotion>,
    onClearClicked: () -> Unit
) {
    val emotions = remember {
        listOfMoods.map { selectableEmotion ->
            selectableEmotion.emotion
        }
    }

    Box(
        modifier = modifier
            .height(32.dp)
            .wrapContentWidth()
            .border(width = 1.dp, color = Color.Black, shape = CircleShape)
            .padding(top = 4.dp, bottom = 4.dp, start = 8.dp, end = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        if(emotions.isEmpty()) {
            Text(text = "All Moods")
        }
        else {
            LazyRow(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy((-6).dp)
            ) {
                items(
                    items = emotions,
                    key = {
                        it.description
                    },
                    itemContent = {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = vectorResource(it.resource),
                            contentDescription = it.description,
                            tint = Color.Unspecified
                        )
                    }
                )

                item {
                    Spacer(modifier = Modifier.width(16.dp))
                }

                item {
                    Text(text = emotions.joinToString { it.description })
                }

                item {
                    Spacer(modifier = Modifier.width(16.dp))
                }

                item {
                        Icon(
                            modifier = Modifier
                                .size(16.dp)
                                .clip(CircleShape)
                                .clickable(onClick = onClearClicked),
                            imageVector = Icons.Default.Close,
                            contentDescription = "close",
                            tint = Color(0xff40434F)
                        )
                }
            }
        }
    }
}
