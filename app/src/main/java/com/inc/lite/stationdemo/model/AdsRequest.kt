package com.inc.lite.stationdemo.model

import com.google.gson.annotations.SerializedName

data class AdsRequest(
    val code: Int,
    @SerializedName("data")
    val adsList: List<AdsList>,
//    val stationUrl: String,
    val msg: String,
    val time: Int,
    val type: Int
)