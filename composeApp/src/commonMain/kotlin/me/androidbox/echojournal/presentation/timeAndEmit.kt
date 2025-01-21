package me.androidbox.echojournal.presentation

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.Clock
import kotlin.math.roundToLong

fun timeAndEmit(emissionsPerSecond: Float) : Flow<Long> {
    return flow {
        val startTime = Clock.System.now().toEpochMilliseconds()
        var lastEmitTime = startTime
        emit(0L)

        while(true) {
            delay((1_000L / emissionsPerSecond).roundToLong())

            val currentTime = Clock.System.now().toEpochMilliseconds()
            val elapsedTime = currentTime - startTime

            emit(elapsedTime)
            lastEmitTime = currentTime
        }
    }
}

fun timeAndEmitPlayback(emissionsPerSecond: Float, playbackDuration: Long): Flow<Long> {
    return flow {
        val startTime = Clock.System.now().toEpochMilliseconds()
        emit(0L)

        while (true) {
            delay((1_000L / emissionsPerSecond).roundToLong())
            val currentTime = Clock.System.now().toEpochMilliseconds()
            val elapsedTime = currentTime - startTime

            if (elapsedTime >= playbackDuration) {
                emit(playbackDuration) // Emit the final value
                break // Stop the loop
            }

            emit(elapsedTime)
        }
    }
}

