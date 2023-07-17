package com.inc.lite.stationdemo.modules.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.inc.lite.stationdemo.modules.ui.screens.rentingSystem.loginReg.LoginScreen
import com.inc.lite.stationdemo.modules.ui.screens.rentingSystem.loginReg.RegOrLoginScreen
import com.inc.lite.stationdemo.modules.ui.screens.rentingSystem.loginReg.RegistrationScreen

fun NavGraphBuilder.rentingNavBuilder(
    navHostController: NavHostController
){
    navigation(
        startDestination = Screen.RegOrLogin.route,
        route = RENTING_ROUTE
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
        composable(RENT){
            rentNavBuilder(navHostController)
        }
        composable(ACCOUNT){
            accountNavBuilder(navHostController)
        }

    }
}


const val RENT = "rent"
const val ACCOUNT = "account"

