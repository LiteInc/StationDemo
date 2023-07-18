package com.inc.lite.stationdemo.util

import android.content.res.Configuration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.TextUnit

/**
 * Helper class to adjust screen size calculations based on the given [Configuration].
 * It provides methods to convert pixel values to [Dp] and [TextUnit] based on the screen configuration.
 *
 * @param configuration The [Configuration] object representing the current device configuration.
 *
 * @author Nik4Prod
 */
class AdjScreenSize(
    val configuration: Configuration
) {
    /**
     * The calculated screen height including an additional offset of 72dp.
     */
    val screenHeight: Dp = (configuration.screenHeightDp + 72).dp

    /**
     * The calculated screen width.
     */
    val screenWidth: Dp = configuration.screenWidthDp.dp

    /**
     * Converts a given pixel size to [Dp] based on the screen configuration.
     *
     * @param size The size in pixels to be converted.
     * @return The corresponding size in [Dp].
     */
    fun dp(size: Int): Dp {
        val div = 1280.0f / size.toFloat()
        val result = screenHeight.value / div
        return result.dp
    }

    /**
     * Converts a given pixel size to [TextUnit.Sp] based on the screen configuration.
     *
     * @param size The size in pixels to be converted.
     * @return The corresponding size in [TextUnit.Sp].
     */
    fun sp(size: Int): TextUnit {
        val div = 1280.0f / size.toFloat()
        val result = screenHeight.value / div
        return result.sp
    }
}