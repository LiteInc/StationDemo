package com.inc.lite.stationdemo.modules.viewModels

import com.inc.lite.stationdemo.modules.models.LoginUiState
import com.inc.lite.stationdemo.modules.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow

interface AuthViewModel {
    val uiState: MutableStateFlow<LoginUiState>

    fun onKeyBoardClick(key: String, typeOfScreen: Screen)
    fun onDropDownItemClick(pair: Pair<String, String>)
    fun onValueEmailSubmit(email: String)
    fun onValueNickNameSubmit(nickname: String)
    fun addValueByKey(string: String, key: String): String
}