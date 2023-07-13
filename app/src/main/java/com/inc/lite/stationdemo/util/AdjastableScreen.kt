package com.inc.lite.stationdemo.util

import android.content.res.Configuration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.TextUnit

class AdjScreenSize(
    val configuration: Configuration,
) {
    val screenHeight = configuration.screenHeightDp + 72

    fun dp(size: Int): Dp{
        val div = 1280.0f / size.toFloat()
        val result = screenHeight.toFloat() / div
        return result.dp
    }
    fun sp(size: Int):TextUnit{
        val div = 1280.0f / size.toFloat()
        val result = screenHeight.toFloat() / div
        return result.sp
    }

}