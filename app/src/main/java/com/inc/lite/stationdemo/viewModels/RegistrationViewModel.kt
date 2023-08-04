package com.inc.lite.stationdemo.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.inc.lite.stationdemo.model.uiState.LoginUiState
import com.inc.lite.stationdemo.ui.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class RegistrationViewModel() : ViewModel(), AuthViewModel {
    private var _uiState = MutableStateFlow(LoginUiState())
    override val uiState = _uiState

    private var _isLoading: MutableState<Boolean> = mutableStateOf(false)
    override val isLoading: MutableState<Boolean> = _isLoading

    private var _isCodeError: MutableState<Boolean> = mutableStateOf(false)
    override val isCodeError = _isCodeError

    private var _isShowToast: MutableState<Boolean> = mutableStateOf(false)
    override val isShowToast = _isShowToast

    override val phoneNumber: MutableState<String> = mutableStateOf("         ")
    override val password: MutableState<String> = mutableStateOf("         ")

    override val smsCode: MutableState<String> = mutableStateOf("")


    lateinit var navigationHost: NavHostController
    val confirmCode: MutableState<String> = mutableStateOf("")

    override fun onKeyBoardClick(key: String, typeOfScreen: Screen) {
        if(key != "del" && key != ""){

        }
    }

    override fun onDropDownItemClick(pair: Pair<String, String>) {
        _uiState.update {
            uiState.value.copy(
                countryName = pair.first,
                countyCode = pair.second.drop(1)
            )
        }
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
    override fun passMainNavHost(navHostController: NavHostController){

    }

    override fun confirmPhoneNumber(navHostController: NavHostController) {
        navigationHost = navHostController
    }

    override fun confirmSMSCode() {
        TODO("Not yet implemented")
    }

    override fun confirmCurrentPassword() {
        TODO("Not yet implemented")
    }
    override fun createPassword() {
        TODO("Not yet implemented")
    }

    override fun confirmEmail(email: String) {
        TODO("Not yet implemented")
    }

    override fun confirmNickname(nickname: String) {
        TODO("Not yet implemented")
    }

}