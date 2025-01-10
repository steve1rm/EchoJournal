package me.androidbox.echojournal.presentation.models

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlin.time.Duration
import kotlin.time.Duration.Companion.days

data class EchoJournalUI(
    val title: String,
    val description: String,
    val date: Long,
    val audioFilePath: String,
    val topics: List<String>,
    val emotion: EmotionMoodsFilled
)

fun subtractDay(timestamp: Long, day: Int): Long {
    val instant = Instant.fromEpochMilliseconds(timestamp)
    val oneDayAgoInstant = instant.minus(day.days)
    return oneDayAgoInstant.toEpochMilliseconds()
}


/** Testing data */
fun populate(): Result<List<EchoJournalUI>> {
    val listOfJournals = listOf(
        EchoJournalUI(
        title = "Holiday",
        description = "Planning a trip to Hong Kong",
        date = Clock.System.now().toEpochMilliseconds(),
        audioFilePath = "Path used for content resolver",
            topics = listOf(""),
        emotion = EmotionMoodsFilled.EXCITED
    ),
        EchoJournalUI(
            title = "Going to work",
            description = "Planning a trip to Hong Kong",
            date = Clock.System.now().toEpochMilliseconds(),
            audioFilePath = "Path used for content resolver",
            topics = listOf(""),
            emotion = EmotionMoodsFilled.EXCITED
        ),
        EchoJournalUI(
            title = "Getting Coffee",
            description = "Planning a trip to Hong Kong",
            date = Clock.System.now().toEpochMilliseconds(),
            audioFilePath = "Path used for content resolver",
            topics = listOf(""),
            emotion = EmotionMoodsFilled.EXCITED
        ),
        EchoJournalUI(
            title = "Writing code",
            description = "Planning a trip to Hong Kong",
            date = subtractDay(Clock.System.now().toEpochMilliseconds(), 1),
            audioFilePath = "Path used for content resolver",
            topics = listOf(""),
            emotion = EmotionMoodsFilled.EXCITED
        ),
        EchoJournalUI(
            title = "Unit testing",
            description = "Planning a trip to Hong Kong",
            date = subtractDay(Clock.System.now().toEpochMilliseconds(), 1),
            audioFilePath = "Path used for content resolver",
            topics = listOf(""),
            emotion = EmotionMoodsFilled.EXCITED
        ),
        EchoJournalUI(
            title = "Traveling to the south",
            description = "Planning a trip to Hong Kong",
            date = subtractDay(Clock.System.now().toEpochMilliseconds(), 2),
            audioFilePath = "Path used for content resolver",
            topics = listOf(""),
            emotion = EmotionMoodsFilled.EXCITED
        ),
        EchoJournalUI(
            title = "Buy new Dell",
            description = "Planning a trip to Hong Kong",
            date = subtractDay(Clock.System.now().toEpochMilliseconds(), 3),
            audioFilePath = "Path used for content resolver",
            topics = listOf(""),
            emotion = EmotionMoodsFilled.EXCITED
        ))

    return Result.success(listOfJournals)
}
