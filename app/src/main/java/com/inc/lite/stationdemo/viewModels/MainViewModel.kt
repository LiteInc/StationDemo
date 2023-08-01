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

    lateinit var webViewProgram: ProgramItem
        private set

    init {
        adsList =
            listOf(
                AdsItem(
                    url = "https://i.postimg.cc/g2MnDMXM/loading-ads.png",
                    type = AdsType.Image,
                    playTime = 5
                )
            )
        _uiState.update {
            it.copy(
                ads = AdsUI(adsList = adsList, adsLayout = AdsLayouts.SingleImage, isAdsLoaded = false)
            )
        }
        onStart()
    }


    fun setProgramForWebView(program: ProgramItem){
        webViewProgram = program
    }

    fun setWebProgram(program: ProgramItem){

        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    webAppInfo = program
                )
            }
            Log.d("VIEWMODEL", "$program")

        }
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
//                    programsList = list
//                    updatePrograms()
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
        viewModelScope.launch {
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

    fun stopTimer(){
        adsTimer.stopTheTimer()
    }

    private fun onStart(){

        Log.d("VIEWMODEL", "Started")
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


