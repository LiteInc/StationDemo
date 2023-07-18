package com.inc.lite.stationdemo.modules.viewModels

import androidx.lifecycle.ViewModel
import com.inc.lite.stationdemo.MyApplication
import com.inc.lite.stationdemo.modules.models.MainUiState
import com.inc.lite.stationdemo.modules.models.StatusBarUiState
import com.inc.lite.stationdemo.modules.repository.MainRepository
import com.inc.lite.stationdemo.util.QrCodeLink
import com.inc.lite.stationdemo.util.StationID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val app: MyApplication
): ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState = _uiState.asStateFlow()

    init {
        onStart()
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
    }

}


