package com.inc.lite.stationdemo.viewModels

import android.content.Context
import android.content.Intent
import android.util.Log
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
import com.inc.lite.stationdemo.ui.components.CouponItemData
import com.inc.lite.stationdemo.ui.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.inc.lite.stationdemo.MyApplication

@HiltViewModel
class RentViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    sharedInfo: SharedInformation
): ViewModel() {

    private val TAG = "RentViewModel"

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
    private val _couponsList: MutableState<MutableList<CouponItemData>>  = mutableStateOf(mutableListOf())
    val couponsList = _couponsList

    private val _couponAvailable: MutableState<Boolean>  = mutableStateOf(false)
    val couponAvailable = _couponAvailable
    private val _loadingString: MutableState<String>  = mutableStateOf(".")
    val loadingString = _loadingString

    private var isLoadingAnimationStop = true

    fun logOut(context: Context){
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
//        mainNavHost.navigate(Screen.RegOrLogin.route)
    }

    fun getNavHost(navHostMain: NavHostController, navHostController: NavHostController){
        mainNavHost = navHostMain
        this.navHostController = navHostController
    }

    fun putContext(c: Context){
        context = c
    }

    fun skipCoupons(){
        startRental()
    }

    fun startRental(){
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
            delay(2000)
            sendBroadcastToPopUp()
            _showStationSlotsInfo.value = true
            isLoadingAnimationStop = true
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
    fun confirmManualCoupon(coupon: String){
        if(couponsList.value.size < 4){
            couponsList.value.add(
                CouponItemData()
            )
        }
        navHostController.navigate(Screen.ChoseWitchCoupons.route)

    }

    fun loadingAnimation(){
        isLoadingAnimationStop = false
        viewModelScope.launch {
            while(!isLoadingAnimationStop){
                _loadingString.value = "."
                delay(500)
                _loadingString.value = ".."
                delay(500)
                _loadingString.value = "..."
                delay(400)
            }

        }
    }

    private fun sendBroadcastToPopUp() {
//        val intent = Intent("com.inc.lite.station.actions.hardCommands.Popup")
//
//        val localBroadcastManager = LocalBroadcastManager.getInstance(context)
//        localBroadcastManager.sendBroadcast(intent)
//
//        Log.d(TAG,"Pop command sended: intent${intent.action}")
    }


}