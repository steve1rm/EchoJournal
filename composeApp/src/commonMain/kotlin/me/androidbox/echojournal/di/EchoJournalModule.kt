package me.androidbox.echojournal.di

import me.androidbox.echojournal.domain.FetchEchoJournalsUseCase
import me.androidbox.echojournal.domain.imp.FetchEchoJournalsUseCaseImp
import me.androidbox.echojournal.presentation.screens.EchoJournalViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val echoJournalModule = module {

    factory<FetchEchoJournalsUseCase> {
        FetchEchoJournalsUseCaseImp()
    }

    viewModel {
        EchoJournalViewModel(get<FetchEchoJournalsUseCase>())
    }
}