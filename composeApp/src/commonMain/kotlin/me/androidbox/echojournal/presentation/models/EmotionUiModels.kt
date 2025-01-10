package me.androidbox.echojournal.presentation.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import echojournal.composeapp.generated.resources.Res
import echojournal.composeapp.generated.resources.excited
import echojournal.composeapp.generated.resources.menu_excited
import echojournal.composeapp.generated.resources.menu_neutral
import echojournal.composeapp.generated.resources.menu_peaceful
import echojournal.composeapp.generated.resources.menu_sad
import echojournal.composeapp.generated.resources.menu_stressed
import echojournal.composeapp.generated.resources.neutral
import echojournal.composeapp.generated.resources.peaceful
import echojournal.composeapp.generated.resources.sad
import echojournal.composeapp.generated.resources.stressed
import org.jetbrains.compose.resources.DrawableResource


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

enum class EmotionMoodsFilled(override val description: String, override val resource: DrawableResource) : EmotionType {
    STRESSED("Stressed", Res.drawable.menu_stressed),
    SAD("Sad", Res.drawable.menu_sad),
    NEUTRAL("Neutral", Res.drawable.menu_neutral),
    PEACEFUL("Peaceful", Res.drawable.menu_peaceful),
    EXCITED("Excited", Res.drawable.menu_excited)
}

enum class MoodColorType(val mood: String, val color: Color) {
    RED("Stressed", Color(0xffED3A3A)),
    BLUE("Sad", Color(0xff2993F7)),
    GREEN("Neutral", Color(0xff71EBAC)),
    PINK("Peaceful", Color( 0xffF991E0)),
    ORANGE("Excited", Color(0xffF6B01A))
}

enum class AudioControl(val icon: ImageVector) {
    PLAY(Icons.Default.PlayArrow),
    STOP(Icons.Default.PlayArrow),
    PAUSE(Icons.Default.PlayArrow)
}
