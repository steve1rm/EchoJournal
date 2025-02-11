@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.icerock.moko.permissions.PermissionState
import kotlinx.coroutines.launch
import me.androidbox.echojournal.EchoJournalScreens
import me.androidbox.echojournal.presentation.components.DropDownEmotionMenu
import me.androidbox.echojournal.presentation.components.DropDownTopicMenu
import me.androidbox.echojournal.presentation.components.EntryCard
import me.androidbox.echojournal.presentation.components.MoodSelectionChip
import me.androidbox.echojournal.presentation.components.RecordAudioBottomSheet
import me.androidbox.echojournal.presentation.components.TopicSelectionChip
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
    cancelRecording: () -> Unit,
    onRecordFinished: () -> Unit
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

    val sheetState = rememberModalBottomSheetState()

    val scope = rememberCoroutineScope()


   /* LaunchedEffect(echoJournalState.audioFile) {
        if (echoJournalState.audioFile.isNotBlank()) {
            // TODO CHANGE NAVIGATION BEHAVIOUR LATER
            navController.navigate(EchoJournalScreens.NewEntryScreen.name)
        }
    }*/

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Your Echo Journal", fontSize = 26.sp, fontWeight = FontWeight.W500)
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier.fillMaxWidth().padding(paddingValues)
                    .padding(horizontal = 16.dp)
            ) {
                Box(modifier = Modifier.wrapContentSize()) {
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.Start),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
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
                            listOfTopics = echoJournalState.listOfTopic.filter { it.isSelected }
                                .map { it.topic },
                            onClearClicked = {
                                clearAllTopics()
                            },
                            onClicked = {
                                shouldOpenTopicDropdown = true
                            })
                    }
                    if (shouldOpenTopicDropdown) {
                        /** Add text here and show the selected currency with a trailing arrow up/down
                         * https://proandroiddev.com/improving-the-compose-dropdownmenu-88469b1ef34
                         * */

                        DropDownTopicMenu(
                            dropDownMenuItems = echoJournalState.listOfTopic,
                            onMenuItemClicked = { selectableTopic, index ->
                                updateTopicSelection(
                                    selectableTopic.copy(isSelected = !selectableTopic.isSelected),
                                    index
                                )
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
                                updateEmotionSelection(
                                    emotion.copy(isSelected = !emotion.isSelected),
                                    index
                                )
                            },
                            onDismissed = {
                                shouldOpenMoodDropdown = false
                            }
                        )
                    }
                }

                var currentPlayingIndex by remember {
                    mutableIntStateOf(-1)
                }

                LazyColumn(
                    modifier = Modifier.fillMaxWidth().padding(top = 20.dp)
                ) {
                    echoJournalState.listOfJournals.forEach { (header, data) ->

                        item {
                            Text(
                                text = header.uppercase(),
                                style = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 12.sp,
                                    color = Color(0xFF40434F)
                                )
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }

                        itemsIndexed(
                            items = data,
                            itemContent = { currentIndex, journalItem ->
                                // Row with vertical line and card
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(IntrinsicSize.Max) // Ensures the height matches the tallest child
                                ) {
                                    // Icon with Divider
                                    Box(
                                        modifier = Modifier.fillMaxHeight(), // Ensures the height matches the Row content
                                        contentAlignment = Alignment.TopCenter
                                    ) {
                                        Box(
                                            modifier = Modifier
                                        ) {
                                            if (currentIndex > 0) {
                                                VerticalDivider(
                                                    modifier = Modifier
                                                        .height(24.dp)
                                                        .padding(start = 16.dp),
                                                    thickness = 0.5.dp,
                                                    color = Color.DarkGray
                                                )
                                            }
                                            if (currentIndex < (data.size - 1)) {
                                                VerticalDivider(
                                                    modifier = Modifier
                                                        .padding(top = 24.dp, start = 16.dp),
                                                    thickness = 0.5.dp,
                                                    color = Color.DarkGray
                                                )
                                            }
                                            Box(
                                                modifier = Modifier.padding(vertical = 8.dp),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Icon(
                                                    imageVector = vectorResource(journalItem.emotion.resource),
                                                    contentDescription = null,
                                                    tint = Color.Unspecified,
                                                    modifier = Modifier.size(32.dp)
                                                )
                                            }
                                        }
                                    }

                                    Spacer(modifier = Modifier.width(16.dp))

                                    // EntryCard with Padding for Card Spacing
                                    EntryCard(
                                        title = journalItem.title,
                                        description = journalItem.description,
                                        date = journalItem.date,
                                        duration = journalItem.audioDuration,
                                        onShowMore = {},
                                        onAudioClicked = {},
                                        backgroundColor = journalItem.emotion.color,
                                        audioFile = journalItem.audioFilePath,
                                        topics = journalItem.topics,
                                        index = currentPlayingIndex,
                                        currentIndex = currentIndex,
                                        updatePlayingIndex = { index ->
                                            currentPlayingIndex = index
                                        },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(
                                                top = if (currentIndex > 0) 8.dp else 0.dp,
                                                bottom = if (currentIndex < (data.size - 1)) 8.dp else 0.dp
                                            ) // Padding for spacing between cards
                                    )
                                }
                            }
                        )

                        item {
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }

                if (shouldOpenAudioRecordingBottomSheet) {
                    when (echoJournalState.permissionState) {
                        PermissionState.Granted -> {
                            RecordAudioBottomSheet(
                                onDismiss = {
                                    shouldOpenAudioRecordingBottomSheet = false
                                },
                                containerColor = Color.White,
                                scrimColor = Color.Black.copy(alpha = 0.32f),
                                sheetState = sheetState,
                                startRecording = startRecording,
                                pauseResumeRecording = pauseResumeRecording,
                                cancelRecording = {
                                    cancelRecording()
                                    /** Maybe should use a one-time event to close this bottom sheet*/
                                    scope.launch {
                                        sheetState.hide()
                                        shouldOpenAudioRecordingBottomSheet = false
                                    }
                                },
                                isRecording = echoJournalState.isRecording,
                                isPaused = echoJournalState.isPaused,
                                duration = echoJournalState.duration,
                                onRecordFinished = onRecordFinished
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

                    println(echoJournalState.permissionState)
                    if (echoJournalState.permissionState == PermissionState.Granted) {
                        startRecording()
                    }
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
