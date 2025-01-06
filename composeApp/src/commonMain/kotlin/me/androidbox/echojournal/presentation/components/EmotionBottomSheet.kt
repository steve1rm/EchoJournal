@file:OptIn(ExperimentalMaterial3Api::class)

package me.androidbox.echojournal.presentation.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun EmotionBottomSheet(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onPauseClicked: () -> Unit,
    onRecordClicked: () -> Unit,
    containerColor: Color,
    scrimColor: Color,
    sheetState: SheetState,
) {

    val emotionList = remember {
        mutableStateListOf<EmotionData>(
            EmotionData(EmotionBottomSheet.STRESSED, false),
            EmotionData(EmotionBottomSheet.SAD, false),
            EmotionData(EmotionBottomSheet.NEUTRAL, false),
            EmotionData(EmotionBottomSheet.PEACEFUL, false),
            EmotionData(EmotionBottomSheet.EXCITED, false)
        )
    }

    ModalBottomSheet(
        modifier = modifier,
        sheetState = sheetState,
        containerColor = containerColor,
        onDismissRequest = onDismiss,
        scrimColor = scrimColor,
    ) {

        EmotionContent(
            emotionList = emotionList,
            onEmotionClicked = { updateEmotion, index ->
                emotionList[index] = updateEmotion
            },
            onConfirmClicked = {
                emotionList.filter {
                    it.isSelected
                }
            },
            onCancelClicked = {
                onDismiss()
            }
        )
    }
}
