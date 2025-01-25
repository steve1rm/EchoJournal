@file:OptIn(ExperimentalMaterial3Api::class)

package me.androidbox.echojournal.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import echojournal.composeapp.generated.resources.Res
import echojournal.composeapp.generated.resources.add
import echojournal.composeapp.generated.resources.edit
import kotlinx.datetime.Clock
import me.androidbox.echojournal.presentation.components.EmotionBottomSheet
import me.androidbox.echojournal.presentation.components.PlayBack
import me.androidbox.echojournal.presentation.components.TopicDropDown
import me.androidbox.echojournal.presentation.models.EchoJournalUI
import me.androidbox.echojournal.presentation.models.EmotionMoodsFilled
import org.jetbrains.compose.resources.vectorResource

@Composable
fun NewEntryScreen(
    modifier: Modifier = Modifier,
    echoJournalState: EchoJournalState,
    toolBarTitle: String = "New Entry",
    onTopicTextChanged: (String) -> Unit,
    onTopicCreated: (String) -> Unit,
    onSaveClicked: (EchoJournalUI) -> Unit,
    onCancelOrBackClicked: () -> Unit,
) {

    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    val selectedTopics = remember {
        mutableStateListOf<String>()
    }

    val sheetState = rememberModalBottomSheetState()

    var openEmotionBottomSheet by remember {
        mutableStateOf(false)
    }

    var selectedEmotion by remember {
        mutableStateOf<EmotionMoodsFilled?>(null)
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = toolBarTitle,
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    Icon(
                        modifier = Modifier.clickable {
                            onCancelOrBackClicked.invoke()
                        },
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Go Back"
                    )
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        modifier = Modifier.wrapContentSize(),
                        onClick = {
                            openEmotionBottomSheet = true
                        },
                        content = {
                            Icon(
                                modifier = Modifier.size(32.dp),
                                imageVector = vectorResource(
                                    selectedEmotion?.resource ?: Res.drawable.add
                                ),
                                contentDescription = selectedEmotion?.description ?: "Add mood",
                                tint = Color.Unspecified
                            )
                        }
                    )
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedLabelColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedLabelColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedTextColor = Color(0xffC1C3CE)
                        ),
                        placeholder = {
                            Text(
                                text = "Add Title...",
                                style = TextStyle(
                                    fontSize = 26.sp,
                                    fontWeight = FontWeight.W500,
                                    color = Color(0xFFC1C3CE)
                                )
                            )
                        },
                        onValueChange = { newTitle ->
                            title = newTitle
                        },
                        value = title,
                        textStyle = TextStyle(
                            fontSize = 26.sp,
                            fontWeight = FontWeight.W500,
                            color = Color(0xFF191A20)
                        )
                    )
                }

                println("New Entry Screen [${echoJournalState.audioFile}]")
                PlayBack(
                    modifier = Modifier,
                    duration = echoJournalState.duration,
                    audioFile = echoJournalState.audioFile,
                    backgroundColor = selectedEmotion?.color ?: Color.Unspecified
                )

                TopicDropDown(
                    selectedTopics = selectedTopics,
                    listOfTopics = echoJournalState.listOfTopicWithPrefix,
                    onTextChanged = onTopicTextChanged,
                    onTopicCreated = { searchText ->
                        onTopicCreated.invoke(searchText)
                        selectedTopics.add(searchText)
                    },
                    onTopicRemoved = { selectedTopic ->
                        selectedTopics.remove(selectedTopic)
                    },
                    onTopicSelected = { selectedTopic ->
                        selectedTopics.add(selectedTopic)
                    }
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = vectorResource(Res.drawable.edit),
                        contentDescription = "description",
                        tint = Color.Unspecified
                    )

                    TextField(
                        modifier = Modifier
                            .weight(1f),
                        colors = androidx.compose.material3.TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedLabelColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedLabelColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        placeholder = {
                            Text(text = "Add Description...")
                        },
                        onValueChange = { newDescription ->
                            description = newDescription
                        },
                        value = description
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(space = 16.dp)
                ) {
                    Button(
                        modifier = Modifier
                            .width(width = 100.dp),
                        onClick = {
                            onCancelOrBackClicked.invoke()
                        },
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xffEEF0FF),
                        )
                        //      enabled = emotionList.any { it.isSelected }
                    ) {
                        Text(
                            text = "Cancel",
                            color = Color(0xff00419C)
                        )
                    }

                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = {
                            onSaveClicked(
                                EchoJournalUI(
                                    title = title,
                                    description = description,
                                    topics = selectedTopics,
                                    audioFilePath = echoJournalState.audioFile,
                                    emotion = selectedEmotion ?: EmotionMoodsFilled.NEUTRAL,
                                    date = Clock.System.now().toEpochMilliseconds(),
                                    audioDuration = echoJournalState.duration
                                )
                            )
                        },
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xff578CFF)
                        ),
                        enabled = (title.isNotBlank() && description.isNotBlank() && selectedTopics.isNotEmpty() && selectedEmotion != null)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            androidx.compose.material.Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = Color.White
                            )
                            Text(
                                text = "Save",
                                color = Color.White
                            )
                        }
                    }
                }
            }

            if (openEmotionBottomSheet) {
                EmotionBottomSheet(
                    sheetState = sheetState,
                    containerColor = Color.White,
                    scrimColor = Color.Black.copy(alpha = 0.32f),
                    onEmotionSelected = { emotion ->
                        selectedEmotion = emotion
                        openEmotionBottomSheet = false
                    },
                    onDismiss = {
                        openEmotionBottomSheet = false
                    }
                )
            }
        }
    )
}