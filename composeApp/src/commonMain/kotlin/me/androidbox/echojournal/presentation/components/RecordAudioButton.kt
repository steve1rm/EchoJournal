package me.androidbox.echojournal.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RecordAudioButton(
    icon: ImageVector,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    iconSize: Dp = 48.dp,
    onButtonClicked: () -> Unit
) {
    Box(
        modifier = modifier
            .size(76.dp)
            .clip(CircleShape)
            .clickable(onClick = onButtonClicked)
            .background(
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
            ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(
                    color = MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier
                    .size(iconSize),
                imageVector = icon,
                contentDescription = contentDescription,
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}
