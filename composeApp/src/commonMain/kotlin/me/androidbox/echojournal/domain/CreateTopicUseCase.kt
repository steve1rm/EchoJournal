package me.androidbox.echojournal.domain

interface CreateTopicUseCase {
    suspend fun execute(topic: String): Result<Unit>
}