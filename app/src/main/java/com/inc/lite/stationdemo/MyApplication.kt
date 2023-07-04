package com.inc.lite.stationdemo

import android.app.Application
import android.content.Context

class MyApplication: Application() {
    fun getInstance(): Context{
        return applicationContext
    }
}