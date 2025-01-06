package me.androidbox.echojournal.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp

@Composable
fun PlayBack(
    modifier: Modifier = Modifier,
    duration: String,
    progress: Float
) {
    Row(modifier = modifier
        .fillMaxWidth()
        .background(shape = RoundedCornerShape(100f), color = Color.Green.copy(alpha = 0.2f))
        .padding(horizontal = 16.dp)
        .padding(top = 4.dp, bottom = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)) {

        IconButton(
            modifier = Modifier
                .background(color = Color.White, RoundedCornerShape(100f)),
            onClick = {}
        ) {
            Icon(
                modifier = Modifier
                    .size(32.dp),
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "Play back button",
                tint = Color.Blue)
        }

        LinearProgressIndicator(
            modifier = Modifier.weight(1f),
            progress = {
                progress
            },
            trackColor = Color.Blue,
            color = Color.Magenta,
            strokeCap = StrokeCap.Round
        )

        Text(
            text = "00:00/12:30"
        )
    }
}