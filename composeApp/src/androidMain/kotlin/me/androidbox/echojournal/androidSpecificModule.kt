package me.androidbox.echojournal

import dev.icerock.moko.permissions.PermissionsController
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidSpecificModule = module {
    single<PermissionsController> {
        PermissionsController(androidContext())
    }
}
