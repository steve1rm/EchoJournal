@file:OptIn(ExperimentalMaterial3Api::class)

package me.androidbox.echojournal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import me.androidbox.echojournal.presentation.components.DropDownEmotionMenu
import me.androidbox.echojournal.presentation.components.EmotionBottomSheet
import me.androidbox.echojournal.presentation.components.EmotionBottomSheetContent
import me.androidbox.echojournal.presentation.components.EntryCard
import me.androidbox.echojournal.presentation.components.ExpandableText
import me.androidbox.echojournal.presentation.components.MoodSelectionChip
import me.androidbox.echojournal.presentation.components.PlayBack
import me.androidbox.echojournal.presentation.components.RecordAudioBottomSheet
import me.androidbox.echojournal.presentation.components.TopicChip
import me.androidbox.echojournal.presentation.components.TopicDropDown
import me.androidbox.echojournal.presentation.models.EmotionBottomSheet
import me.androidbox.echojournal.presentation.models.EmotionDropDown
import me.androidbox.echojournal.presentation.models.EmotionType
import me.androidbox.echojournal.presentation.models.SelectableEmotion
import me.androidbox.echojournal.presentation.screens.EchoJournalScreen
import me.androidbox.echojournal.presentation.screens.NewEntryScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // App()
            /*NewEntryScreen(
                onSaveClicked = {},
                onCancelClicked = {},
                onEmotionClicked = {}
            )*/
            EchoJournalScreen()
        }
    }
}


@Preview
@Composable
fun MoodSelectionChipPreview() {
    val moods = listOf(
        SelectableEmotion(EmotionDropDown.EXCITED, true),
        SelectableEmotion(EmotionDropDown.PEACEFUL, false),
        SelectableEmotion(EmotionDropDown.NEUTRAL, false)
    )
    MoodSelectionChip(listOfMoods = moods)
}

@Preview
@Composable
fun TopicChipPreview() {
    TopicChip(
        topic = "Compose",
        onCloseClicked = {}
    )
}

@Preview
@Composable
fun TopicDropDownPreview() {
    TopicDropDown(modifier = Modifier,
        listOfTopics = listOf("Jack", "Jared", "Jasper", "Bob", "Peter", "Steve", "Stand", "State"))
}

@Preview
@Composable
fun EntryCardPreview() {
    EntryCard(
        title = "My Entry",
        time = "12:34",
        start = "Start",
        end = "End",
        description = "This is a long description that might exceed the maximum number of lines and get truncated. In this case, we want to show an ellipsis and a 'Show more' button to expand the text. Long description that might exceed the maximum number of lines and get truncated",
        onAudioClicked = {},
        onShowMore = {}
    )
}

@Preview
@Composable
fun PlayBackPreview() {
    PlayBack(duration = "00:00/12:30", progress = 0.5f)
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
        onDismiss = {

        },
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

    EmotionBottomSheetContent(
        emotionList = emotionList,
        onEmotionClicked = { newEmotionUpdate, index ->
            emotionList[index] = newEmotionUpdate
        },
        onConfirmClicked = {
            println("onConfirmClicked")
        },
        onCancelClicked = {
            emotionList.replaceAll {
                it.copy(isSelected = false)
            }
        }
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