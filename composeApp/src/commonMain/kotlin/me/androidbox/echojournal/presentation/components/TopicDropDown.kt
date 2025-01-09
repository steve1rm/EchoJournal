@file:OptIn(ExperimentalLayoutApi::class)

package me.androidbox.echojournal.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopicDropDown(
    modifier: Modifier = Modifier,
    listOfTopics: List<String>
) {

    var selectedTopics = remember {
        mutableStateListOf<String>()
    }

    var searchText by remember {
        mutableStateOf("")
    }

    var showList by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        /** Maybe change this to BasicTextField2 that has no inner padding */
        TextField(
            modifier = Modifier.onFocusChanged { focusState ->
                showList = focusState.isFocused
            },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedLabelColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedLabelColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            value = searchText,
            onValueChange = { newSearch ->
                searchText = newSearch
            },
            placeholder = {
                Text("# Topic")
            },
        )

        AnimatedVisibility(
            visible = selectedTopics.isNotEmpty(),
            content = {
                Column {
                    FlowRow(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        content = {
                            selectedTopics.forEach { selectedTopic ->
                                key(selectedTopic) {
                                    TopicChip(
                                        topic = selectedTopic,
                                        onCloseClicked = { selectedTopic ->
                                            selectedTopics.remove(selectedTopic)
                                        })
                                }
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        )

        AnimatedVisibility(
            visible = showList
        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(disabledElevation = 16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth().padding(all = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    items(
                        key = { topic ->
                            topic
                        },
                        items = listOfTopics.filter { it.contains(searchText, ignoreCase = true) },
                        itemContent = { topic ->

                            Row(
                                modifier = Modifier.wrapContentWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    "#",
                                    fontSize = 14.sp,
                                    color = Color(0xff40434F).copy(alpha = 0.5f)
                                )

                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            selectedTopics.add(topic)
                                        },
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.W500,
                                    color = Color(0xff3B4663),
                                    text = topic
                                )
                            }
                        }
                    )

                    if (listOfTopics.firstOrNull {
                            it.contains(searchText, ignoreCase = true)
                        } == null) {
                        item {
                            Text(
                                modifier = Modifier.clickable {
                                    selectedTopics.add(searchText)
                                },
                                color = Color(0xff00419C),
                                fontWeight = FontWeight.Medium,
                                text = "+ Create '$searchText'"
                            )
                        }
                    }
                }
            }
        }
    }
}