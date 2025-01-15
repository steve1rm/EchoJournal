package me.androidbox.echojournal

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class EchoJournalApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        initializeKoin(
            koinConfig = {
                androidContext(this@EchoJournalApplication)
                androidLogger(Level.DEBUG)
            },
            androidSpecificModule
        )
    }
}