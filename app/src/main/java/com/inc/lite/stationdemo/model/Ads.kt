package com.inc.lite.stationdemo.model

data class Ads(
    val code: Int,
    val data: List<Data>,
    val msg: String,
    val time: Int,
    val type: Int
)