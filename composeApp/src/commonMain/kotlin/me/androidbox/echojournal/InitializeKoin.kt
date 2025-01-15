package me.androidbox.echojournal

import me.androidbox.echojournal.di.echoJournalModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

fun initializeKoin(koinConfig: KoinAppDeclaration? = null, vararg platformSpecificModules: Module = emptyArray()) {

    startKoin {
        koinConfig?.invoke(this@startKoin)

        this.modules(
            echoJournalModule,
            *platformSpecificModules
        )
    }
}