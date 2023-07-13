package com.inc.lite.stationdemo.modules.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.inc.lite.stationdemo.modules.ui.models.LoginUiState
import com.inc.lite.stationdemo.modules.ui.screens.rentingSystem.loginReg.EnterPhoneNumber



@Composable
fun RegistrationNavGraph(
    navHostController: NavHostController,
    uiState: LoginUiState
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.LoginEnterNumber.route
    ){
        composable(Screen.LoginEnterNumber.route){
//            EnterPhoneNumber(navHostController = navHostController)
        }
    }
}