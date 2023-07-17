package com.inc.lite.stationdemo.modules.ui.screens.rentingSystem.loginReg

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.inc.lite.stationdemo.modules.ui.components.BottomBar
import com.inc.lite.stationdemo.modules.ui.components.StatusBar
import com.inc.lite.stationdemo.modules.ui.components.TopBar
import com.inc.lite.stationdemo.modules.ui.models.StatusBarUiState
import com.inc.lite.stationdemo.modules.ui.navigation.HOME_ROUTE
import com.inc.lite.stationdemo.modules.ui.navigation.LoginNavGraph
import com.inc.lite.stationdemo.modules.ui.navigation.Screen
import com.inc.lite.stationdemo.modules.ui.viewModel.LoginViewModel


@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
//    val uiState by viewModel.uiState.collectAsState()
    Scaffold(
        topBar = {
            Column {
                StatusBar(uiState = StatusBarUiState())
                TopBar(
                    title = "Log in",
                    onBackArrowClick = {
                        navHostController.navigate(Screen.RegOrLogin.route)
                    },
                    onReturnHomeClick = {
                        navHostController.navigate(HOME_ROUTE)
                    }
                )
            }
        },
        bottomBar = {
            BottomBar()
        }
    ) {
        LoginNavGraph(
            paddingValues = it,
            navController = rememberNavController()
        )
    }
}


@Preview
@Composable
fun LoginPreview() {
    LoginScreen(navHostController = rememberNavController())
}