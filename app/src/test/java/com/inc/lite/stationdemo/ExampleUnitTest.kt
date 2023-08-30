package com.inc.lite.stationdemo

import com.inc.lite.stationdemo.model.AdsItem
import com.inc.lite.stationdemo.model.AdsLayouts
import com.inc.lite.stationdemo.model.AdsType
import com.inc.lite.stationdemo.model.uiState.LoginUiState
import com.inc.lite.stationdemo.repository.MainRepoImpl
import com.inc.lite.stationdemo.repository.MainRepository
import com.inc.lite.stationdemo.util.AdsTimer
import com.inc.lite.stationdemo.viewModels.LoginViewModel
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)


    }
    @Test
    fun ads_timer_proper_sorting(){

        val adsList =  listOf(
            AdsItem("first",AdsType.Image, playTime = 10, 1),
            AdsItem("second",AdsType.Image, playTime = 10, 3),
            AdsItem("third",AdsType.Image, playTime = 10, 1),
            AdsItem("fourth",AdsType.Image, playTime = 10, 2),
        )


        val adsTimer = AdsTimer.AdsTimerBuilder()
            .layout(AdsLayouts.SingleImage)
            .listOfAds(adsList)
            .build()

        println( "\n" + adsTimer.listOfAds  + "\n")
    }



}