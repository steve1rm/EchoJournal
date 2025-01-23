package me.androidbox.echojournal.presentation.models

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlin.time.Duration.Companion.days

data class EchoJournalUI(
    val title: String,
    val description: String,
    val date: Long,
    val audioFilePath: String,
    val topics: List<String>,
    val emotion: EmotionMoodsFilled,
    val audioDuration: Long
)

/*fun subtractDay(timestamp: Long, day: Int): Long {
    val instant = Instant.fromEpochMilliseconds(timestamp)
    val oneDayAgoInstant = instant.minus(day.days)
    return oneDayAgoInstant.toEpochMilliseconds()
}*/

/*

*/
/** Testing data *//*

fun populate(): Result<List<EchoJournalUI>> {

    println("POPULATE")

    val listOfJournals = listOf(
        EchoJournalUI(
            title = "Holiday",
            description = "Planning a trip to Hong Kong Planning a trip to Hong KongPlanning a trip to Hong KongPlanning a trip to Hong KongPlanning a trip to Hong KongPlanning a trip to Hong KongPlanning a trip to Hong Kong Planning a trip to Hong KongPlanning a trip to Hong KongPlanning a trip to Hong KongPlanning a trip to Hong KongPlanning a trip to Hong KongPlanning a trip to Hong Kong",
            date = Clock.System.now().toEpochMilliseconds(),
            audioFilePath = "Path used for content resolver",
            topics = listOf("work", "flight", "busy", "jobs", "phone"),
            emotion = EmotionMoodsFilled.EXCITED
        ),
        EchoJournalUI(
            title = "Going to work",
            description = "Planning a trip to Hong Kong",
            date = Clock.System.now().toEpochMilliseconds(),
            audioFilePath = "Path used for content resolver",
            topics = listOf("work", "flight", "busy", "jobs", "phone"),
            emotion = EmotionMoodsFilled.PEACEFUL
        ),
        EchoJournalUI(
            title = "Getting Coffee",
            description = "Planning a trip to Hong Kong Planning a trip to Hong KongPlanning a trip to Hong KongPlanning a trip to Hong KongPlanning a trip to Hong KongPlanning a trip to Hong KongPlanning a trip to Hong Kong",
            date = Clock.System.now().toEpochMilliseconds(),
            audioFilePath = "Path used for content resolver",
            topics = listOf("work", "flight", "busy", "jobs", "phone"),
            emotion = EmotionMoodsFilled.STRESSED
        ),
        EchoJournalUI(
            title = "Writing code",
            description = "Planning a trip to Hong Kong",
            date = subtractDay(Clock.System.now().toEpochMilliseconds(), 1),
            audioFilePath = "Path used for content resolver",
            topics = listOf("work", "flight", "busy", "jobs", "phone"),
            emotion = EmotionMoodsFilled.SAD
        ),
        EchoJournalUI(
            title = "Unit testing",
            description = "Planning a trip to Hong Kong",
            date = subtractDay(Clock.System.now().toEpochMilliseconds(), 1),
            audioFilePath = "Path used for content resolver",
            topics = listOf("work", "flight", "busy", "jobs", "phone"),
            emotion = EmotionMoodsFilled.NEUTRAL
        ),
        EchoJournalUI(
            title = "Traveling to the south",
            description = "Planning a trip to Hong Kong",
            date = subtractDay(Clock.System.now().toEpochMilliseconds(), 2),
            audioFilePath = "Path used for content resolver",
            topics = listOf("work", "flight", "busy", "jobs", "phone"),
            emotion = EmotionMoodsFilled.NEUTRAL
        ),
        EchoJournalUI(
            title = "Buy new Dell",
            description = "Planning a trip to Hong KongPlanning a trip to Hong KongPlanning a trip to Hong KongPlanning a trip to Hong KongPlanning a trip to Hong KongPlanning a trip to Hong Kong",
            date = subtractDay(Clock.System.now().toEpochMilliseconds(), 3),
            audioFilePath = "Path used for content resolver",
            topics = listOf("work", "flight", "busy", "jobs", "phone"),
            emotion = EmotionMoodsFilled.PEACEFUL
        ),
        EchoJournalUI(
            title = "Working on moving location",
            description = "Lets get this done today, and move all our stuff to another property. Lets Go...",
            date = subtractDay(Clock.System.now().toEpochMilliseconds(), 4),
            audioFilePath = "Path used for content resolver",
            topics = listOf("work", "flight", "busy", "jobs", "phone"),
            emotion = EmotionMoodsFilled.PEACEFUL
        ),
        EchoJournalUI(
            title = "Coffee with Donuts @ Dunkin Donuts",
            description = "Lets get this party started. Start planning and less day dreaming",
            date = subtractDay(Clock.System.now().toEpochMilliseconds(), 4),
            audioFilePath = "Path used for content resolver",
            topics = listOf("work", "flight", "busy", "jobs", "phone"),
            emotion = EmotionMoodsFilled.SAD
        ),
        EchoJournalUI(
            title = "Relocation is in progress",
            description = "Getting ready with flight and package of suitcases and other things",
            date = subtractDay(Clock.System.now().toEpochMilliseconds(), 4),
            audioFilePath = "Path used for content resolver",
            topics = listOf("work", "flight", "busy", "jobs", "phone"),
            emotion = EmotionMoodsFilled.STRESSED
        ))

    return Result.success(listOfJournals)
}
*/
