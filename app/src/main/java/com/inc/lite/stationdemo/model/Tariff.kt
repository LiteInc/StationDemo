package com.inc.lite.stationdemo.model

sealed class Tariff(
    val name: String,
    val tariffPerHour: Int,
    val tariffPerDay: Int
){
    object First: Tariff("First", 12, 36)
}