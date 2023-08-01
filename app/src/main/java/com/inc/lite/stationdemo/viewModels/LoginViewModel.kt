package com.inc.lite.stationdemo.viewModels

import androidx.lifecycle.ViewModel
import com.inc.lite.stationdemo.model.uiState.LoginUiState
import com.inc.lite.stationdemo.ui.navigation.Screen
import com.inc.lite.stationdemo.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val mainRepository: MainRepository,
): ViewModel(), AuthViewModel {

    private var _uiState = MutableStateFlow(LoginUiState())
    override val uiState = _uiState


    init {

    }

    override fun onKeyBoardClick(key: String, typeOfScreen: Screen){
    }

    override fun onDropDownItemClick(pair: Pair<String, String>){
        _uiState.update {
            uiState.value.copy(
                countryName = pair.first,
                countyCode = pair.second
            )
        }
    }

    override fun onValueEmailSubmit(email: String){

    }
    override fun onValueNickNameSubmit(nickname: String){

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