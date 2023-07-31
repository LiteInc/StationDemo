package com.inc.lite.stationdemo.model

import androidx.compose.ui.graphics.Color
import com.inc.lite.stationdemo.ui.theme.MainColor


data class MainUiState(
    val stationID: Long = 86546307,
    val stationQR: String = "http://www.riisu.co/rent?now=86546307",
    val startButtonString: String = "開始",
    val startButtonColor: Color = MainColor,
    val statusUiState: StatusBarUiState = StatusBarUiState(),
    val programsList: List<ProgramItem> = emptyList(),
    val onAppPreviewClick: (ProgramItem) -> Unit = {_ ->},
    val onStartClick: () -> Unit = {},
    val ads: AdsUI = AdsUI(),
    val isShowQrAdd: Boolean = false
)
