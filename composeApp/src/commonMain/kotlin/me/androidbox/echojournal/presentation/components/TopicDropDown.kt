@file:OptIn(ExperimentalLayoutApi::class)

package me.androidbox.echojournal.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TopicDropDown(
    modifier: Modifier = Modifier
) {
    var listOfTopics = remember {
        mutableStateListOf("Jack", "Jared", "Jasper", "Bob", "Peter", "Steve", "Stand", "State")
    }

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
        TextField(
            modifier = Modifier.onFocusChanged { focusState ->
                showList = focusState.isFocused
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedLabelColor = Color.Transparent,
                unfocusedLabelColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            value = searchText,
            onValueChange = { newSearch ->
                searchText = newSearch
            },
            placeholder = {
                Text("# Text")
            },
        )

        AnimatedVisibility(
            visible = selectedTopics.isNotEmpty(),
            content = {
                FlowRow(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    content = {
                        selectedTopics.forEach {
                            Text(text = it)
                        }
                    }
                )
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        AnimatedVisibility(
            visible = showList
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(
                    key = { topic ->
                        topic
                    },
                    items = listOfTopics.filter { it.contains(searchText, ignoreCase = true)},
                    itemContent = { topic ->
                        Text(
                            modifier = Modifier.clickable {
                                selectedTopics.add(topic)
                            },
                            text = topic)
                    }
                )

                if(listOfTopics.none {
                        it.contains(searchText, ignoreCase = true)
                    }) {
                    item {
                        Text("+ Create '$searchText'")
                    }
                }
            }
        }
    }
}