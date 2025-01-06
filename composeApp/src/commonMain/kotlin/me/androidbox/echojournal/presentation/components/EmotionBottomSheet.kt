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
import me.androidbox.echojournal.presentation.components.models.EmotionBottomSheet
import me.androidbox.echojournal.presentation.components.models.SelectableEmotion

@Composable
fun EmotionBottomSheet(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    containerColor: Color,
    scrimColor: Color,
    sheetState: SheetState,
) {

    val emotionList = remember {
        mutableStateListOf<SelectableEmotion>(
            SelectableEmotion(EmotionBottomSheet.STRESSED, false),
            SelectableEmotion(EmotionBottomSheet.SAD, false),
            SelectableEmotion(EmotionBottomSheet.NEUTRAL, false),
            SelectableEmotion(EmotionBottomSheet.PEACEFUL, false),
            SelectableEmotion(EmotionBottomSheet.EXCITED, false)
        )
    }

    ModalBottomSheet(
        modifier = modifier,
        sheetState = sheetState,
        containerColor = containerColor,
        onDismissRequest = onDismiss,
        scrimColor = scrimColor,
    ) {

        EmotionBottomSheetContent(
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
                emotionList.forEachIndexed { index, item ->
                    emotionList[index] = item.copy(isSelected = false)
                }
            }
        )
    }
}
