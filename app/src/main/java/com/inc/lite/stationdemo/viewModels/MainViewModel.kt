package com.inc.lite.stationdemo.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inc.lite.stationdemo.MyApplication
import com.inc.lite.stationdemo.model.Ads
import com.inc.lite.stationdemo.model.AdsItem
import com.inc.lite.stationdemo.model.AdsLayouts
import com.inc.lite.stationdemo.model.AdsType
import com.inc.lite.stationdemo.model.AdsUI
import com.inc.lite.stationdemo.model.MainUiState
import com.inc.lite.stationdemo.model.StatusBarUiState
import com.inc.lite.stationdemo.repository.MainRepository
import com.inc.lite.stationdemo.util.AdsTimer
import com.inc.lite.stationdemo.util.QrCodeLink
import dagger.hilt.android.lifecycle.HiltViewModel
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

    init {
        adsList = listOf(
            AdsItem(
                url = "http://res.cloudinary.com/riisu/image/upload/v1688934321/aka8mtwsm6fq4kaxa9ol.jpg",
                type = AdsType.Image,
                playTime = 10000
            )
        )
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

    private fun getAddsRequest(){
        viewModelScope.launch {
            adsList = mainRepository.getAds()
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
                startButtonString = "Start",
                stationID = imei.toLong(),
                stationQR = qrURL,
                statusUiState = StatusBarUiState(
                    stationID = app.getStationId(),
                    titleText = "時裝時裝"
                )
            )
        }
        getAddsRequest()
        initTimer()


    }

}


