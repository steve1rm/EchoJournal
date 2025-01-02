package me.androidbox.echojournal

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform