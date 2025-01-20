@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class,
    ExperimentalBasicSound::class
)

package me.androidbox.echojournal.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import app.lexilabs.basic.sound.Audio
import app.lexilabs.basic.sound.ExperimentalBasicSound
import dev.icerock.moko.permissions.PermissionState
import dev.icerock.moko.permissions.compose.BindEffect
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory
import dev.theolm.record.Record
import echojournal.composeapp.generated.resources.Res
import echojournal.composeapp.generated.resources.excited
import eu.iamkonstantin.kotlin.gadulka.GadulkaPlayer
import me.androidbox.echojournal.EchoJournalScreens
import me.androidbox.echojournal.presentation.components.DropDownEmotionMenu
import me.androidbox.echojournal.presentation.components.DropDownTopicMenu
import me.androidbox.echojournal.presentation.components.EntryCard
import me.androidbox.echojournal.presentation.components.MoodSelectionChip
import me.androidbox.echojournal.presentation.components.RecordAudioBottomSheet
import me.androidbox.echojournal.presentation.components.TopicSelectionChip
import me.androidbox.echojournal.presentation.models.EmotionMoodsFilled
import me.androidbox.echojournal.presentation.models.SelectableEmotion
import me.androidbox.echojournal.presentation.models.SelectableTopic
import org.jetbrains.compose.resources.vectorResource

@Composable
fun EchoJournalScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    echoJournalState: EchoJournalState,
    updateTopicSelection: (topic: SelectableTopic, index: Int) -> Unit,
    updateEmotionSelection: (emotion: SelectableEmotion, index: Int) -> Unit,
    onShowPermissionDialog: () -> Unit,
    onShowAppSettings: () -> Unit,
    clearAllTopics: () -> Unit,
    clearAllEmotions: () -> Unit,
    startRecording: () -> Unit,
    pauseResumeRecording: () -> Unit,
    cancelRecording: () -> Unit
) {

    var shouldOpenMoodDropdown by remember {
        mutableStateOf(false)
    }

    var shouldOpenTopicDropdown by remember {
        mutableStateOf(false)
    }

    var shouldOpenAudioRecordingBottomSheet by remember {
        mutableStateOf(false)
    }


    Scaffold(
        modifier = modifier.padding(horizontal = 16.dp),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Your Echo Journal", fontSize = 26.sp, fontWeight = FontWeight.W500)
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(modifier = Modifier.wrapContentSize()) {
                    FlowRow(
                        modifier = Modifier
                            .padding(paddingValues)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {

                        MoodSelectionChip(
                            listOfMoods = echoJournalState.emotionList.filter { it.isSelected },
                            onClearClicked = {
                                clearAllEmotions()
                            },
                            onClicked = {
                                shouldOpenMoodDropdown = true
                            })

                        TopicSelectionChip(
                            listOfTopics = echoJournalState.listOfTopic.filter { it.isSelected }.map { it.topic },
                            onClearClicked = {
                                clearAllTopics()
                            },
                            onClicked = {
                                shouldOpenTopicDropdown = true
                            })
                    }

                    if (shouldOpenTopicDropdown) {
                        DropDownTopicMenu(
                            dropDownMenuItems = echoJournalState.listOfTopic,
                            onMenuItemClicked = { selectableTopic, index ->
                                updateTopicSelection(selectableTopic.copy(isSelected = !selectableTopic.isSelected), index)
                            },
                            onDismissed = {
                                shouldOpenTopicDropdown = false
                            }
                        )
                    }

                    if (shouldOpenMoodDropdown) {
                        DropDownEmotionMenu(
                            dropDownMenuItems = echoJournalState.emotionList,
                            onMenuItemClicked = { emotion, index ->
                                updateEmotionSelection(emotion.copy(isSelected = !emotion.isSelected), index)
                            },
                            onDismissed = {
                                shouldOpenMoodDropdown = false
                            }
                        )
                    }
                }

                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    echoJournalState.listOfJournals.forEach { (header, data) ->

                        item {
                            Text(text = header, fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
                        }

                        itemsIndexed(
                            items = data,
                            itemContent = { index, journalItem ->
                                // Row with vertical line and card
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(IntrinsicSize.Max) // Ensures the height matches the tallest child
                                ) {
                                    // Icon with Divider
                                    Box(
                                        modifier = Modifier
                                            .width(24.dp) // Space for Icon and Divider
                                            .fillMaxHeight(), // Ensures the height matches the Row content
                                        contentAlignment = Alignment.TopCenter
                                    ) {
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            modifier = Modifier.fillMaxHeight()
                                        ) {
                                            Icon(
                                                imageVector = vectorResource(journalItem.emotion.resource),
                                                contentDescription = null,
                                                tint = Color.Unspecified,
                                                modifier = Modifier.size(24.dp)
                                            )

                                            // Vertical Line
                                       //     println("Header $header ${data.count()}")

                                                VerticalDivider(
                                                    thickness = 1.dp,
                                                    color = Color.DarkGray
                                                )

                                        }
                                    }

                                    Spacer(modifier = Modifier.width(16.dp))

                                    // EntryCard with Padding for Card Spacing
                                    EntryCard(
                                        title = journalItem.title,
                                        description = journalItem.description,
                                        start = "17:30",
                                        end = "12:20",
                                        time = "10:00",
                                        onShowMore = {},
                                        onAudioClicked = {},
                                        backgroundColor = journalItem.emotion.color,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 8.dp) // Padding for spacing between cards
                                    )
                                }
                            }
                        )
                    }
                }

                if(shouldOpenAudioRecordingBottomSheet) {
                    when(echoJournalState.permissionState) {
                        PermissionState.Granted -> {
                            RecordAudioBottomSheet(
                                onDismiss = {
                                    shouldOpenAudioRecordingBottomSheet = false
                                    // TODO CHANGE NAVIGATION BEHAVIOUR LATER
                                    navController.navigate(EchoJournalScreens.NewEntryScreen.name)
                                },
                               /* onPauseClicked = {

                                    val path = Record.stopRecording()
                                    println("RECORD STOPPED $path")
                                    val audio = Audio(
                                        "/data/user/0/me.androidbox.echojournal/cache/1737115297858.mp4",
                                        true
                                    )

                                    audio.pause()
                                },
                                onRecordClicked = {
                                    println("RECORD STARTED")
                                    Record.startRecording()
                                },*/
                                containerColor = Color.White,
                                scrimColor = Color.Black.copy(alpha = 0.32f),
                                sheetState = rememberModalBottomSheetState(),
                                startRecording = startRecording,
                                pauseResumeRecording = pauseResumeRecording,
                                cancelRecording = cancelRecording,
                                isRecording = echoJournalState.isRecording,
                                isPaused = echoJournalState.isPaused,
                                duration = echoJournalState.duration
                            )
                        }
                        PermissionState.DeniedAlways -> {
                            onShowAppSettings()
                        }
                        else -> {
                            /** show permission dialog again */
                            onShowPermissionDialog()
                        }
                    }
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                containerColor = Color(0xff578CFF),
                onClick = {
                    shouldOpenAudioRecordingBottomSheet = true
                },
                shape = CircleShape,
                content = {
                    Icon(

                        imageVector = Icons.Default.Add,
                        contentDescription = "Add entry",
                        tint = Color.White
                    )
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End
    )
}



