package com.inc.lite.stationdemo.viewModels

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.inc.lite.stationdemo.activities.MainActivity
import com.inc.lite.stationdemo.model.PaymentType
import com.inc.lite.stationdemo.model.uiState.ProfileUiState
import com.inc.lite.stationdemo.model.uiState.RentUiState
import com.inc.lite.stationdemo.repository.MainRepository
import com.inc.lite.stationdemo.ui.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RentViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    sharedInfo: SharedInformation
): ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState = _uiState.asStateFlow()
    private val _rentUiState = MutableStateFlow(RentUiState())
    val rentUiState = _rentUiState.asStateFlow()

    private val _selectedPayment: MutableState<PaymentType?> = mutableStateOf(null)
    val selectedPayment = _selectedPayment

    private lateinit var mainNavHost: NavHostController
    private lateinit var navHostController: NavHostController

    private val userToken: String = sharedInfo.userToken

    private val _statusBarUiState = mutableStateOf(sharedInfo.statusBarState)
    val statusBarUiState = _statusBarUiState


    fun logOut(context: Context){
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
//        mainNavHost.navigate(Screen.RegOrLogin.route)
    }

    fun getNavHost(navHostMain: NavHostController, navHostController: NavHostController){
        mainNavHost = navHostMain
        this.navHostController = navHostController
    }

    fun skipCoupons(){

    }
    fun useCoupons(){
        navHostController.navigate(Screen.ChoseWitchCoupons.route)
    }

    fun onPaymentConfirm() {
        //TODO Not yet implemented
    }

    fun onCouponsSelect() {
        _selectedPayment.value = PaymentType.Coupons
    }

    fun onLiteWalletSelect() {
        _selectedPayment.value = PaymentType.LiteWallet
    }

    fun onLinePaySelect() {
        _selectedPayment.value = PaymentType.LinePay
    }

}