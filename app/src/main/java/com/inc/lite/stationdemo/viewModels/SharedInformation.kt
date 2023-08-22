package com.inc.lite.stationdemo.viewModels

import com.inc.lite.stationdemo.model.StatusBarUiState

class SharedInformation() {

    var userToken = ""
        private set

    var statusBarState = StatusBarUiState()
        private set

    fun setStatusBarState(title: String, stationID: String){
        statusBarState = statusBarState.copy(stationID = stationID, titleText = title)
    }

}