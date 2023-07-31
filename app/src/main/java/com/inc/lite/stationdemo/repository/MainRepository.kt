package com.inc.lite.stationdemo.repository

import com.inc.lite.stationdemo.model.Ads
import com.inc.lite.stationdemo.model.AdsItem
import com.inc.lite.stationdemo.model.ProgramItem

interface  MainRepository {
    suspend fun getAds(result: (List<AdsItem>, String?) -> Unit)
    suspend fun getPrograms(result: (List<ProgramItem>, String?) -> Unit)
}