package com.inc.lite.stationdemo.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.inc.lite.stationdemo.ui.screens.rentingSystem.loginReg.EnterPassword
import com.inc.lite.stationdemo.ui.screens.rentingSystem.loginReg.EnterPhoneNumber
import com.inc.lite.stationdemo.ui.screens.rentingSystem.loginReg.EnterSMS
import com.inc.lite.stationdemo.viewModels.LoginViewModel


@Composable
fun LoginNavGraph(
    paddingValues: PaddingValues,
    navController: NavHostController,
    ) {
    NavHost(
        navController = navController,
        startDestination = Screen.LoginEnterNumber.route,
    ){
        composable(Screen.LoginEnterNumber.route){
            EnterPhoneNumber(
                Modifier.padding(paddingValues),
                navHostController = navController,
                viewModel = hiltViewModel<LoginViewModel>()
            )
        }
        composable(Screen.LoginEnterSMS.route){
            EnterSMS(
                Modifier.padding(paddingValues),
                navHostController = navController,
                viewModel = hiltViewModel<LoginViewModel>()
            )
        }
        composable(Screen.LoginEnterPass.route){
            EnterPassword(
                Modifier.padding(paddingValues),
                navHostController = navController,
                viewModel = hiltViewModel<LoginViewModel>()
            )
        }
    }
}