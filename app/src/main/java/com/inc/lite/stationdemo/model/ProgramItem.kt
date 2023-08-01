package com.inc.lite.stationdemo.model

import com.inc.lite.stationdemo.R

data class ProgramItem(
    val id: Int = 0,
    val title: String = "",
    val logo: Int = R.drawable.gplay_logo,
    val link: String = "https://www.google.com.tw/?hl=zh_TW",
    val createdAt: String = "",
)
