package com.inc.lite.stationdemo.viewModels

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.inc.lite.stationdemo.model.uiState.ProfileUiState
import com.inc.lite.stationdemo.repository.MainRepository
import com.inc.lite.stationdemo.ui.navigation.Screen
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

    private lateinit var mainNavHost: NavHostController
    private lateinit var navHostController: NavHostController

    private val userToken: String = ""

    fun logOut(){
        mainNavHost.navigate(Screen.RegOrLogin.route)
    }

    fun getNavHost(navHostMain: NavHostController, navHostController: NavHostController){
        mainNavHost = navHostMain
        this.navHostController = navHostController
    }

    fun skipCoupons(){

    }
    fun useCoupons(){

    }

}