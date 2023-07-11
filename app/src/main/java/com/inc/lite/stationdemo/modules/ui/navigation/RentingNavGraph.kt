package com.inc.lite.stationdemo.modules.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.rentingNavBuilder(
    navHostController: NavHostController
){
    navigation(
        startDestination = Screen.RegOrLogin.route,
        route = RENTING_ROUTE
    ){
        composable(Screen.RegOrLogin.route){

        }
        composable(LOG_IN){
            loginNavBuilder(navHostController)
        }
        composable(REGISTER){
            regNavBuilder(navHostController)
        }
        composable(RENT){
            rentNavBuilder(navHostController)
        }
        composable(ACCOUNT){
            accountNavBuilder(navHostController)
        }

    }
}

const val LOG_IN = "login"
const val REGISTER = "register"
const val RENT = "rent"
const val ACCOUNT = "account"

