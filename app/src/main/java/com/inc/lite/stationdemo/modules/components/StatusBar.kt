package com.inc.lite.stationdemo.modules.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import com.inc.lite.stationdemo.R
import com.inc.lite.stationdemo.modules.models.StatusBarUiState
import com.inc.lite.stationdemo.util.AdjScreenSize


@Composable
fun StatusBar(
    uiState: StatusBarUiState,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.White,
    contentColor: Color = Color.Black,
    backgroundAlpha: Float = 1f
) {

    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(size.dp(47))
            .background(backgroundColor.copy(alpha = backgroundAlpha))
            .padding(horizontal = (size.dp(12))),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("ID: ${uiState.stationID}", fontSize = size.sp(12), color = contentColor)
            Icon(
                painter = painterResource(id = R.drawable.network_state),
                contentDescription = "Network state",
                modifier = Modifier.height(size.dp(16)),
                tint = contentColor
            )
        }
        Text(uiState.titleText, fontSize = size.sp(12), color = contentColor)

    }
}