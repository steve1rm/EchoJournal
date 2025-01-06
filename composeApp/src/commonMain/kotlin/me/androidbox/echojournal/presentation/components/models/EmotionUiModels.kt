package me.androidbox.echojournal.presentation.components.models

import androidx.compose.ui.graphics.Color
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

enum class EmotionBottomSheet(override val description: String, override val resource: DrawableResource, override val color: Color) : EmotionType {
    STRESSED("Stressed", Res.drawable.stressed, Color(0xffED3A3A)),
    SAD("Sad", Res.drawable.sad, Color(0xff2993F7)),
    NEUTRAL("Neutral", Res.drawable.neutral, Color(0xff71EBAC)),
    PEACEFUL("Peaceful", Res.drawable.peaceful, Color(0xffF991E0)),
    EXCITED("Excited", Res.drawable.excited, Color(0xffF6B01A))
}

enum class EmotionDropDown(override val description: String, override val resource: DrawableResource) : EmotionType {
    STRESSED("Stressed", Res.drawable.menu_stressed),
    SAD("Sad", Res.drawable.menu_sad),
    NEUTRAL("Neutral", Res.drawable.menu_neutral),
    PEACEFUL("Peaceful", Res.drawable.menu_peaceful),
    EXCITED("Excited", Res.drawable.menu_excited)
}
