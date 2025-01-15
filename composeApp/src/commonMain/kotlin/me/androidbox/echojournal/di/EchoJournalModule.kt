package me.androidbox.echojournal.di

import me.androidbox.echojournal.presentation.screens.EchoJournalViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val echoJournalModule = module {
    viewModel {
        EchoJournalViewModel()
    }
}