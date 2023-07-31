package com.inc.lite.stationdemo.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.inc.lite.stationdemo.ui.screens.rentingSystem.loginReg.EnterEmail
import com.inc.lite.stationdemo.ui.screens.rentingSystem.loginReg.EnterNickName
import com.inc.lite.stationdemo.ui.screens.rentingSystem.loginReg.EnterPassword
import com.inc.lite.stationdemo.ui.screens.rentingSystem.loginReg.EnterPhoneNumber
import com.inc.lite.stationdemo.ui.screens.rentingSystem.loginReg.EnterSMS
import com.inc.lite.stationdemo.viewModels.LoginViewModel
import com.inc.lite.stationdemo.viewModels.RegistrationViewModel


@Composable
fun RegistrationNavGraph(
    paddingValues: PaddingValues,
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.LoginEnterNumber.route
    ){
        composable(Screen.LoginEnterNumber.route){
            EnterPhoneNumber(
                modifier = Modifier.padding(paddingValues),
                navHostController = navHostController,
                viewModel = hiltViewModel<RegistrationViewModel>()
            )
        }
        composable(Screen.LoginEnterSMS.route){
            EnterSMS(
                modifier = Modifier.padding(paddingValues),
                navHostController = navHostController,
                viewModel = hiltViewModel<RegistrationViewModel>()
            )
        }

        composable(Screen.LoginEnterPass.route){
            EnterPassword(
                Modifier.padding(paddingValues),
                navHostController = navHostController,
                viewModel = hiltViewModel<LoginViewModel>(),
                onNextClick = {
                    navHostController.navigate(Screen.RegEnterNickName.route)
                }
            )
        }

        composable(Screen.RegEnterEmail.route){
            EnterEmail(
                Modifier.padding(paddingValues),
                navHostController = navHostController,
                viewModel = hiltViewModel<LoginViewModel>()
            )
        }
        composable(Screen.RegEnterNickName.route){
            EnterNickName(
                Modifier.padding(paddingValues),
                navHostController = navHostController,
                viewModel = hiltViewModel<LoginViewModel>()
            )
        }
    }
}