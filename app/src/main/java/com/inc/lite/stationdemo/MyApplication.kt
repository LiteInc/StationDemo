package com.inc.lite.stationdemo

import android.app.Application
import android.content.Context
import com.inc.lite.stationdemo.model.AppEnvironment
import com.inc.lite.stationdemo.util.StationID
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication: Application() {

    companion object{
        val environment: AppEnvironment = AppEnvironment.Development
    }


    private val imei = "860456060990168"
    fun getInstance(): Context{
        return applicationContext
    }
    fun getAddUrl(): String{
        return environment.getAdsHostUrl()
    }

    fun getAdsSign(): String{
        return environment.adsSign
    }

    fun getStationId(): String{
        return StationID().getUserFriendlyStationId(imei)
    }

    fun getStationImei(): String{
        return imei
    }

}