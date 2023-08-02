package com.inc.lite.stationdemo.viewModels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.inc.lite.stationdemo.model.User
import com.inc.lite.stationdemo.model.UserUpdate
import com.inc.lite.stationdemo.model.uiState.LoginUiState
import com.inc.lite.stationdemo.ui.navigation.Screen
import com.inc.lite.stationdemo.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val mainRepository: MainRepository,
): ViewModel(), AuthViewModel {

    private var _uiState = MutableStateFlow(LoginUiState())
    override val uiState = _uiState
    private lateinit var navigationHost: NavHostController

    private val TAG = "LoginViewModel"

    private var _isLoading: MutableState<Boolean> = mutableStateOf(false)
    override val isLoading: MutableState<Boolean> = _isLoading


    private var _isCodeError: MutableState<Boolean> = mutableStateOf(false)
    override val isCodeError = _isCodeError

    private var phoneNumber: String = ""
    private var password: String = ""
    private var verificationCode: String = ""
    private var user = mutableStateOf(User())

    private var token: String = ""


    fun cleanUser(){
        user.value =  User()
    }

    private fun startVerification(){
        viewModelScope.launch(Dispatchers.IO) {
            mainRepository.startVerification(phoneNumber){ response,message ->
                if (response != null){
                    if (response.needConfirmation){
                        viewModelScope.launch(Dispatchers.Main) {
                            navigationHost.navigate(Screen.LoginEnterSMS.route)
                        }
                        _isLoading.value = false

                    }else {
                        user.value.id = response.userId
                        viewModelScope.launch(Dispatchers.Main) {
                            navigationHost.navigate(Screen.LoginEnterPass.route)
                        }
                        _isLoading.value = false
                    }
                }
                if (message != null){
                    Log.e(TAG, "StartVerification error: $message")
                }
            }
        }
    }

    private fun confirmVerification(){
        viewModelScope.launch(Dispatchers.IO) {
            mainRepository.confirmVerification(phoneNumber, verificationCode){ result, message ->
                if(result != null){
                    user.value = result.user
                    token = result.token
                    Log.d(TAG, "Token = $token")
                    Log.d(TAG, "User = ${user.value}")
                    viewModelScope.launch(Dispatchers.Main) {
                        navigationHost.navigate(Screen.LoginCreatePass.route)
                    }
                    _isLoading.value = false
                }else{
                    isCodeError.value = true
                    _isLoading.value = false

                }

            }
        }
    }

    private fun confirmPassword(){
        viewModelScope.launch(Dispatchers.IO) {
            mainRepository.confirmPassword(password, user.value.id.toString()){ result, message ->
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
                            //Todo end of authentication
//                            navigationHost.navigate(Screen.LoginCreatePass.route)
                        }
                    }
                    _isLoading.value = false
                }else{
                    _isLoading.value = false

                }
            }
        }
    }

    private fun updateUser(){
        viewModelScope.launch(Dispatchers.IO) {
            val userInfo = UserUpdate(password, user.value.nickname, user.value.email)
            mainRepository.updateUser(token, user.value.id.toString(),userInfo){ result, message ->

            }
        }
    }
    override fun confirmPhoneNumber(navHostController: NavHostController, number: String) {
        navigationHost = navHostController
        phoneNumber =  "+" + uiState.value.countyCode + number
        Log.d(TAG, "Phone number: $phoneNumber")
        _isLoading.value = true
        startVerification()
//        navigationHost.navigate(Screen.LoginEnterSMS.route)
    }

    override fun confirmSMSCode(verificationCode: String) {
        this.verificationCode = verificationCode
        _isLoading.value = true
        confirmVerification()
    }

    override fun confirmPassword(password: String) {
        this.password = password
        confirmPassword()
    }
    override fun createPassword(password: String) {
        this.password = password
        Log.d(TAG, "User + password: ${user.value} \n password : ${this.password}")
        if(user.value.email == null){
            navigationHost.navigate(Screen.RegEnterEmail.route)
        }else{
            updateUser()
            //Todo navigate to user page

        }
    }

    override fun onKeyBoardClick(key: String, typeOfScreen: Screen){

    }

    override fun onDropDownItemClick(pair: Pair<String, String>){
        _uiState.update {
            uiState.value.copy(
                countryName = pair.first,
                countyCode = pair.second.drop(1)
            )
        }
    }

    override fun onValueEmailSubmit(email: String){
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
    override fun onValueNickNameSubmit(nickname: String){
        user.value = user.value.copy(
            nickname = nickname
        )
        Log.d(TAG, "User + nickname : ${user.value}")
        updateUser()
        //Todo navigate to user page
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