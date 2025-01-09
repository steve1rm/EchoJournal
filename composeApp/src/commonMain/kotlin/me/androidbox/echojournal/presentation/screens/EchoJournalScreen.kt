package me.androidbox.echojournal.presentation.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import echojournal.composeapp.generated.resources.Res
import echojournal.composeapp.generated.resources.fab
import org.jetbrains.compose.resources.vectorResource

@Composable
fun EchoJournalScreen(
    modifier: Modifier = Modifier
) {

    Scaffold(
        topBar = {
            Text(text = "Your Echo Journal")
        },
        content = { paddingValues ->

        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier
                    .padding(bottom = 32.dp, end = 16.dp),
                onClick = {
                  //  showBottomSheet = true
                },
                content = {
                    Icon(
                        imageVector = vectorResource(Res.drawable.fab),
                        contentDescription = "Add Meme",
                        tint = Color.Unspecified
                    )
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End
    )
}