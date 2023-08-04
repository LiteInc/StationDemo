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
    val isShowToast: MutableState<Boolean>

    val smsCode: MutableState<String>
    val phoneNumber: MutableState<String>
    val password: MutableState<String>

//    val isCleanPassword: MutableState<Boolean>

    fun onKeyBoardClick(key: String, typeOfScreen: Screen)
    fun onDropDownItemClick(pair: Pair<String, String>)
    fun addValueByKey(string: String, key: String): String
    fun confirmPhoneNumber(navHostController: NavHostController)
    fun confirmEmail(email: String)
    fun confirmNickname(nickname: String)
    fun confirmSMSCode()
    fun createPassword()
    fun confirmCurrentPassword()
    fun passMainNavHost(navHostController: NavHostController)

}