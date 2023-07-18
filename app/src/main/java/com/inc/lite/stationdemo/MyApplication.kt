package com.inc.lite.stationdemo

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication: Application() {
    fun getInstance(): Context{
        return applicationContext
    }
    fun getAddUrl(): String{
        return "https://api.riisu.co"
    }
}