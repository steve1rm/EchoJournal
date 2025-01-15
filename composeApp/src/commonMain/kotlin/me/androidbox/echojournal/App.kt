package me.androidbox.echojournal

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import me.androidbox.echojournal.presentation.screens.EchoJournalScreen
import me.androidbox.echojournal.presentation.screens.NewEntryScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        EchoJournalApp()
    }
}

enum class EchoJournalScreens {
    EchoJournalScreen,
    NewEntryScreen
}

@Composable
fun EchoJournalApp(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()

    // Get the name of the current screen
    val currentScreen = EchoJournalScreens.valueOf(
        backStackEntry?.destination?.route ?: EchoJournalScreens.EchoJournalScreen.name
    )

    Scaffold(
        topBar = {
            // TODO LATER
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = EchoJournalScreens.EchoJournalScreen.name,
            modifier = Modifier.fillMaxSize()
        ) {
            composable(route = EchoJournalScreens.EchoJournalScreen.name) {
//                EchoJournalScreen()
            }

            composable(route = EchoJournalScreens.NewEntryScreen.name) {
                NewEntryScreen(
                    onSaveClicked = {},
                    onCancelClicked = {},
                    onEmotionClicked = {}
                )
            }
        }
    }
}