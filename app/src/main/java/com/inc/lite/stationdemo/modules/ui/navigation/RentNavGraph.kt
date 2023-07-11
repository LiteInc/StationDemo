package com.inc.lite.stationdemo.modules.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation

fun NavGraphBuilder.rentNavBuilder(
    navHostController: NavHostController
){
    navigation(
        startDestination = Screen.Rent.route,
        route = RENT
    ){
        composable(Screen.Rent.route){

        }

    }
}