package me.androidbox.echojournal.domain

import me.androidbox.echojournal.presentation.models.SelectableTopic

interface FetchTopicsUseCase {
    suspend fun execute(): Result<List<SelectableTopic>>
}