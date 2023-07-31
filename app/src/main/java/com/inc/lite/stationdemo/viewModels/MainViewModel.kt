package com.inc.lite.stationdemo.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inc.lite.stationdemo.MyApplication
import com.inc.lite.stationdemo.model.AdsItem
import com.inc.lite.stationdemo.model.AdsLayouts
import com.inc.lite.stationdemo.model.AdsType
import com.inc.lite.stationdemo.model.AdsUI
import com.inc.lite.stationdemo.model.MainUiState
import com.inc.lite.stationdemo.model.ProgramItem
import com.inc.lite.stationdemo.model.StatusBarUiState
import com.inc.lite.stationdemo.repository.MainRepository
import com.inc.lite.stationdemo.util.AdsTimer
import com.inc.lite.stationdemo.util.QrCodeLink
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val app: MyApplication
): ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState = _uiState.asStateFlow()

    private var isVideoPlaying = true
    private lateinit var adsTimer: AdsTimer

    private var adsList: List<AdsItem> = emptyList()
    private var programsList: List<ProgramItem> = emptyList()

    private val programs = listOf(
        ProgramItem(
            title = "Google map",
            link = "https://www.google.com/maps/place/Taiwan/data=!4m2!3m1!1s0x346ef3065c07572f:0xe711f004bf9c5469?sa=X&ved=2ahUKEwih8ufHzK6AAxWJR_EDHYDwBUMQ8gF6BAgQEAA&ved=2ahUKEwih8ufHzK6AAxWJR_EDHYDwBUMQ8gF6BAgREAI",
            logo = "https://i.postimg.cc/VNZJSxTC/new-google-maps-icon-logo-263-A01-C734-seeklogo-com.png"
        ),
        ProgramItem(
            title = "",
            link = "https://www.cwb.gov.tw/V8/C/W/County/index.html",
            logo = "https://i.postimg.cc/SKMGCCbV/weather.webp"
        ),
        ProgramItem(
            title = "",
            link = "https://inline.app/groups/skm-xinyi?language=zh-TW",
            logo = ""
        )
    )

    init {
        adsList =
            listOf(
                AdsItem(
                    url = "http://res.cloudinary.com/riisu/image/upload/v1688934321/aka8mtwsm6fq4kaxa9ol.jpg",
                    type = AdsType.Image,
                    playTime = 20
                )
            )
        _uiState.update {
            it.copy(
                ads = AdsUI(adsList = adsList, adsLayout = AdsLayouts.SingleImage, isAdsLoaded = false)
            )
        }
        onStart()
    }

    fun getAdsChange(){
        viewModelScope.launch {
            adsTimer.showAdsFlow { adsItem ->
                _uiState.update {
                    it.copy(
                        ads =
                        AdsUI().copy(
                            adsList = listOf(adsItem)
                        )
                    )
                }
            }
        }
    }

    private fun getProgramsRequest(){
        viewModelScope.launch {
            mainRepository.getPrograms { list, message ->
                if (message == null) {
                    programsList = list
                    updatePrograms()
                }
            }
        }

    }

    private fun updatePrograms(){
        _uiState.update {
            it.copy(
                programsList = programsList
            )
        }
    }



    private fun getAddsRequest(){
        viewModelScope.launch(Dispatchers.IO) {
            mainRepository.getAds{list, message ->
                if(message == null){
                    adsList = list
                }
            }
            adsTimer.updateListOfAds(adsList)
            getAdsChange()
        }
    }
    private fun initTimer(){
        adsTimer = AdsTimer.AdsTimerBuilder()
            .layout(AdsLayouts.SingleImage)
            .listOfAds(adsList)
            .build()

    }

    fun videoPlaying(value: Boolean){
        adsTimer.updateVideoStatus(value)
        isVideoPlaying = value
        Log.d("MainViewModel", "Video is finish: $isVideoPlaying")
    }


    private fun onStart(){
        val imei = "86732904546307"

        val qrURL = QrCodeLink().getLink(imei)
        _uiState.update {
            it.copy(
                stationID = imei.toLong(),
                stationQR = qrURL,
                statusUiState = StatusBarUiState(
                    stationID = app.getStationId(),
                    titleText = "時光3C內湖體驗門市"
                )
            )
        }
        getAddsRequest()
        initTimer()
        getProgramsRequest()
    }

}


