package me.androidbox.echojournal.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun TopicDropDown(
    modifier: Modifier = Modifier
) {
    var listOfTopics = remember {
        mutableStateListOf("Jack", "Jared", "Jasper", "Bob", "Peter", "Steve", "Stand", "State")
    }

    var searchText by remember {
        mutableStateOf("")
    }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        TextField(
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
            }
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(
                items = listOfTopics.filter { it.contains(searchText, ignoreCase = true)},
                itemContent = { topic ->
                    Text(text = topic)
                }
            )

            if(listOfTopics.none {
                    it.contains(searchText, ignoreCase = true)}) {
                item {
                    Text("+ Create '$searchText'")
                }
            }
        }
    }
}