package me.androidbox.echojournal.di

import dev.icerock.moko.permissions.PermissionsController
import me.androidbox.echojournal.data.EchoJournalDataSource
import me.androidbox.echojournal.data.EchoJournalDataSourceImp
import me.androidbox.echojournal.data.EchoJournalDatabase
import me.androidbox.echojournal.domain.CreateJournalUseCase
import me.androidbox.echojournal.domain.CreateTopicUseCase
import me.androidbox.echojournal.domain.FetchEchoJournalsUseCase
import me.androidbox.echojournal.domain.FetchTopicsUseCase
import me.androidbox.echojournal.domain.imp.CreateJournalUseCaseImp
import me.androidbox.echojournal.domain.imp.CreateTopicUseCaseImp
import me.androidbox.echojournal.domain.imp.FetchEchoJournalsUseCaseImp
import me.androidbox.echojournal.domain.imp.FetchTopicsUseCaseImp
import me.androidbox.echojournal.presentation.screens.EchoJournalViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val echoJournalModule = module {

    factory<FetchEchoJournalsUseCase> {
        FetchEchoJournalsUseCaseImp(
            get<EchoJournalDataSource>()
        )
    }

    factory<CreateJournalUseCase> {
        CreateJournalUseCaseImp(
            get<EchoJournalDataSource>()
        )
    }

    factory<FetchTopicsUseCase> {
        FetchTopicsUseCaseImp(
            get<EchoJournalDataSource>()
        )
    }

    factory<CreateTopicUseCase> {
        CreateTopicUseCaseImp(
            get<EchoJournalDataSource>()
        )
    }

    factory<EchoJournalDataSource> {
        EchoJournalDataSourceImp(
            get<EchoJournalDatabase>()
        )
    }

    viewModel {
        EchoJournalViewModel(
            get<FetchEchoJournalsUseCase>(),
            get<CreateJournalUseCase>(),
            get<FetchTopicsUseCase>(),
            get<CreateTopicUseCase>(),
            permissionsController = get<PermissionsController>()
        )
    }
}