package com.inc.lite.stationdemo.modules.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import com.inc.lite.stationdemo.modules.theme.GrayDotColor
import com.inc.lite.stationdemo.util.AdjScreenSize

@Preview
@Composable
fun PasswordItem(
    modifier: Modifier = Modifier,
    digit: String = "1",
    width: Int = 84,
    color: Color = GrayDotColor
){
    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)

    Box(
        modifier = modifier.width(size.dp(width)),
        contentAlignment = Alignment.Center
    ){
        Spacer(
            modifier = Modifier
                .height(size.dp(20))
                .width(size.dp(20))
                .clip(CircleShape)
                .background(color)
        )

    }
}