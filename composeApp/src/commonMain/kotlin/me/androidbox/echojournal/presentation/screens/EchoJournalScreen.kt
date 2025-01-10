@file:OptIn(ExperimentalMaterial3Api::class)

package me.androidbox.echojournal.presentation.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import me.androidbox.echojournal.presentation.components.MoodSelectionChip

@Composable
fun EchoJournalScreen(
    modifier: Modifier = Modifier
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
            Row(modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()) {

                MoodSelectionChip(listOfMoods = emptyList())


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
                        contentDescription = "Add Meme",
                        tint = Color.White
                    )
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End
    )
}