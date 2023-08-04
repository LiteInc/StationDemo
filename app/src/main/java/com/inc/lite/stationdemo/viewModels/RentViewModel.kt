package com.inc.lite.stationdemo.viewModels

import androidx.lifecycle.ViewModel
import com.inc.lite.stationdemo.model.uiState.ProfileUiState
import com.inc.lite.stationdemo.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RentViewModel @Inject constructor(
    private val mainRepository: MainRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState = _uiState.asStateFlow()

    private val userToken: String = ""

    fun logOut(){

    }

}