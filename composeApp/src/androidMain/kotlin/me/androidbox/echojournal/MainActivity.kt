@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalBasicSound::class)

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
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import app.lexilabs.basic.sound.ExperimentalBasicSound
import me.androidbox.echojournal.presentation.components.DropDownEmotionMenu
import me.androidbox.echojournal.presentation.components.DropDownTopicMenu
import me.androidbox.echojournal.presentation.components.EmotionBottomSheet
import me.androidbox.echojournal.presentation.components.EmotionBottomSheetContent
import me.androidbox.echojournal.presentation.components.EntryCard
import me.androidbox.echojournal.presentation.components.ExpandableText
import me.androidbox.echojournal.presentation.components.MoodSelectionChip
import me.androidbox.echojournal.presentation.components.PlayBack
import me.androidbox.echojournal.presentation.components.RecordAudioBottomSheet
import me.androidbox.echojournal.presentation.components.TopicChip
import me.androidbox.echojournal.presentation.components.TopicSelectionChip
import me.androidbox.echojournal.presentation.models.EmotionMoodsFilled
import me.androidbox.echojournal.presentation.models.SelectableEmotion
import me.androidbox.echojournal.presentation.models.SelectableTopic

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
             App()
        }
    }
}


@Preview
@Composable
fun TopicSelectionChipPreview() {
    TopicSelectionChip(listOfTopics = listOf("Android", "iPhone", "Dell XPS", "Macbook Pro"), onClearClicked = {}, onClicked = {})
}

@Preview
@Composable
fun TopicSelectionChipPreviewEmpty() {
    TopicSelectionChip(listOfTopics = emptyList(), onClearClicked =  {}, onClicked = {})
}

@Preview
@Composable
fun TopicSelectionChipPreviewTwo() {
    TopicSelectionChip(listOfTopics = listOf("Work", "Balance"), onClearClicked = {}, onClicked = {})
}


@Preview
@Composable
fun MoodSelectionChipPreview() {
    val moods = listOf(
        SelectableEmotion(EmotionMoodsFilled.EXCITED, true),
        SelectableEmotion(EmotionMoodsFilled.PEACEFUL, false),
        SelectableEmotion(EmotionMoodsFilled.NEUTRAL, false)
    )
    MoodSelectionChip(listOfMoods = moods, onClearClicked = {}, onClicked = {})
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
//    TopicDropDown(modifier = Modifier,
//        listOfTopics = listOf("Jack", "Jared", "Jasper", "Bob", "Peter", "Steve", "Stand", "State"))
}

@Preview
@Composable
fun EntryCardPreview() {
    EntryCard(
        title = "My Entry",
        date = 0L,
        description = "This is a long description that might exceed the maximum number of lines and get truncated. In this case, we want to show an ellipsis and a 'Show more' button to expand the text. Long description that might exceed the maximum number of lines and get truncated",
        onAudioClicked = {},
        backgroundColor = Color.Green.copy(alpha = 0.5f),
        onShowMore = {},
        audioFile = "",
        duration = 0L,
        topics = emptyList(),
        updatePlayingIndex = {},
        currentIndex = -1,
        index = -1
    )
}

@Preview
@Composable
fun PlayBackPreview() {
    PlayBack(duration = 0L, backgroundColor = Color.Green.copy(alpha = 0.5f), audioFile = "")
}

@Preview
@Composable
fun DropDownTopicMenuPreview() {
    val dropDownMenuItems = listOf(
        SelectableTopic("Work", false),
        SelectableTopic("Life", true),
        SelectableTopic("Relocation", false),
        SelectableTopic("Rest", false),
        SelectableTopic("Travel", true),
        SelectableTopic("Flight Tickets", false)
    )

    DropDownTopicMenu(
        dropDownMenuItems = dropDownMenuItems,
        onMenuItemClicked = { _, _ -> },
        onDismissed = {}
    )
}

@Preview
@Composable
fun DropDownEmotionMenuPreview() {
    val emotionList = remember {
        mutableStateListOf<SelectableEmotion>(
            SelectableEmotion(EmotionMoodsFilled.STRESSED, false),
            SelectableEmotion(EmotionMoodsFilled.SAD, false),
            SelectableEmotion(EmotionMoodsFilled.NEUTRAL, false),
            SelectableEmotion(EmotionMoodsFilled.PEACEFUL, false),
            SelectableEmotion(EmotionMoodsFilled.EXCITED, false)
        )
    }

    DropDownEmotionMenu(
        dropDownMenuItems = emotionList,
        onMenuItemClicked = { emotion, index ->
            emotionList[index] = emotion.copy(isSelected = !emotion.isSelected)
        },
        onDismissed = {})
}

@Preview
@Composable
fun EmotionBottomSheetPreview() {
    val sheetState = rememberModalBottomSheetState()

    EmotionBottomSheet(
        sheetState = sheetState,
        onDismiss = {},
        onEmotionSelected = {},
        containerColor = Color.White,
        scrimColor = Color.Black
    )
}

@Preview
@Composable
fun EmotionContentPreview() {
    EmotionBottomSheetContent(
        onConfirmClicked = {
            println("onConfirmClicked")
        },
        onCancelClicked = {
            println("onCancelClicked")
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
        containerColor = Color.White,
        scrimColor = Color.Black.copy(alpha = 0.32f),
        modifier = TODO(),
        startRecording = TODO(),
        pauseResumeRecording = TODO(),
        cancelRecording = TODO(),
        isRecording = true,
        isPaused = false,
        duration = 0L
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