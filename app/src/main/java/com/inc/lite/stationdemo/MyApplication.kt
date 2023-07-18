package com.inc.lite.stationdemo

import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.telephony.TelephonyManager
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import com.inc.lite.stationdemo.modules.models.AppEnvironment
import com.inc.lite.stationdemo.util.StationID
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication: Application() {

    companion object{
        val environment: AppEnvironment = AppEnvironment.Development
    }


    private val imei = "86732904546307"
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