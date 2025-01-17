package me.androidbox.echojournal.domain

sealed interface AudioRecorder {
    fun startRecording()
    fun stopRecording()
}