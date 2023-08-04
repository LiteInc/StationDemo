package com.inc.lite.stationdemo.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.inc.lite.stationdemo.ui.screens.rentingSystem.loginReg.CreatePassword
import com.inc.lite.stationdemo.ui.screens.rentingSystem.loginReg.EnterEmail
import com.inc.lite.stationdemo.ui.screens.rentingSystem.loginReg.EnterNickName
import com.inc.lite.stationdemo.ui.screens.rentingSystem.loginReg.EnterPassword
import com.inc.lite.stationdemo.ui.screens.rentingSystem.loginReg.EnterPhoneNumber
import com.inc.lite.stationdemo.ui.screens.rentingSystem.loginReg.EnterSMS
import com.inc.lite.stationdemo.viewModels.LoginViewModel


@Composable
fun LoginNavGraph(
    paddingValues: PaddingValues,
    navController: NavHostController,
    viewModel: LoginViewModel,
    mainNavHost: NavHostController
    ) {
    NavHost(
        navController = navController,
        startDestination = Screen.LoginEnterNumber.route,
    ){
        composable(Screen.LoginEnterNumber.route){
            EnterPhoneNumber(
                Modifier.padding(paddingValues),
                navHostController = navController,
                viewModel = viewModel,
                mainNavHost = mainNavHost
            )
        }
        composable(Screen.LoginEnterSMS.route){
            EnterSMS(
                Modifier.padding(paddingValues),
                navHostController = navController,
                viewModel = viewModel
            )
        }
        composable(Screen.LoginEnterPass.route){
            EnterPassword(
                Modifier.padding(paddingValues),
                navHostController = navController,
                viewModel = viewModel
            )
        }
        composable(Screen.LoginCreatePass.route){
            CreatePassword(
                Modifier.padding(paddingValues),
                viewModel = viewModel
            )
        }
        composable(Screen.RegEnterEmail.route){
            EnterEmail(
                Modifier.padding(paddingValues),
                viewModel = viewModel
            )
        }
        composable(Screen.RegEnterNickName.route){
            EnterNickName(
                Modifier.padding(paddingValues),
                navHostController = navController,
                viewModel = viewModel
            )
        }
    }
}