package com.inc.lite.stationdemo.viewModels

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.inc.lite.stationdemo.R
import com.inc.lite.stationdemo.activities.MainActivity
import com.inc.lite.stationdemo.model.PaymentType
import com.inc.lite.stationdemo.model.uiState.ProfileUiState
import com.inc.lite.stationdemo.model.uiState.RentUiState
import com.inc.lite.stationdemo.repository.MainRepository
import com.inc.lite.stationdemo.ui.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
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

    private val _showStationSlotsInfo = mutableStateOf(false)
    val showStationSlotsInfo  = _showStationSlotsInfo

    private var time = 20
    private val _rentalTimer = mutableStateOf(time)
    val rentalTimer = _rentalTimer

    lateinit var context: Context

    private val _topTitle: MutableState<Int>  = mutableStateOf(R.string.log_in)
    val topTitle = _topTitle

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
        startRental()
    }

    private fun startRental(){
        mainNavHost.navigate(Screen.StartRent.route)

    }
    fun useCoupons(){
        navHostController.navigate(Screen.ChoseWitchCoupons.route)
        setTitle(R.string.voucher)
    }

    fun setTitle(title: Int){
        _topTitle.value = title
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

    fun onFinishRent(){
        time = 1
    }
    fun popUpPowerBank(context: Context){
        viewModelScope.launch(Dispatchers.IO) {
            popUpPowerBank()
            delay(2000)
            _showStationSlotsInfo.value = true

            while (time > 1 ){
                delay( 1000)
                time -= 1
                _rentalTimer.value = time
            }
            logOut(context)
//            viewModelScope.launch(Dispatchers.Main){
//                mainNavHost.navigate(Screen.RegOrLogin.route)
//            }
        }
    }
    fun navigateBack(){
        navHostController.popBackStack()
    }
    fun addManualCoupon(){
        navHostController.navigate(Screen.EnterManuallyCoupons.route)
    }
    fun confirmManualCoupon(){

    }

    private fun popUpPowerBank(){

    }
}