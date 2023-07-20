package com.inc.lite.stationdemo.repository

import com.inc.lite.stationdemo.model.Ads
import com.inc.lite.stationdemo.model.AdsItem

interface  MainRepository {
    suspend fun getAds(): List<AdsItem>
}