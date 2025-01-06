@file:OptIn(ExperimentalMaterial3Api::class)

package me.androidbox.echojournal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import me.androidbox.echojournal.presentation.components.DropDownEmotionMenu
import me.androidbox.echojournal.presentation.components.models.EmotionBottomSheet
import me.androidbox.echojournal.presentation.components.EmotionContent
import me.androidbox.echojournal.presentation.components.models.SelectableEmotion
import me.androidbox.echojournal.presentation.components.models.EmotionDropDown
import me.androidbox.echojournal.presentation.components.ExpandableText
import me.androidbox.echojournal.presentation.components.RecordAudioBottomSheet

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }
}


@Preview
@Composable
fun DropDownEmotionMenuPreview() {
    val emotionList = remember {
        mutableStateListOf<SelectableEmotion>(
            SelectableEmotion(EmotionDropDown.STRESSED, false),
            SelectableEmotion(EmotionDropDown.SAD, false),
            SelectableEmotion(EmotionDropDown.NEUTRAL, false),
            SelectableEmotion(EmotionDropDown.PEACEFUL, false),
            SelectableEmotion(EmotionDropDown.EXCITED, false)
        )
    }

    DropDownEmotionMenu(
        dropDownMenuItems = emotionList,
        onMenuItemClicked = { emotion, index ->
            emotionList[index] = emotion.copy(isSelected = !emotion.isSelected)
        })
}

@Preview
@Composable
fun EmotionBottomSheetPreview() {
    val sheetState = rememberModalBottomSheetState()
    EmotionBottomSheet(
        sheetState = sheetState,
        onDismiss = { /*TODO*/ },
        onPauseClicked = { /*TODO*/ },
        onRecordClicked = { /*TODO*/ },
        containerColor = Color.White,
        scrimColor = Color.Black
    )
}

@Preview
@Composable
fun EmotionContentPreview() {
    val emotionList = remember {
        mutableStateListOf<SelectableEmotion>(
            SelectableEmotion(EmotionBottomSheet.STRESSED, false),
            SelectableEmotion(EmotionBottomSheet.SAD, false),
            SelectableEmotion(EmotionBottomSheet.NEUTRAL, false),
            SelectableEmotion(EmotionBottomSheet.PEACEFUL, false),
            SelectableEmotion(EmotionBottomSheet.EXCITED, false)
        )
    }

    EmotionContent(
        emotionList = emotionList,
        onEmotionClicked = { newEmotionUpdate, index ->
            emotionList[index] = newEmotionUpdate
        },
        onConfirmClicked = {},
        onCancelClicked = {}
    )
}

@Preview
@Composable
fun RecordAudioBottomSheetPreview() {
    val sheetState = rememberModalBottomSheetState()
    RecordAudioBottomSheet(
        sheetState = sheetState,
        onDismiss = { /*TODO*/ },
        onPauseClicked = { /*TODO*/ },
        onRecordClicked = { /*TODO*/ },
        containerColor = Color.White,
        scrimColor = Color.Black.copy(alpha = 0.32f),
    )
}

@Preview
@Composable
fun ExpandableTextPreview() {
    ExpandableText(description = "This is a long description that might exceed the maximum number of lines and get truncated. In this case, we want to show an ellipsis and a 'Show more' button to expand the text. Long description that might exceed the maximum number of lines and get truncated")
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}