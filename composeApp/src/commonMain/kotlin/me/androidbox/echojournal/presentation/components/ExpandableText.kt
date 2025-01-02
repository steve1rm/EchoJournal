package me.androidbox.echojournal.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

@Composable
fun ExpandableText(description: String) {
    var isExpanded by remember { mutableStateOf(false) }
    var hasOverflow by remember { mutableStateOf(false) }
    val maxLines = if (isExpanded) Int.MAX_VALUE else 3

    // Use AnnotatedString to handle clickable parts
    val annotatedString = buildAnnotatedString {
        append(description)
        if (hasOverflow && !isExpanded) {
            append("... ")
            pushStringAnnotation(tag = "SHOW_MORE", annotation = "show_more")
            withStyle(SpanStyle(color = Color.Blue, fontWeight = FontWeight.Bold)) {
                append("show more")
            }
            pop()
        } else if (isExpanded) {
            append(" ")
            pushStringAnnotation(tag = "SHOW_LESS", annotation = "show_less")
            withStyle(SpanStyle(color = Color.Blue, fontWeight = FontWeight.Bold)) {
                append("show less")
            }
            pop()
        }
    }

    Box(modifier = Modifier.fillMaxWidth()) {
        ClickableText(
            text = annotatedString,
            style = TextStyle(
                fontWeight = FontWeight.Medium,
                color = Color.Gray,
                fontSize = 18.sp
            ),
            maxLines = maxLines,
            overflow = TextOverflow.Ellipsis,
            onTextLayout = { textLayoutResult ->
                hasOverflow = textLayoutResult.hasVisualOverflow
            },
            onClick = { offset ->
                annotatedString.getStringAnnotations(
                    tag = "SHOW_MORE",
                    start = offset,
                    end = offset
                ).firstOrNull()?.let {
                    isExpanded = true
                }

                annotatedString.getStringAnnotations(
                    tag = "SHOW_LESS",
                    start = offset,
                    end = offset
                ).firstOrNull()?.let {
                    isExpanded = false
                }
            }
        )
    }
}
