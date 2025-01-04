package me.androidbox.echojournal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import me.androidbox.echojournal.presentation.components.ExpandableText

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }
}


@Preview
@Composable
fun ExpandableTextPreview() {
    ExpandableText(description = "This is a long description that might exceed the maximum number of lines and get truncated. In this case, we want to show an ellipsis and a 'Show more' button to expand the text. Long description that might exceed the maximum number of lines and get truncated")
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}