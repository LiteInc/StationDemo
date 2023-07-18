package com.inc.lite.stationdemo.modules.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.inc.lite.stationdemo.modules.screens.rentingSystem.loginReg.LoginScreen
import com.inc.lite.stationdemo.modules.screens.rentingSystem.loginReg.RegOrLoginScreen
import com.inc.lite.stationdemo.modules.screens.rentingSystem.loginReg.RegistrationScreen

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

