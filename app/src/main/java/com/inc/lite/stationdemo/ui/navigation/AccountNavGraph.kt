package com.inc.lite.stationdemo.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation

fun NavGraphBuilder.accountNavBuilder(
    navHostController: NavHostController
){
    navigation(
        startDestination = Screen.AccountPage.route,
        route = ACCOUNT
    ){
        composable(Screen.AccountPage.route){

        }
    }
}