package com.mukesh.markdownview.example

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mukesh.MarkDown
import com.mukesh.otpview_example.ui.theme.ComposeTheme
import java.io.File
import java.net.URL

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    // Display Markdown via Text
//                    MarkDown(
//                        text = "# Test Markdown",
//                        modifier = Modifier.fillMaxSize()
//                    )
                    // Display markdown via File
//                    MarkDown(
//                        file = getFileFromAssets(
//                            context = this@MainActivity,
//                            fileName = "Sample.md"
//                        ),
//                        modifier = Modifier.fillMaxSize()
//                    )

                    // Display markdown via URL
                    MarkDown(
                        url = URL("https://raw.githubusercontent.com/mukeshsolanki/MarkdownView-Android/main/README.md"),
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

fun getFileFromAssets(context: Context, fileName: String): File = File(context.cacheDir, fileName)
    .also {
        if (!it.exists()) {
            it.outputStream().use { cache ->
                context.assets.open(fileName).use { inputStream ->
                    inputStream.copyTo(cache)
                }
            }
        }
    }