package com.inc.lite.stationdemo.modules.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.inc.lite.stationdemo.R
import com.inc.lite.stationdemo.modules.ui.models.MainUiState
import com.inc.lite.stationdemo.modules.ui.models.StatusBarUiState



@Composable
fun StatusBar(
    uiState: StatusBarUiState,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.White,
    contentColor: Color = Color.Black,
    backgroundAlpha: Float = 1f
) {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp + 72

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height((screenHeight/27.23).dp)
            .background(backgroundColor.copy(alpha = backgroundAlpha))
            .padding(horizontal = (screenHeight/106.6).dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("ID: ${uiState.stationID}", fontSize = (screenHeight/106.6).sp, color = contentColor)
            Icon(
                painter = painterResource(id = R.drawable.network_state),
                contentDescription = "Network state",
                modifier = Modifier.height((screenHeight/80).dp),
                tint = contentColor
            )
        }
        Text(uiState.titleText, fontSize = (screenHeight/106.6).sp, color = contentColor)

    }
}