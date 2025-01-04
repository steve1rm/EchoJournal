package me.androidbox.echojournal.presentation.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.LinkAnnotation.Clickable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp


@Composable
fun ExpandableText(
    modifier: Modifier = Modifier,
    fontStyle: FontStyle? = null,
    description: String,
    collapsedMaxLine: Int = 3,
    showMoreText: String = "... Show More",
    showMoreStyle: SpanStyle = SpanStyle(fontWeight = FontWeight.SemiBold, color = Color.Blue),
    showLessStyle: SpanStyle = showMoreStyle,
    showLessText: String = " Show Less",
    fontSize: TextUnit = 18.sp
) {
    // State variables to track the expanded state, clickable state, and last character index.
    var isExpanded by remember { mutableStateOf(false) }
    var isClickable by remember { mutableStateOf(false) }
    var lastCharIndex by remember { mutableStateOf(0) }

    val annotatedText = buildAnnotatedString {

        if (isClickable) {
            if(isExpanded) {
                this.append(description)
                this.withLink(
                    link = Clickable(
                        tag = "show less",
                        linkInteractionListener = {
                            isExpanded = !isExpanded
                        }
                    )
                ) {
                    this.withStyle(style = showLessStyle) {
                        this.append(showLessText)
                    }
                }
            }
            else {
                val adjustedText = description.substring(startIndex = 0, endIndex = lastCharIndex)
                    .dropLast(showMoreText.length)
                    .dropLastWhile { it.isWhitespace() || it == '.' }

                this.append(adjustedText)

                this.withLink(
                    link = Clickable(
                        tag = "show more",
                        linkInteractionListener = {
                            isExpanded = !isExpanded
                        }
                    )
                ) {
                    this.withStyle(style = showMoreStyle) {
                        this.append(showMoreText)
                    }
                }
            }
        } else {
            this.append(description)
        }
    }

    Text(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(),
        text = annotatedText,
        maxLines = if (isExpanded) Int.MAX_VALUE else collapsedMaxLine,
        fontStyle = fontStyle,
        onTextLayout = { textLayoutResult ->
            if (!isExpanded && textLayoutResult.hasVisualOverflow) {
                isClickable = true
                lastCharIndex = textLayoutResult.getLineEnd(collapsedMaxLine - 1)
            }
        },
        fontSize = fontSize
    )
}
