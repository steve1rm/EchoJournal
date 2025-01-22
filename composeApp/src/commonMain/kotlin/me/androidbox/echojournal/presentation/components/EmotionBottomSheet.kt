@file:OptIn(ExperimentalMaterial3Api::class)

package me.androidbox.echojournal.presentation.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import me.androidbox.echojournal.presentation.models.EmotionMoodsFilled

@Composable
fun EmotionBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    containerColor: Color,
    scrimColor: Color,
    onEmotionSelected: (EmotionMoodsFilled) -> Unit,
    onDismiss: () -> Unit,
) {

    ModalBottomSheet(
        modifier = modifier,
        sheetState = sheetState,
        containerColor = containerColor,
        onDismissRequest = onDismiss,
        scrimColor = scrimColor,
    ) {

        EmotionBottomSheetContent(
            onConfirmClicked = { emotion ->
                onEmotionSelected.invoke(emotion)
            },
            onCancelClicked = {
                onDismiss.invoke()
            }
        )
    }
}
