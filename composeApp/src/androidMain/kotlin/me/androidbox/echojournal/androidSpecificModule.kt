package me.androidbox.echojournal

import androidx.room.Room
import androidx.sqlite.driver.AndroidSQLiteDriver
import dev.icerock.moko.permissions.PermissionsController
import me.androidbox.echojournal.data.EchoJournalDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidSpecificModule = module {
    single<PermissionsController> {
        PermissionsController(androidContext())
    }

    single<EchoJournalDatabase> {
        val dbFile = androidContext().getDatabasePath("echoJournal.db")

        Room.databaseBuilder<EchoJournalDatabase>(
            context = androidContext(),
            name = dbFile.absolutePath)
            .setDriver(AndroidSQLiteDriver())
            .build()
    }
}
