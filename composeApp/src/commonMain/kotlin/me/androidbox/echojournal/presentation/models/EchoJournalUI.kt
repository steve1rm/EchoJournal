package me.androidbox.echojournal.presentation.models

import kotlinx.datetime.Clock

data class EchoJournalUI(
    val title: String,
    val description: String,
    val date: Long,
    val audioFilePath: String,
    val emotion: EmotionMoodsFilled
)

/** Testing data */
fun populate(): Result<List<EchoJournalUI>> {
    val listOfJournals = listOf(
        EchoJournalUI(
        title = "Holiday",
        description = "Planning a trip to Hong Kong",
        date = Clock.System.now().toEpochMilliseconds(),
        audioFilePath = "Path used for content resolver",
        emotion = EmotionMoodsFilled.EXCITED
    ),
        EchoJournalUI(
            title = "Going to work",
            description = "Planning a trip to Hong Kong",
            date = Clock.System.now().toEpochMilliseconds(),
            audioFilePath = "Path used for content resolver",
            emotion = EmotionMoodsFilled.EXCITED
        ),
        EchoJournalUI(
            title = "Getting Coffee",
            description = "Planning a trip to Hong Kong",
            date = Clock.System.now().toEpochMilliseconds(),
            audioFilePath = "Path used for content resolver",
            emotion = EmotionMoodsFilled.EXCITED
        ),
        EchoJournalUI(
            title = "Writing code",
            description = "Planning a trip to Hong Kong",
            date = Clock.System.now().toEpochMilliseconds(),
            audioFilePath = "Path used for content resolver",
            emotion = EmotionMoodsFilled.EXCITED
        ),
        EchoJournalUI(
            title = "Unit testing",
            description = "Planning a trip to Hong Kong",
            date = Clock.System.now().toEpochMilliseconds(),
            audioFilePath = "Path used for content resolver",
            emotion = EmotionMoodsFilled.EXCITED
        ),
        EchoJournalUI(
            title = "Traveling to the south",
            description = "Planning a trip to Hong Kong",
            date = Clock.System.now().toEpochMilliseconds(),
            audioFilePath = "Path used for content resolver",
            emotion = EmotionMoodsFilled.EXCITED
        ),
        EchoJournalUI(
            title = "Buy new Dell",
            description = "Planning a trip to Hong Kong",
            date = Clock.System.now().toEpochMilliseconds(),
            audioFilePath = "Path used for content resolver",
            emotion = EmotionMoodsFilled.EXCITED
        ))

    return Result.success(listOfJournals)
}
