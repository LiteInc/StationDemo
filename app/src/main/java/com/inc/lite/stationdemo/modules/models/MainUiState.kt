package com.inc.lite.stationdemo.modules.models

import androidx.compose.ui.graphics.Color
import com.inc.lite.stationdemo.modules.theme.MainColor
import com.inc.lite.stationdemo.util.AdsTimer


data class MainUiState(
    val stationID: Long = 86546307,
    val stationQR: String = "http://www.riisu.co/rent?now=86546307",
    val startButtonString: String = "Start",
    val startButtonColor: Color = MainColor,
    val statusUiState: StatusBarUiState = StatusBarUiState(),
    val onAppPreviewClick: () -> Unit = {},
    val onStartClick: () -> Unit = {},
    val adsLayout: AdsLayouts = AdsLayouts.SingleImage,
    val isShowQrAdd: Boolean = false
)
