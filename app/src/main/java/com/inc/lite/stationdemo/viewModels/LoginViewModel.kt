package com.inc.lite.stationdemo.viewModels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.inc.lite.stationdemo.MyApplication
import com.inc.lite.stationdemo.model.User
import com.inc.lite.stationdemo.model.UserUpdate
import com.inc.lite.stationdemo.model.uiState.LoginUiState
import com.inc.lite.stationdemo.ui.navigation.Screen
import com.inc.lite.stationdemo.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val app: MyApplication,
    sharedInfo: SharedInformation
): ViewModel(), AuthViewModel {

    private var _uiState = MutableStateFlow(LoginUiState())
    override val uiState = _uiState
    private lateinit var navigationHost: NavHostController
    private lateinit var mainNavHost: NavHostController

    private val TAG = "LoginViewModel"

    private val spaces4 = "    "
    private val spaces6 = "      "
    private val spaces9 = "         "

    private var _isLoading: MutableState<Boolean> = mutableStateOf(false)
    override val isLoading: MutableState<Boolean> = _isLoading

    private var _password: MutableState<String> = mutableStateOf(spaces6)
    private var _smsCode: MutableState<String> = mutableStateOf(spaces4)
    private var _phoneNumber: MutableState<String> = mutableStateOf(spaces9)
    override val smsCode: MutableState<String> = _smsCode
    override val phoneNumber: MutableState<String> = _phoneNumber
    override val password: MutableState<String> = _password
    private var _isCodeError: MutableState<Boolean> = mutableStateOf(false)
    override val isCodeError = _isCodeError
    private var _isShowToast: MutableState<Boolean> = mutableStateOf(false)
    override val isShowToast = _isShowToast

    private val _statusBarUiState = mutableStateOf(sharedInfo.statusBarState)
    override val statusBarUiState = _statusBarUiState


    private var user = mutableStateOf(User())

    private var token: String = ""
    private var isVerificationTimerOn = false

    private var fullPhoneNumber = ""

    private var loginedPage = Screen.Coupons.route


    fun cleanUser(){
        user.value =  User()
        fullPhoneNumber = ""
    }
    override fun passMainNavHost(navHostController: NavHostController){
        mainNavHost = navHostController
    }

    private fun showToast(){
        _isShowToast.value = true
    }
    private fun smsVerificationTimer(){
        viewModelScope.launch {
            isVerificationTimerOn = true
            var totalCount = 0
            while(isVerificationTimerOn){
                if(totalCount <= 35){
                    delay(5000)
                    totalCount += 1
                    Log.d(TAG, "TotalCount: $totalCount")
                }else{
                    isVerificationTimerOn = false
                    Log.d(TAG, "Time out = totalCount: $totalCount")
                    showToast()
                    navigationHost.navigate(Screen.LoginEnterNumber.route)
                }
            }
        }
    }

    private fun startVerification(){
        viewModelScope.launch(Dispatchers.IO) {
            mainRepository.startVerification(fullPhoneNumber){ response,message ->
                if (response != null){
                    if (response.needConfirmation){
                        viewModelScope.launch(Dispatchers.Main) {
                            navigationHost.navigate(Screen.LoginEnterSMS.route)
                            _isLoading.value = false
                            smsVerificationTimer()
                        }


                    }else {
                        user.value.id = response.userId
                        viewModelScope.launch(Dispatchers.Main) {
                            navigationHost.navigate(Screen.LoginEnterPass.route)
                            _isLoading.value = false
                        }

                    }
                }else{
                    Log.e(TAG, "StartVerification error: $message")
                    _isLoading.value = false
//                    _isCodeError.value = true
                    _phoneNumber.value = spaces9
                }
            }
        }
    }

    private fun confirmVerification(){
        Log.d(TAG, "Confirm verification: $fullPhoneNumber + sms : ${smsCode.value} ")
        viewModelScope.launch(Dispatchers.IO) {
            Log.d(TAG, "Confirm verification: $fullPhoneNumber + sms : ${smsCode.value} ")
            mainRepository.confirmVerification(fullPhoneNumber, smsCode.value){ result, message ->
                if(result != null){
                    user.value = result.user
                    token = result.token
                    Log.d(TAG, "Token = $token")
                    Log.d(TAG, "User = ${user.value}")
                    viewModelScope.launch(Dispatchers.Main) {
                        isVerificationTimerOn = false
                        navigationHost.navigate(Screen.LoginCreatePass.route)
                    }
                    _isLoading.value = false
                }else{
                    _smsCode.value = spaces4
                    isCodeError.value = true
                    _isLoading.value = false

                }

            }
        }
    }

    private fun confirmPassword(){
        viewModelScope.launch(Dispatchers.IO) {
            _isCodeError.value = false
            mainRepository.confirmPassword(password.value, user.value.id.toString()){ result, message ->
                if (result != null ){
                    user.value = result.user
                    token = result.token
                    Log.d(TAG, "Token = $token")

                    if(user.value.email == null || user.value.nickname == null){
                        viewModelScope.launch(Dispatchers.Main) {
                            navigationHost.navigate(Screen.RegEnterEmail.route)
                        }
                    }else{
                        viewModelScope.launch(Dispatchers.Main) {
                            mainNavHost.navigate(loginedPage)
                        }
                    }
                    _isLoading.value = false
                }else{
                    _isLoading.value = false
                    _isCodeError.value = true
                    _password.value = spaces6


                }
            }
        }
    }

    private fun updateUser(){
        viewModelScope.launch(Dispatchers.IO) {
            val userInfo = UserUpdate(password.value, user.value.nickname, user.value.email)
            mainRepository.updateUser(token, user.value.id.toString(),userInfo){ result, message ->

            }
        }
    }
    override fun confirmPhoneNumber(navHostController: NavHostController) {
        navigationHost = navHostController
        fullPhoneNumber =  "+" + uiState.value.countyCode + phoneNumber.value
        Log.d(TAG, "Phone number: ${phoneNumber.value}")
        _isLoading.value = true
        startVerification()
    }

    override fun confirmSMSCode() {
        _isLoading.value = true
        confirmVerification()
    }

    override fun confirmCurrentPassword() {
        confirmPassword()
    }
    override fun createPassword() {
        Log.d(TAG, "User + password: ${user.value} \n password : ${this.password.value}")
        if(user.value.email == null){
            navigationHost.navigate(Screen.RegEnterEmail.route)
        }else{
            updateUser()
            mainNavHost.navigate(loginedPage)

        }
    }



    override fun onDropDownItemClick(pair: Pair<String, String>){
        _uiState.update {
            uiState.value.copy(
                countryName = pair.first,
                countyCode = pair.second.drop(1)
            )
        }
    }

    override fun confirmEmail(email: String){

        user.value = user.value.copy(
            email = email
        )
        Log.d(TAG, "User + email : ${user.value}")
        if(user.value.nickname == null){
            navigationHost.navigate(Screen.RegEnterNickName.route)
        }else{
            updateUser()
        }
    }
    override fun confirmNickname(nickname: String){
        user.value = user.value.copy(
            nickname = nickname
        )
        Log.d(TAG, "User + nickname : ${user.value}")
        updateUser()
        mainNavHost.navigate(loginedPage)
    }

    override fun addValueByKey(string: String, key: String): String {
        val array = string.toCharArray()

        if(key != "d" && key != " "){
            for(n in array.indices){
                if(array[n] == ' ') {
                    array[n] = key.first()
                    var result = ""
                    for(n in array.indices){
                        result += array[n].toString()
                    }
                    return result
                }
            }

            var result = ""
            for(n in array.indices){
                result += array[n].toString()
            }
            return result
        }
        else{

            for(n in array.indices){
                if(array[n] == ' ' && n != 0 && key == "d" ) {
                    array[n-1] = ' '
                    var result = ""
                    for(n in array.indices){
                        result += array[n].toString()
                    }
                    return result
                }else if( array[n] != ' ' && n == (array.size-1) && key == "d"){
                    array[n] = ' '
                    var result = ""
                    for(n in array.indices){
                        result += array[n].toString()
                    }
                    return result
                }

            }

            var result = ""
            for(n in array.indices){
                result += array[n].toString()
            }
            return result
        }
    }
}