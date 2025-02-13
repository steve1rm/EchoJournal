package me.androidbox.echojournal.presentation.models

import androidx.compose.ui.graphics.Color
import echojournal.composeapp.generated.resources.Res
import echojournal.composeapp.generated.resources.excited
import echojournal.composeapp.generated.resources.menu_excited
import echojournal.composeapp.generated.resources.menu_neutral
import echojournal.composeapp.generated.resources.menu_peaceful
import echojournal.composeapp.generated.resources.menu_sad
import echojournal.composeapp.generated.resources.menu_stressed
import echojournal.composeapp.generated.resources.mic
import echojournal.composeapp.generated.resources.neutral
import echojournal.composeapp.generated.resources.pause
import echojournal.composeapp.generated.resources.peaceful
import echojournal.composeapp.generated.resources.sad
import echojournal.composeapp.generated.resources.stop
import echojournal.composeapp.generated.resources.stressed
import echojournal.composeapp.generated.resources.tick
import me.androidbox.echojournal.presentation.models.EmotionMoodsFilled.EXCITED
import me.androidbox.echojournal.presentation.models.EmotionMoodsFilled.NEUTRAL
import me.androidbox.echojournal.presentation.models.EmotionMoodsFilled.PEACEFUL
import me.androidbox.echojournal.presentation.models.EmotionMoodsFilled.SAD
import me.androidbox.echojournal.presentation.models.EmotionMoodsFilled.STRESSED
import org.jetbrains.compose.resources.DrawableResource

data class SelectableTopic(
    val topic: String,
    val isSelected: Boolean = false
)

val emotionList =
    listOf<SelectableEmotion>(
        SelectableEmotion(EmotionMoodsFilled.STRESSED, false),
        SelectableEmotion(EmotionMoodsFilled.SAD, false),
        SelectableEmotion(EmotionMoodsFilled.NEUTRAL, false),
        SelectableEmotion(EmotionMoodsFilled.PEACEFUL, false),
        SelectableEmotion(EmotionMoodsFilled.EXCITED, false)
    )

sealed interface EmotionType {
    val description: String
    val resource: DrawableResource
    val color: Color
        get() {
            return Color.Unspecified
        }
}

data class SelectableEmotion(
    val emotion: EmotionType,
    val isSelected: Boolean = false
)

enum class EmotionMoodsOutlined(override val description: String, override val resource: DrawableResource, override val color: Color) : EmotionType {
    STRESSED("Stressed", Res.drawable.stressed, MoodColorType.RED.color),
    SAD("Sad", Res.drawable.sad, MoodColorType.BLUE.color),
    NEUTRAL("Neutral", Res.drawable.neutral, MoodColorType.GREEN.color),
    PEACEFUL("Peaceful", Res.drawable.peaceful, MoodColorType.PINK.color),
    EXCITED("Excited", Res.drawable.excited, MoodColorType.ORANGE.color)
}

enum class EmotionMoodsFilled(override val description: String, override val resource: DrawableResource, override val color: Color) : EmotionType {
    STRESSED("Stressed", Res.drawable.menu_stressed, MoodColorType.RED.color),
    SAD("Sad", Res.drawable.menu_sad, MoodColorType.BLUE.color),
    NEUTRAL("Neutral", Res.drawable.menu_neutral, MoodColorType.GREEN.color),
    PEACEFUL("Peaceful", Res.drawable.menu_peaceful, MoodColorType.PINK.color),
    EXCITED("Excited", Res.drawable.menu_excited, MoodColorType.ORANGE.color)
}

fun getEmotionMoodsFilled(name: String) : EmotionMoodsFilled {
    return when(name) {
        STRESSED.name -> STRESSED
        SAD.name -> SAD
        NEUTRAL.name -> NEUTRAL
        PEACEFUL.name -> PEACEFUL
        EXCITED.name -> EXCITED
        else -> NEUTRAL
    }
}

enum class MoodColorType(val mood: String, val color: Color) {
    RED("Stressed", Color(0xffED3A3A)),
    BLUE("Sad", Color(0xff2993F7)),
    GREEN("Neutral", Color(0xff71EBAC)),
    PINK("Peaceful", Color( 0xffF991E0)),
    ORANGE("Excited", Color(0xffF6B01A))
}

enum class AudioControl(val recordingStateIcon: DrawableResource, pausedStateIcon: DrawableResource) {
    RECORDING(recordingStateIcon = Res.drawable.tick, pausedStateIcon = Res.drawable.pause),
    PAUSED(recordingStateIcon = Res.drawable.mic, pausedStateIcon = Res.drawable.stop),
}
