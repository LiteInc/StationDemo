package com.inc.lite.stationdemo.viewModels

import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import com.inc.lite.stationdemo.model.uiState.LoginUiState
import com.inc.lite.stationdemo.ui.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow

interface AuthViewModel {
    val uiState: MutableStateFlow<LoginUiState>

    val isCodeError: MutableState<Boolean>
    val isLoading: MutableState<Boolean>

    fun onKeyBoardClick(key: String, typeOfScreen: Screen)
    fun onDropDownItemClick(pair: Pair<String, String>)
    fun onValueEmailSubmit(email: String)
    fun onValueNickNameSubmit(nickname: String)
    fun addValueByKey(string: String, key: String): String
    fun confirmPhoneNumber(navHostController: NavHostController, number: String)
    fun confirmSMSCode(verificationCode: String)

    fun createPassword(password: String)
    fun confirmPassword(password: String)

}