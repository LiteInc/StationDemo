package com.inc.lite.stationdemo.model.uiState

import androidx.compose.ui.graphics.Color
import com.inc.lite.stationdemo.R
import com.inc.lite.stationdemo.model.AdsUI
import com.inc.lite.stationdemo.model.ProgramItem
import com.inc.lite.stationdemo.model.StatusBarUiState
import com.inc.lite.stationdemo.ui.theme.MainColor


data class MainUiState(
    val stationID: Long = 86546307,
    val stationQR: String = "http://www.riisu.co/rent?now=86546307",
    val startButtonString: String = "開始",
    val startButtonColor: Color = MainColor,
    val statusUiState: StatusBarUiState = StatusBarUiState(),
    val programsList: List<ProgramItem> = listOf(
        ProgramItem(
            title = "地圖",
            link = "https://www.google.com/maps/@23.4857501,120.0843006,7z?entry=ttu",
            logo = R.drawable.gmap
        ),
            ProgramItem(
            title = "天氣",
            link = "https://www.cwb.gov.tw/V8/C/W/County/index.html",
            logo = R.drawable.weather
        ),
            ProgramItem(
            title = "inline 訂位",
            link = "https://inline.app/groups/skm-xinyi?language=zh-TW",
            logo = R.drawable.inline_image
        )
    ),
    val webAppInfo: ProgramItem = ProgramItem(),
    val onAppPreviewClick: (ProgramItem) -> Unit = { _ ->},
    val onStartClick: () -> Unit = {},
    val ads: AdsUI = AdsUI(),
    val isShowQrAdd: Boolean = false
)
