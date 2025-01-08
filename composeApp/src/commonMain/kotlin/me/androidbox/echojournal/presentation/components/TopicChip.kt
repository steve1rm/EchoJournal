package me.androidbox.echojournal.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopicChip(
    modifier: Modifier = Modifier,
    topic: String,
    onCloseClicked: (topic: String) -> Unit
) {
    Row(
        modifier = modifier
            .background(color = Color(0xffF2F2F7), RoundedCornerShape(100f))
            .padding(horizontal = 8.dp)
            .height(32.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            "#",
            fontSize = 14.sp,
            color = Color(0xff40434F).copy(alpha = 0.5f))

        Text(
            text = topic,
            fontSize = 14.sp,
            color = Color.Black)

        Icon(
            modifier = Modifier
                .size(16.dp)
                .clickable {
                    onCloseClicked(topic)
                },
            imageVector = Icons.Default.Close,
            contentDescription = "Close topic",
            tint = Color(0xff40434F).copy(alpha = 0.5f)
        )
    }
}