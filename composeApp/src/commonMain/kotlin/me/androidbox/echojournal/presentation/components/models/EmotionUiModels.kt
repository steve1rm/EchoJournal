package me.androidbox.echojournal.presentation.components.models

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
}

data class SelectableEmotion(
    val emotion: EmotionType,
    val isSelected: Boolean = false
)

enum class EmotionBottomSheet(override val description: String, override val resource: DrawableResource) : EmotionType {
    STRESSED("Stressed", Res.drawable.stressed),
    SAD("Sad", Res.drawable.sad),
    NEUTRAL("Neutral", Res.drawable.neutral),
    PEACEFUL("Peaceful", Res.drawable.peaceful),
    EXCITED("Excited", Res.drawable.excited)
}

enum class EmotionDropDown(override val description: String, override val resource: DrawableResource) : EmotionType {
    STRESSED("Stressed", Res.drawable.menu_stressed),
    SAD("Sad", Res.drawable.menu_sad),
    NEUTRAL("Neutral", Res.drawable.menu_neutral),
    PEACEFUL("Peaceful", Res.drawable.menu_peaceful),
    EXCITED("Excited", Res.drawable.menu_excited)
}
