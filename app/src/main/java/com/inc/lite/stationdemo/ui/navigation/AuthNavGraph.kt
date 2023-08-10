package com.inc.lite.stationdemo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.inc.lite.stationdemo.ui.screens.rentingSystem.rent.ProfileScreen
import com.inc.lite.stationdemo.ui.screens.rentingSystem.loginReg.LoginScreen
import com.inc.lite.stationdemo.ui.screens.rentingSystem.loginReg.RegOrLoginScreen
import com.inc.lite.stationdemo.ui.screens.rentingSystem.loginReg.RegistrationScreen
import com.inc.lite.stationdemo.ui.screens.rentingSystem.rent.CouponsScreen
import com.inc.lite.stationdemo.ui.screens.rentingSystem.rent.StartRentScreen

@Composable
fun AuthNavGraph(
    navHostController: NavHostController
){
    NavHost(
        navController = navHostController,
        startDestination = Screen.RegOrLogin.route
    ){
        composable(Screen.RegOrLogin.route){
            RegOrLoginScreen(navHostController)
        }
        composable(Screen.LoginScreen.route){
            LoginScreen(navHostController = navHostController)
        }
        composable(Screen.Registration.route){
            RegistrationScreen(navHostController = navHostController)
        }
        composable(Screen.ProfilePage.route){
            ProfileScreen(navHostController = navHostController)
        }
        composable(Screen.StartRent.route){
            StartRentScreen(navHostController = navHostController)
        }
        composable(Screen.Coupons.route){
            CouponsScreen(navHostController = navHostController)
        }

    }
}


const val RENT = "rent"
const val ACCOUNT = "account"

