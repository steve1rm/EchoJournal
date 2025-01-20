@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalBasicSound::class)

package me.androidbox.echojournal

import android.media.MediaRecorder
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
import me.androidbox.echojournal.presentation.components.TopicSelectionChip
import me.androidbox.echojournal.presentation.models.EmotionMoodsOutlined
import me.androidbox.echojournal.presentation.models.EmotionMoodsFilled
import me.androidbox.echojournal.presentation.models.SelectableEmotion
import me.androidbox.echojournal.presentation.screens.EchoJournalScreen
import me.androidbox.echojournal.presentation.screens.EchoJournalViewModel
import androidx.compose.runtime.getValue
import androidx.core.net.toUri
import app.lexilabs.basic.sound.ExperimentalBasicSound
import dev.icerock.moko.permissions.compose.BindEffect
import me.androidbox.echojournal.presentation.components.DropDownTopicMenu
import me.androidbox.echojournal.presentation.models.SelectableTopic
import org.koin.compose.viewmodel.koinViewModel
import java.io.File

class MainActivity : ComponentActivity() {

    fun extractFileFromCache(fileName: String): Uri {
        // Get the cache directory
        val cacheDir = this@MainActivity.cacheDir

        // Create a File object for the file
        val file = File(cacheDir, fileName)

        // Check if the file exists. If it doesn't, this means the file doesn't exist, or it has been deleted by Android.
        if (!file.exists()) {
            println("File doesn't exist in cache!")
            return Uri.EMPTY
        }

        return file.toUri()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
             App()
          /*  NewEntryScreen(
                onSaveClicked = {},
                onCancelClicked = {},
                onEmotionClicked = {}
            )
*/


        //    val audio = Audio(extractFileFromCache("1737106878440.mp4").toString(), false)
          //  val audio = Audio("/data/user/0/me.androidbox.echojournal/cache/1737115297858.mp4", true)
         //   val audio = Audio("https://dare.wisc.edu/wp-content/uploads/sites/1051/2008/11/MS072.mp3", false)



         /*   val resource = "https://dare.wisc.edu/wp-content/uploads/sites/1051/2008/11/MS072.mp3"
            val audio = Audio(resource, true) // AutoPlay is marked "true"*/

          /*  MediaPlayer.create(this@MainActivity, extractFileFromCache("1737115297858.mp4")).also {
               // it.setDataSource("/data/user/0/me.androidbox.echojournal/cache/1737115297858.mp4")
                it.start()
            }*/

          /*  val player = GadulkaPlayer(this@MainActivity)
            player.play("/data/user/0/me.androidbox.echojournal/cache/1737115297858.mp4")
            player.stop()*/


            val echoJournalViewModel = koinViewModel<EchoJournalViewModel>()
            val echoJournalState by echoJournalViewModel.echoJournalState.collectAsStateWithLifecycle()


            BindEffect(echoJournalViewModel.permissionsController)

//            EchoJournalScreen(
//                echoJournalState = echoJournalState,
//                updateTopicSelection = { selectableTopic, index ->
//                    echoJournalViewModel.updateTopicSelection(selectableTopic, index)
//                },
//                clearAllTopics = {
//                    echoJournalViewModel.clearAllTopics()
//                },
//                updateEmotionSelection = { selectableEmotion, index ->
//                    echoJournalViewModel.updateEmotionSelection(selectableEmotion, index)
//                },
//                clearAllEmotions = {
//                    echoJournalViewModel.clearAllEmotions()
//                },
//                onShowAppSettings = {
//                    echoJournalViewModel.openAppSettings()
//                },
//                onShowPermissionDialog = {
//                    echoJournalViewModel.provideOrRequestRecordAudioPermission()
//                },
//                startRecording = {
//                    echoJournalViewModel.startRecording()
//                },
//                pauseResumeRecording = {
//                    echoJournalViewModel.pauseResumeRecording()
//                },
//                cancelRecording = {
//                    echoJournalViewModel.cancelRecording()
//                },
//            )
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
        time = "12:34",
        start = "Start",
        end = "End",
        description = "This is a long description that might exceed the maximum number of lines and get truncated. In this case, we want to show an ellipsis and a 'Show more' button to expand the text. Long description that might exceed the maximum number of lines and get truncated",
        onAudioClicked = {},
        backgroundColor = Color.Green.copy(alpha = 0.5f),
        onShowMore = {}
    )
}

@Preview
@Composable
fun PlayBackPreview() {
    PlayBack(duration = "00:00/12:30", progress = 0.5f, backgroundColor = Color.Green.copy(alpha = 0.5f))
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
            SelectableEmotion(EmotionMoodsOutlined.STRESSED, false),
            SelectableEmotion(EmotionMoodsOutlined.SAD, false),
            SelectableEmotion(EmotionMoodsOutlined.NEUTRAL, false),
            SelectableEmotion(EmotionMoodsOutlined.PEACEFUL, false),
            SelectableEmotion(EmotionMoodsOutlined.EXCITED, false)
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