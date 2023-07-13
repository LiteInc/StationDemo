package com.inc.lite.stationdemo.modules.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.inc.lite.stationdemo.modules.ui.models.LoginUiState
import com.inc.lite.stationdemo.modules.ui.screens.rentingSystem.loginReg.EnterPhoneNumber
import com.inc.lite.stationdemo.modules.ui.screens.rentingSystem.loginReg.EnterSMS


@Composable
fun LoginNavGraph(
    paddingValues: PaddingValues,
    navController: NavHostController,
    uiState: LoginUiState
    ) {
    NavHost(
        navController = navController,
        startDestination = Screen.LoginEnterNumber.route,
    ){
        composable(Screen.LoginEnterNumber.route){
            EnterPhoneNumber(Modifier.padding(paddingValues), navHostController = navController)
        }
        composable(Screen.LoginEnterSMS.route){
            EnterSMS(Modifier.padding(paddingValues), navHostController = navController)
        }
        composable(Screen.LoginEnterPass.route){

        }
    }
}