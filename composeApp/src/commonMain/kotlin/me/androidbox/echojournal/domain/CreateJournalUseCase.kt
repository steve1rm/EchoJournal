package me.androidbox.echojournal.domain

import me.androidbox.echojournal.data.Journal

interface CreateJournalUseCase {
    suspend fun execute(journal: Journal): Result<Unit>
}