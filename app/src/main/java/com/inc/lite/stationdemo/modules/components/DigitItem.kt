package com.inc.lite.stationdemo.modules.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.inc.lite.stationdemo.modules.theme.LightGrayColor
import com.inc.lite.stationdemo.util.AdjScreenSize


@Composable
fun DigitItem(
    modifier: Modifier = Modifier,
    digit: String,
    width: Int = 36
){
    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)

    Box(
        modifier = modifier.width(size.dp(width))
    ){
        Column {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = size.dp(5)),
                text = digit,
                fontSize = size.sp(28),
                textAlign = TextAlign.Center
            )
            Spacer(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(LightGrayColor)
            )
        }
    }
}