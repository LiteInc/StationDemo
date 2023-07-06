package com.inc.lite.stationdemo.modules.ui.screens


import android.annotation.SuppressLint
import android.graphics.Rect
import android.view.ViewTreeObserver
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.inc.lite.stationdemo.modules.ui.components.BottomBar
import com.inc.lite.stationdemo.modules.ui.components.StatusBar
import com.inc.lite.stationdemo.modules.ui.components.TopBar
import com.inc.lite.stationdemo.modules.ui.components.WebViewComponent
import com.inc.lite.stationdemo.modules.ui.models.StatusBarUiState


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WebViewScreen(
    navHostController: NavHostController,
    url: String = "https://www.google.com.tw/?hl=zh_TW"
) {

    val isKeyboardOpen by keyboardAsState()

    val animatedWebView by animateIntAsState(targetValue = if (isKeyboardOpen == Keyboard.Opened) 700 else 959)
//    val animatedSpacer by animateFloatAsState(targetValue = if (isKeyboardOpen == Keyboard.Opened) 0.273f else 0.001f)
    val animatedSpacer2 by animateIntAsState(targetValue = if (isKeyboardOpen == Keyboard.Opened) 350 else 0)


//    Column(
//        Modifier.fillMaxSize()
//    ) {
//        StatusBar(modifier = Modifier.weight(0.056f), uiState = StatusBarUiState())
//        TopBar(modifier = Modifier.weight(0.06f),onBackArrowClick = { navHostController.popBackStack() })
//        WebViewComponent(modifier = Modifier.weight(animatedWebView), url = url)
////        Text(text = "$isKeyboardOpen")
//        BottomBar(modifier = Modifier.weight(0.157f))
//        Spacer(modifier = Modifier.weight(animatedSpacer))
//    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Column(Modifier.height(149.dp)) {
                StatusBar(uiState = StatusBarUiState())
                TopBar(modifier = Modifier,onBackArrowClick = { navHostController.popBackStack() })
            }
//            Text(text = "$isKeyboardOpen")
        },
        bottomBar = {
            BottomBar(Modifier.padding(bottom = animatedSpacer2.dp))
//            Spacer(modifier = Modifier.height(animatedSpacer2.dp))
        },
    ) {
        WebViewComponent(modifier = Modifier.height(animatedWebView.dp).padding(top = 149.dp),url = url)
    }
}


enum class Keyboard {
    Opened, Closed
}

@Composable
fun keyboardAsState(): State<Keyboard> {
    val keyboardState = remember { mutableStateOf(Keyboard.Closed) }
    val view = LocalView.current
    DisposableEffect(view) {
        val onGlobalListener = ViewTreeObserver.OnGlobalLayoutListener {
            val rect = Rect()
            view.getWindowVisibleDisplayFrame(rect)
            val screenHeight = view.rootView.height
            val keypadHeight = screenHeight - rect.bottom
            keyboardState.value = if (keypadHeight > screenHeight * 0.15) {
                Keyboard.Opened
            } else {
                Keyboard.Closed
            }
        }
        view.viewTreeObserver.addOnGlobalLayoutListener(onGlobalListener)

        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(onGlobalListener)
        }
    }

    return keyboardState
}