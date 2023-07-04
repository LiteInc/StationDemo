package com.inc.lite.stationdemo.modules.ui.viewModel

import androidx.lifecycle.ViewModel
import com.inc.lite.stationdemo.modules.ui.models.MainUiState
import com.inc.lite.stationdemo.util.QrCodeLink
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel: ViewModel() {

    val uiTest = true
    private val uiState = MutableStateFlow(MainUiState())
    val _uiState = uiState.asStateFlow()



    init {
        onStart()
    }






    private fun onStart(){
        val imei = "86732904546307"

        val qrURL = QrCodeLink().getLink(imei)
        uiState.update {
            it.copy(stationID = imei.toLong(), stationQR = qrURL)
        }
    }

}


