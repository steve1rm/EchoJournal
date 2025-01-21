package me.androidbox.echojournal.presentation

import androidx.compose.runtime.MutableLongState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlin.math.roundToLong

fun timeAndEmit(emissionsPerSecond: Float): Flow<Long> {
    return flow {
        val startTime = Clock.System.now().toEpochMilliseconds()
        var lastEmitTime = startTime
        emit(0L)

        while (true) {
            delay((1_000L / emissionsPerSecond).roundToLong())

            val currentTime = Clock.System.now().toEpochMilliseconds()
            val elapsedTime = currentTime - startTime

            emit(elapsedTime)
            lastEmitTime = currentTime
        }
    }
}

class TimeAndEmitPlay(
    private val scope: CoroutineScope
) {
    enum class PlayerState {
        IDLE,
        PLAY,
        PAUSE
    }

    var state: MutableState<PlayerState> = mutableStateOf(PlayerState.IDLE)
    val currentTime: MutableLongState = mutableLongStateOf(0L)
    private var playbackDuration: Long = 0L

    fun setDuration(playbackDuration: Long) {
        currentTime.value = 0L
        state.value = PlayerState.IDLE
        this.playbackDuration = playbackDuration
    }

    fun start() {
        state.value = PlayerState.PLAY
        timeAndEmitPlayback(30f)
    }

    fun pause() {
        state.value = PlayerState.PAUSE
    }

    private fun timeAndEmitPlayback(emissionsPerSecond: Float) {
        scope.launch {
            while (state.value == PlayerState.PLAY) {
                delay((1_000L / emissionsPerSecond).roundToLong())
                currentTime.value += (1_000L / emissionsPerSecond).roundToLong()
                println("TIME AND EMIT PLAYING ${currentTime.value}")

                if (currentTime.value >= playbackDuration) {
                    state.value = PlayerState.IDLE
                    currentTime.value = 0L
                    break // Stop the loop
                }
            }
        }
    }
}
