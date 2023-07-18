package com.inc.lite.stationdemo.modules.models

enum class AppEnvironment {
    Production,
    Development;

    companion object {
        const val productionUrl = "https://api.riisu.co"
        const val developmentUrl = "https://api-dev.riisu.co"

    }

    fun getAdsHostUrl(): String {
        return when(this){
            Production -> productionUrl
            Development -> developmentUrl
        }
    }
    val adsSign = "PxHigSSyqDoUE6mXTuXViUuR"

}