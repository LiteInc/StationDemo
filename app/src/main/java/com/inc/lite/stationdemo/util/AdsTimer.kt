package com.inc.lite.stationdemo.util

import android.util.Log
import com.inc.lite.stationdemo.model.AdsItem
import com.inc.lite.stationdemo.model.AdsLayouts
import com.inc.lite.stationdemo.model.AdsType
import kotlinx.coroutines.delay

class AdsTimer private constructor(
    val layoutType: AdsLayouts,
    var listOfAds: List<AdsItem>,
) {
    private var isTimer = false
    private var isVideoPlay = true
    private val TAG = "AdsTimer"
    private val defaultVideoDelay: Long = 1000
    class AdsTimerBuilder{
        private var layoutType: AdsLayouts = AdsLayouts.SingleImage
        private var listOfAds: List<AdsItem> = emptyList()

        fun layout(value: AdsLayouts) = apply { layoutType = value }
        fun listOfAds(value: List<AdsItem>) = apply { listOfAds = value.sortedBy { it.order } }

        fun build() = AdsTimer(layoutType,listOfAds)
    }

    fun stopTheTimer(){
        isTimer = false
    }

    fun updateVideoStatus(value: Boolean){
        isVideoPlay = value
    }
    private fun sortAdsList(list: List<AdsItem>): List<AdsItem>{
        return list.sortedBy { it.order }
    }

    fun updateListOfAds(list: List<AdsItem>){
        listOfAds = sortAdsList(list)
        isTimer = false
    }


    suspend fun showAdsFlow(
        actionOnPlay: (AdsItem)-> Unit
    ){
        Log.d(TAG,"Start the ads flow")
        isTimer = true
        while (isTimer){
            for(n in listOfAds.indices){
                if (listOfAds[n].type == AdsType.Video){
                    isVideoPlay = true
                    Log.d(TAG,"Video shows")
                    actionOnPlay(listOfAds[n])
                    while(isVideoPlay){
                        delay(defaultVideoDelay)
                    }
                }else{
                    Log.d(TAG,"Image shows")
                    actionOnPlay(listOfAds[n])
                    val time = if (listOfAds[n].playTime <= 1000 ) 2000 else listOfAds[n].playTime
                    delay(time)
                }
            }
//            Log.d("AdsTimer","Working")
        }
        Log.d(TAG,"Stopped")
    }



}