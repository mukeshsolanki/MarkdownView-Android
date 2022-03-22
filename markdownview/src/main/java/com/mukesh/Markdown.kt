package com.mukesh

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Base64
import android.util.Log
import android.view.ViewGroup
import android.webkit.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import java.io.*
import java.net.URL
import java.util.regex.Pattern
import kotlin.concurrent.thread

//region Global Variables
private const val TAG = "MarkDown"
//endregion

//region Components
/**
 * Creates a composable view that can be used to display Markdown text.
 * @param modifier - Modifier used for controlling the view
 * @param text - The text used to generate the Markdown.
 * @param shouldOpenUrlInBrowser - Flag that tells the composable if it needs to open urls in a the browser or in the same view.
 */
@Composable
fun MarkDown(
    modifier: Modifier = Modifier,
    text: String,
    shouldOpenUrlInBrowser: Boolean = true
) {
    val bs64MdText: String = imgToBase64(text)
    val escMdText: String = escapeForText(bs64MdText)
    val previewText = "preview('$escMdText')"
    AndroidView(factory = { context ->
        WebView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    view?.evaluateJavascript(previewText, null)
                }

                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    if (shouldOpenUrlInBrowser) {
                        context.startActivity(Intent(Intent.ACTION_VIEW, request?.url))
                        return true
                    }
                    return false
                }
            }
            loadUrl("file:///android_asset/html/preview.html")
            @SuppressLint("SetJavaScriptEnabled")
            settings.javaScriptEnabled = true
            settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
    }, modifier = modifier)
}

/**
 * Creates a composable view that can be used to display Markdown from a file.
 * @param modifier - Modifier used for controlling the view
 * @param file - The file which is used to get the details to generate the Markdown.
 * @param shouldOpenUrlInBrowser - Flag that tells the composable if it needs to open urls in a the browser or in the same view.
 */
@Composable
fun MarkDown(
    modifier: Modifier = Modifier,
    file: File,
    shouldOpenUrlInBrowser: Boolean = true
) {
    var mdText = ""
    try {
        val fileInputStream = FileInputStream(file)
        val inputStreamReader = InputStreamReader(fileInputStream)
        val bufferedReader = BufferedReader(inputStreamReader)
        var readText: String?
        val stringBuilder = StringBuilder()
        while (bufferedReader.readLine().also { readText = it } != null) {
            stringBuilder.append(readText)
            stringBuilder.append("\n")
        }
        fileInputStream.close()
        mdText = stringBuilder.toString()
    } catch (e: FileNotFoundException) {
        e.message?.let { Log.d(TAG, it) }
    } catch (e: IOException) {
        e.message?.let { Log.d(TAG, it) }
    }
    MarkDown(
        text = mdText,
        modifier = modifier,
        shouldOpenUrlInBrowser = shouldOpenUrlInBrowser
    )
}

/**
 * Creates a composable view that can be used to display Markdown from a url.
 * @param modifier - Modifier used for controlling the view
 * @param url - The url to the Markdown file.
 * @param shouldOpenUrlInBrowser - Flag that tells the composable if it needs to open urls in a the browser or in the same view.
 */
@Composable
fun MarkDown(
    modifier: Modifier = Modifier,
    url: URL,
    shouldOpenUrlInBrowser: Boolean = true
) {
    val urlContent = remember { mutableStateOf("") }
    thread {
        val reader = BufferedReader(InputStreamReader(url.openStream()))
        var line: String
        var mdText = ""
        while (reader.readLine().also { line = it ?: "" } != null) {
            mdText += "$line\n"
        }
        reader.close()
        urlContent.value = mdText
    }
    if (urlContent.value.isNotEmpty()) {
        MarkDown(
            text = urlContent.value,
            modifier = modifier,
            shouldOpenUrlInBrowser = shouldOpenUrlInBrowser
        )
    }
}
//endregion

//region Helper Methods
private fun escapeForText(mdText: String): String {
    var escText = mdText.replace("\n", "\\\\n")
    escText = escText.replace("'", "\\\'")
    escText = escText.replace("\r", "")
    return escText
}

private fun imgToBase64(mdText: String): String {
    val ptn = Pattern.compile("!\\[(.*)\\]\\((.*)\\)")
    val matcher = ptn.matcher(mdText)
    if (!matcher.find()) {
        return mdText
    }
    val imgPath = matcher.group(2)
    imgPath?.let {
        if (isUrlPrefix(imgPath) || !isPathExCheck(imgPath)) {
            return mdText
        }
        val baseType = imgEx2BaseType(imgPath)
        if ("" == baseType) {
            return mdText
        }
        val file = File(imgPath)

        val bytes = ByteArray(file.length().toInt())
        try {
            val buf = BufferedInputStream(FileInputStream(file))
            buf.read(bytes, 0, bytes.size)
            buf.close()
        } catch (e: FileNotFoundException) {
            e.message?.let { Log.d(TAG, it) }
        } catch (e: IOException) {
            e.message?.let { Log.d(TAG, it) }
        }
        val base64Img = baseType + Base64.encodeToString(bytes, Base64.NO_WRAP)
        return mdText.replace(imgPath, base64Img)
    }
    return ""
}

private fun isUrlPrefix(text: String): Boolean {
    return text.startsWith("http://") || text.startsWith("https://")
}

private fun isPathExCheck(text: String): Boolean {
    return (text.endsWith(".png")
            || text.endsWith(".jpg")
            || text.endsWith(".jpeg")
            || text.endsWith(".gif"))
}

private fun imgEx2BaseType(text: String): String {
    return if (text.endsWith(".png")) {
        "data:image/png;base64,"
    } else if (text.endsWith(".jpg") || text.endsWith(".jpeg")) {
        "data:image/jpg;base64,"
    } else if (text.endsWith(".gif")) {
        "data:image/gif;base64,"
    } else {
        ""
    }
}
//endregion