@file:OptIn(ExperimentalMaterial3Api::class)

package me.androidbox.echojournal.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import me.androidbox.echojournal.presentation.components.PlayBack

@Composable
fun NewEntryScreen(
    modifier: Modifier = Modifier,
    title: String = "New Entry",
    onSaveClicked: () -> Unit,
    onCancelClicked: () -> Unit
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = title,
                        textAlign = TextAlign.Center)
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Go Back")
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = Color.LightGray.copy(alpha = 0.6f)
                        ),
                        onClick = {

                        },
                        content = {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add mood"
                            )
                        })

                    TextField(
                        modifier = Modifier
                            .weight(1f),
                        placeholder = {
                            Text(text = "Add Title...")
                        },
                        onValueChange = { newTitle ->

                        },
                        value = ""
                    )
                }

                PlayBack(
                    duration = "12:30",
                    progress = 1f
                )

                /* SearchBar(

            )*/

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "description"
                    )

                    TextField(
                        modifier = Modifier
                            .weight(1f),
                        placeholder = {
                            Text(text = "Add Title...")
                        },
                        onValueChange = { newTitle ->

                        },
                        value = ""
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(align = Alignment.Bottom)
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(space = 16.dp)
                ) {
                    Button(
                        modifier = Modifier
                            .width(width = 100.dp),
                        onClick = {
                            onCancelClicked()
                        },
                        shape = CircleShape,
                        //      enabled = emotionList.any { it.isSelected }
                    ) {
                        androidx.compose.material.Text(text = "Cancel")
                    }

                    Button(
                        modifier = Modifier
                            .weight(1f),
                        onClick = onSaveClicked,
                        shape = CircleShape,
                        //     enabled = emotionList.any { it.isSelected }
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            androidx.compose.material.Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null
                            )
                            androidx.compose.material.Text(text = "Confirm")
                        }
                    }
                }
            }
        }
    )
}