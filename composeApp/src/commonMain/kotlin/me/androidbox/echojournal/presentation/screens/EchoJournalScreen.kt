@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)

package me.androidbox.echojournal.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.androidbox.echojournal.presentation.components.EntryCard
import me.androidbox.echojournal.presentation.components.MoodSelectionChip
import me.androidbox.echojournal.presentation.components.TopicSelectionChip
import me.androidbox.echojournal.presentation.models.EchoJournalUI
import me.androidbox.echojournal.presentation.models.EmotionMoodsFilled
import me.androidbox.echojournal.presentation.models.SelectableEmotion

@Composable
fun EchoJournalScreen(
    modifier: Modifier = Modifier,
    listOfJournals: Map<String, List<EchoJournalUI>>
) {


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
            FlowRow(modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                verticalArrangement = Arrangement.spacedBy(4.dp)) {

                MoodSelectionChip(
                    listOfMoods = listOf(
                        SelectableEmotion(EmotionMoodsFilled.EXCITED, true),
                        SelectableEmotion(EmotionMoodsFilled.PEACEFUL, false),
                        SelectableEmotion(EmotionMoodsFilled.NEUTRAL, false)
                    ),
                    onClearClicked = {})

                TopicSelectionChip(
                    listOfTopics = listOf("Android", "iPhone", "Dell XPS", "Macbook Pro"),
                    onClearClicked = {})

                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    listOfJournals.forEach { (header, data) ->
                        item {
                            Text(text = header, fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
                        }

                        items(
                            items = data,
                            itemContent = {
                                Text(text = it.title)
                            }
                        )

                    }



                    /*items(
                        items = listOfJournals,

                       *//* itemContent = { echoJournal ->
                            EntryCard(
                                title = echoJournal.title,
                                description = echoJournal.description,
                                time = "7:30",
                                start = "",
                                end = "",
                                onShowMore = {

                                },
                                onAudioClicked = {

                                }
                            )
                        }*/
                    //)
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                containerColor = Color(0xff578CFF),
                onClick = {
                  //  showBottomSheet = true
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