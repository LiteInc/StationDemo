package com.inc.lite.stationdemo.model

data class Programs(
    val code: Int,
    val data: List<ProgramItem>,
    val msg: String,
    val time: Int,
    val type: Int
)
