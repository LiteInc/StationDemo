package com.inc.lite.stationdemo.modules.ui.models

import androidx.compose.ui.graphics.Color
import com.inc.lite.stationdemo.modules.ui.theme.MainColor


data class MainUiState(
    val stationID: Long = 86546307,
    val stationQR: String = "http://www.riisu.co/rent?now=86546307",
    val title: String = "時裝時裝",
    val startButtonString: String = "開始點餐",
    val startButtonColor: Color = MainColor,
    val statusUiState: StatusBarUiState = StatusBarUiState(),
    val onAppPreviewClick: () -> Unit = {},
    val onStartClick: () -> Unit = {},
)
