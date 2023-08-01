package com.inc.lite.stationdemo.ui.components

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun WebViewComponent(
    modifier: Modifier = Modifier,
    url: String
) {
    AndroidView(
        modifier = modifier.fillMaxWidth(),
        factory = { context ->

        WebView(context).apply {
            settings.apply {
                // Enable JavaScript
                javaScriptEnabled = true

//
//                // Set the user agent string to simulate a desktop browser
                userAgentString = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36"
//
//                // Adjust other settings as needed
//                // For example, you can enable built-in zoom controls or set the initial scale
                builtInZoomControls = true
                displayZoomControls = false
                setSupportZoom(true)
                useWideViewPort = true
                loadWithOverviewMode = true
            }
            webViewClient = WebViewClient()
            loadUrl(url)
        }
    })
}