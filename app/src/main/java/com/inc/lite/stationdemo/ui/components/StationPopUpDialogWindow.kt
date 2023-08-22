package com.inc.lite.stationdemo.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import com.inc.lite.stationdemo.ui.theme.White
import com.inc.lite.stationdemo.util.AdjScreenSize

@Composable
fun StationPopUpDialogWindow(
    isVisible: Boolean = false
) {
    AnimatedVisibility(visible = isVisible) {
        StationDialogComponent()
    }
}

@Composable
fun StationDialogComponent() {
    val config= LocalConfiguration.current
    val size = AdjScreenSize(config)

    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.21f)),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .height(size.dp(320))
                .fillMaxWidth()
                .background(White),
            contentAlignment = Alignment.Center
        ){

        }
    }
}