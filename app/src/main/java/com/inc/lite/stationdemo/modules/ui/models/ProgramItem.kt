package com.inc.lite.stationdemo.modules.ui.models

import com.inc.lite.stationdemo.R

data class ProgramItem(
    val title: String = "",
    val imageUrl: Int = R.drawable.gplay_logo,
    val programUrl: String = "https://www.google.com.tw/?hl=zh_TW"
)
