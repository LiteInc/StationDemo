package com.inc.lite.stationdemo.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.inc.lite.stationdemo.ui.theme.MainColor
import com.inc.lite.stationdemo.util.AdjScreenSize


@Composable
fun LoadingIndicator(isLoading: Boolean) {
    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)
    if (isLoading) {
        Box(
            modifier = Modifier
                .wrapContentSize(Alignment.Center)
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .height(size.dp(80))
                    .padding(16.dp),
                color = MainColor
            )
        }
    }
}