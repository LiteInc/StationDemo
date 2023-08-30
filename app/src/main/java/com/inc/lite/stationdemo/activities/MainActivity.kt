package com.inc.lite.stationdemo.activities

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.inc.lite.stationdemo.ui.navigation.HomeNavGraph
import com.inc.lite.stationdemo.ui.theme.StationLiteTheme
import com.inc.lite.stationdemo.util.FullScreenMode
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        setContent {
            StationLiteTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navHostController = rememberNavController()
                    HomeNavGraph(navHostController = navHostController)
                }
            }
        }
        FullScreenMode().hideSystemUI(window)
        val rootView = window.decorView
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            val rect = Rect()
            rootView.getWindowVisibleDisplayFrame(rect)
            val screenHeight = rootView.height
            val keypadHeight = screenHeight - rect.bottom

            if (keypadHeight > screenHeight * 0.15) {
                // Keyboard is open
            } else {
                rootView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                FullScreenMode().hideSystemUI(window)
                // Keyboard is closed
                // Here you can hide the navigation bar if needed
            }
        }
        rootView.viewTreeObserver.addOnGlobalLayoutListener(listener)
        FullScreenMode().hideSystemUI(window)
    }
}

