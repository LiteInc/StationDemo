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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
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
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(47.dp)
            .background(backgroundColor.copy(alpha = backgroundAlpha))
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("ID: ${uiState.stationID}", fontSize = 12.sp, color = contentColor)
            Image(
                painter = painterResource(id = R.drawable.network_state),
                contentDescription = "Network state",
                modifier = Modifier.height(16.dp)
            )
        }
        Text(uiState.titleText, fontSize = 12.sp, color = contentColor)

    }
}