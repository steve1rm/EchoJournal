package me.androidbox.echojournal

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.icerock.moko.permissions.compose.BindEffect
import me.androidbox.echojournal.presentation.screens.CreateJournalState
import me.androidbox.echojournal.presentation.screens.EchoJournalScreen
import me.androidbox.echojournal.presentation.screens.EchoJournalViewModel
import me.androidbox.echojournal.presentation.screens.NewEntryScreen
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

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
    val echoJournalViewModel = koinViewModel<EchoJournalViewModel>()
    val echoJournalState by echoJournalViewModel.echoJournalState.collectAsStateWithLifecycle()


    BindEffect(echoJournalViewModel.permissionsController)

    NavHost(
        navController = navController,
        startDestination = EchoJournalScreens.EchoJournalScreen.name,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(route = EchoJournalScreens.EchoJournalScreen.name) {
            SideEffect {
                echoJournalViewModel.fetchEchoJournalEntries()
            }
            EchoJournalScreen(
                navController = navController,
                echoJournalState = echoJournalState,
                updateTopicSelection = { selectableTopic, index ->
                    echoJournalViewModel.updateTopicSelection(selectableTopic, index)
                },
                clearAllTopics = {
                    echoJournalViewModel.clearAllTopics()
                },
                updateEmotionSelection = { selectableEmotion, index ->
                    echoJournalViewModel.updateEmotionSelection(selectableEmotion, index)
                },
                clearAllEmotions = {
                    echoJournalViewModel.clearAllEmotions()
                },
                onShowAppSettings = {
                    echoJournalViewModel.openAppSettings()
                },
                onShowPermissionDialog = {
                    echoJournalViewModel.provideOrRequestRecordAudioPermission()
                },
                startRecording = {
                    echoJournalViewModel.startRecording()
                },
                pauseResumeRecording = {
                    echoJournalViewModel.pauseResumeRecording()
                },
                cancelRecording = {
                    echoJournalViewModel.cancelRecording()
                },
            )
        }

        composable(route = EchoJournalScreens.NewEntryScreen.name) {
            LaunchedEffect(echoJournalState.createJournalState) {
                if (echoJournalState.createJournalState == CreateJournalState.Success) {
                    navController.navigateUp()
                }
            }
            NewEntryScreen(
                echoJournalState = echoJournalState,
                onTopicTextChanged = { text ->
                    echoJournalViewModel.fetchFilteredTopics(text)
                },
                onTopicCreated = { text ->
                    echoJournalViewModel.createTopic(text)
                },
                onCancelOrBackClicked = {
                    echoJournalViewModel.resetNewEntryState()
                    navController.navigateUp()
                },
                onSaveClicked = { journal ->
                    echoJournalViewModel.createJournal(journal)
                }
            )
        }
    }
}