package com.inc.lite.stationdemo.model

sealed class StationType(
    val slots: Int
){
    object eightSlots: StationType(8)
    object twelveSlots: StationType(8)
}
