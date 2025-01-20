package me.androidbox.echojournal.domain

interface FetchTopicsWithPrefixUseCase {
    suspend fun execute(prefix: String): Result<List<String>>
}