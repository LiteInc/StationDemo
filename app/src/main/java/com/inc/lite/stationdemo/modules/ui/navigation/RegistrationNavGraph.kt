package com.inc.lite.stationdemo.modules.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.regNavBuilder(
    navHostController: NavHostController
){
    navigation(
        startDestination = Screen.LoginEnterNumber.route,
        route = REGISTER
    ){
        composable(Screen.LoginEnterNumber.route){

        }
        composable(Screen.LoginEnterSMS.route){

        }
        composable(Screen.LoginEnterPass.route){

        }
        composable(Screen.RegEnterEmail.route){

        }
    }
}