/*  BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
      val boxHeight = this.maxHeight

      Row(modifier = Modifier.fillMaxWidth()) {
          Column(
              modifier = Modifier
                  .wrapContentWidth()
                  .fillMaxHeight(),
              horizontalAlignment = Alignment.CenterHorizontally
          ) {
              Icon(
                  imageVector = vectorResource(Res.drawable.excited),
                  contentDescription = null,
                  tint = Color.Unspecified
              )

              Spacer(modifier = Modifier.height(8.dp))

              Box(
                  modifier = Modifier
                      .fillMaxHeight() // Stretch the divider to the parent height
                      .width(4.dp)
                      .background(Color.DarkGray)
              )
              *//*VerticalDivider(
                                                modifier = Modifier.height(boxHeight),
                                                thickness = 4.dp,
                                                color = Color.DarkGray
                                            )*//*
                                        }
                                        Spacer(modifier = Modifier.width(16.dp))

                                        EntryCard(
                                            title = it.title,
                                            description = it.description,
                                            start = "17:30",
                                            end = "12:20",
                                            time = "10:00",
                                            onShowMore = {},
                                            onAudioClicked = {}
                                        )


                                    }
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                            }*/

/*item {
    Row(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.wrapContentWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier.padding(bottom = 8.dp),
                imageVector = vectorResource(Res.drawable.excited),
                contentDescription = null,
                tint = Color.Unspecified
            )
            if(data.size > 1){
                VerticalDivider(
                    modifier = Modifier.height((data.size * 90).dp),
                    thickness = 4.dp,
                    color = Color.DarkGray
                )
            }


        }
        Spacer(modifier = Modifier.width(8.dp))
    }

}*/

@Composable
fun VerticalDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = 1.dp,
    color: Color = Color.Black
) {
    Divider(
        modifier = modifier
            .fillMaxHeight()
            .width(thickness),
        color = color
    )
}
