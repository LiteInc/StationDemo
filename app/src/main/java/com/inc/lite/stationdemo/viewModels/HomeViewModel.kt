package com.inc.lite.stationdemo.viewModels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.inc.lite.stationdemo.MyApplication
import com.inc.lite.stationdemo.model.AdsItem
import com.inc.lite.stationdemo.model.AdsLayouts
import com.inc.lite.stationdemo.model.AdsType
import com.inc.lite.stationdemo.model.AdsUI
import com.inc.lite.stationdemo.model.uiState.MainUiState
import com.inc.lite.stationdemo.model.ProgramItem
import com.inc.lite.stationdemo.model.StatusBarUiState
import com.inc.lite.stationdemo.repository.MainRepository
import com.inc.lite.stationdemo.ui.navigation.Screen
import com.inc.lite.stationdemo.util.AdsTimer
import com.inc.lite.stationdemo.util.QrCodeLink
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val sharedInformation: SharedInformation,
    private val app: MyApplication
): ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState = _uiState.asStateFlow()

    private val TAG = "HomeViewModel"

    val adsIsLoaded: MutableState<Boolean> = mutableStateOf(true)
    val timerTime = mutableStateOf("")
    val isShowProgramsTimer = mutableStateOf(false)
    private var isTimerOn = false
    private var programsTime = 0
    private val timeOut = 60 // Time out in seconds for timer


    private var isVideoPlaying = true
    private lateinit var adsTimer: AdsTimer
    private lateinit var navHost: NavHostController

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
                ads = AdsUI(
                    adsList = adsList,
                    adsLayout = AdsLayouts.SingleImage,
                    isAdsLoaded = false
                )
            )
        }
        onStart()
    }

    fun initiateNavHost(navHostController: NavHostController){
        navHost = navHostController
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
        viewModelScope.launch(Dispatchers.IO) {
            mainRepository.getAds{list, message ->
                if(message == null){
                    adsList = list
                    _uiState.update {
                        it.copy(
                            ads = AdsUI(
                                adsList = list,
                                isAdsLoaded = true
                            )
                        )
                    }
                }

                if (list.isNotEmpty()){
                    adsIsLoaded.value = false
                    adsTimer.updateListOfAds(adsList)
                    getAdsChange()
                }
            }
        }
    }


    private fun initAdsTimer(){
        adsTimer = AdsTimer.AdsTimerBuilder()
            .layout(AdsLayouts.SingleImage)
            .listOfAds(adsList)
            .build()

    }

    fun videoPlaying(value: Boolean){
        adsTimer.updateVideoStatus(value)
        isVideoPlaying = value
        Log.d(TAG, "Video is finish: $isVideoPlaying")
    }

    fun stopAdsTimer(){
        adsTimer.stopTheTimer()
    }

    private fun onStart(){

        Log.d(TAG, "Started")
        val imei = "86732904546307"

        sharedInformation.setStatusBarState(stationID = app.getStationId(), title = "時光3C內湖體驗門市")

        val qrURL = QrCodeLink().getLink(imei)
        _uiState.update {
            it.copy(
                stationID = imei.toLong(),
                stationQR = qrURL,
                statusUiState = sharedInformation.statusBarState
            )
        }
//        getAddsRequest()
        initAdsTimer()
        layout1()
//        getProgramsRequest()

    }
    fun startTimer(){
        isTimerOn = true
        programsTimer()
    }
    private fun stopProgramsTimer(){
        isTimerOn = false
        programsTime = 0
    }

    fun onExitTimerClick(){
        navHost.navigate(Screen.Main.route)
        isTimerOn = false
    }
    fun onContinueTimerClick(){
        programsTime = 0
        isShowProgramsTimer.value = false
    }

    private fun programsTimer(){
        programsTime = 0
        Log.d(TAG, "Programs timer starts")
        viewModelScope.launch(Dispatchers.Main) {
            while (isTimerOn){
                delay(1000)
                programsTime += 1
                Log.d(TAG, "Programs time = $programsTime")
                if((timeOut - programsTime) <= 15  && (timeOut - programsTime) > 0 ){
                    Log.d(TAG, "Show Dialog")
                    isShowProgramsTimer.value = true
                    timerTime.value = (timeOut - programsTime).toString()
//                    Log.d(TAG,"TimerTime = ${timerTime.value }")
                }else if (programsTime == timeOut){
                    //Todo on timeout
                    Log.d(TAG, "Timer out")
                    isShowProgramsTimer.value = false
                    isTimerOn = false
                    programsTime = 0
                    navHost.navigate(Screen.Main.route)
                }
            }
        }
    }

    fun navigateToPrograms() {
        stopAdsTimer()
        navHost.navigate(Screen.Programs.route)
    }

    fun navigateToWeb(programItem: ProgramItem) {
        setWebProgram(programItem)
        webViewProgram = programItem
        stopProgramsTimer()
        navHost.navigate(Screen.WebView.route)
    }

    fun layout1() {
        adsIsLoaded.value = false

        adsTimer.updateListOfAds(
            listOf(
                AdsItem("https://res.cloudinary.com/riisu/image/upload/v1692865710/dva8rn2ltaju0artrisr.jpg",AdsType.Image, 5000)
            )
        )
        _uiState.update {
            it.copy(
                ads =
                AdsUI().copy(
                    adsLayout = AdsLayouts.SingleImage,
                    adsList = listOf(
                        AdsItem("https://res.cloudinary.com/riisu/image/upload/v1692865710/dva8rn2ltaju0artrisr.jpg",AdsType.Image, 5000),
                    )
                )
            )
        }
    }

    fun layout2() {
        adsTimer.updateListOfAds(
            listOf(
                AdsItem("https://res.cloudinary.com/riisu/image/upload/v1692868788/zetywm808lwif21m38ba.png",AdsType.Image, 5000),
                AdsItem("https://res.cloudinary.com/riisu/image/upload/v1692868788/zetywm808lwif21m38ba.png",AdsType.Image, 5000),
            )
        )
        _uiState.update {
            it.copy(
                ads =
                AdsUI().copy(
                    adsLayout = AdsLayouts.TwoImage,
                    adsList = listOf(
                        AdsItem("https://res.cloudinary.com/riisu/image/upload/v1692868788/zetywm808lwif21m38ba.png",AdsType.Image, 5000),
                        AdsItem("https://res.cloudinary.com/riisu/image/upload/v1692868788/zetywm808lwif21m38ba.png",AdsType.Image, 5000),
                    )
                )
            )
        }

    }
    fun layout3() {
        adsTimer.updateListOfAds(
            listOf(
                AdsItem("https://res.cloudinary.com/riisu/image/upload/v1692868788/zetywm808lwif21m38ba.png",AdsType.Image, 5000),
                AdsItem("https://res.cloudinary.com/riisu/image/upload/v1692865710/dva8rn2ltaju0artrisr.jpg",AdsType.Image, 5000),
                AdsItem("https://res.cloudinary.com/riisu/image/upload/v1692865710/dva8rn2ltaju0artrisr.jpg",AdsType.Image, 5000),
            )
        )
        _uiState.update {
            it.copy(
                ads =
                AdsUI().copy(
                    adsLayout = AdsLayouts.ThreeImage,
                    adsList = listOf(
                        AdsItem("https://res.cloudinary.com/riisu/image/upload/v1692868788/zetywm808lwif21m38ba.png",AdsType.Image, 5000),
                        AdsItem("https://res.cloudinary.com/riisu/image/upload/v1692865710/dva8rn2ltaju0artrisr.jpg",AdsType.Image, 5000),
                        AdsItem("https://res.cloudinary.com/riisu/image/upload/v1692865710/dva8rn2ltaju0artrisr.jpg",AdsType.Image, 5000),
                    )
                )
            )
        }
    }
    fun layout4() {
        adsIsLoaded.value = false

        adsTimer.updateListOfAds(
            listOf(
                AdsItem("https://res.cloudinary.com/riisu/image/upload/v1692865710/dva8rn2ltaju0artrisr.jpg",AdsType.Image, 5000),
                AdsItem("https://res.cloudinary.com/riisu/image/upload/v1692865710/dva8rn2ltaju0artrisr.jpg",AdsType.Image, 5000),
                AdsItem("https://res.cloudinary.com/riisu/image/upload/v1692865710/dva8rn2ltaju0artrisr.jpg",AdsType.Image, 5000),
                AdsItem("https://res.cloudinary.com/riisu/image/upload/v1692865710/dva8rn2ltaju0artrisr.jpg",AdsType.Image, 5000),
            )
        )

        _uiState.update {
            it.copy(
                ads =
                AdsUI().copy(
                    adsLayout = AdsLayouts.FourImage,
                    adsList = listOf(
                        AdsItem("https://res.cloudinary.com/riisu/image/upload/v1692865710/dva8rn2ltaju0artrisr.jpg",AdsType.Image, 5000),
                        AdsItem("https://res.cloudinary.com/riisu/image/upload/v1692865710/dva8rn2ltaju0artrisr.jpg",AdsType.Image, 5000),
                        AdsItem("https://res.cloudinary.com/riisu/image/upload/v1692865710/dva8rn2ltaju0artrisr.jpg",AdsType.Image, 5000),
                        AdsItem("https://res.cloudinary.com/riisu/image/upload/v1692865710/dva8rn2ltaju0artrisr.jpg",AdsType.Image, 5000),
                    )
                )
            )
        }
    }

}


