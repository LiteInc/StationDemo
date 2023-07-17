package com.inc.lite.stationdemo.modules.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.inc.lite.stationdemo.modules.ui.models.LoginUiState
import com.inc.lite.stationdemo.modules.ui.screens.rentingSystem.loginReg.EnterPhoneNumber
import com.inc.lite.stationdemo.modules.ui.screens.rentingSystem.loginReg.EnterSMS
import com.inc.lite.stationdemo.modules.ui.viewModel.LoginViewModel
import com.inc.lite.stationdemo.modules.ui.viewModel.RegistrationViewModel


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
    }
}