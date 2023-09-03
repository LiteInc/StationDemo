 package com.inc.lite.stationdemo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.inc.lite.stationdemo.ui.screens.rentingSystem.rent.ProfileScreen
import com.inc.lite.stationdemo.ui.screens.rentingSystem.loginReg.LoginScreen
import com.inc.lite.stationdemo.ui.screens.rentingSystem.loginReg.RegOrLoginScreen
import com.inc.lite.stationdemo.ui.screens.rentingSystem.loginReg.RegistrationScreen
import com.inc.lite.stationdemo.ui.screens.rentingSystem.rent.ChoosePaymentScreen
import com.inc.lite.stationdemo.ui.screens.rentingSystem.rent.CouponsScreen
import com.inc.lite.stationdemo.ui.screens.rentingSystem.rent.StartRentScreen
import com.inc.lite.stationdemo.viewModels.RentViewModel

@Composable
fun AuthNavGraph(
    navHostController: NavHostController
){

    val rentViewModel = hiltViewModel<RentViewModel>()
    NavHost(
        navController = navHostController,
        startDestination = Screen.RegOrLogin.route
    ){
        composable(Screen.RegOrLogin.route){
            RegOrLoginScreen(navHostController = navHostController)
        }
        composable(Screen.LoginScreen.route){
            LoginScreen(navHostController = navHostController)
        }
        composable(Screen.Registration.route){
//            CouponsScreen(navHostController = navHostController, viewModel = rentViewModel)
//            StartRentScreen(navHostController = navHostController, viewModel = rentViewModel)
            RegistrationScreen(navHostController = navHostController)
        }
//        composable(Screen.ProfilePage.route){
//            ProfileScreen(navHostController = navHostController)
//        }
        composable(Screen.StartRent.route){
            StartRentScreen(navHostController = navHostController, viewModel = rentViewModel)
        }
        composable(Screen.Coupons.route){
            CouponsScreen(navHostController = navHostController, viewModel = rentViewModel)
        }
        composable(Screen.ChoosePaymentMethod.route){
            ChoosePaymentScreen(navHostController = navHostController, viewModel = rentViewModel)
        }

    }
}




