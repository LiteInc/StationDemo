package com.inc.lite.stationdemo.modules.ui.screens.rentingSystem.loginReg

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.inc.lite.stationdemo.modules.ui.components.BottomBar
import com.inc.lite.stationdemo.modules.ui.components.StatusBar
import com.inc.lite.stationdemo.modules.ui.components.TopBar
import com.inc.lite.stationdemo.modules.ui.models.StatusBarUiState
import com.inc.lite.stationdemo.modules.ui.navigation.HOME_ROUTE
import com.inc.lite.stationdemo.modules.ui.navigation.RegistrationNavGraph
import com.inc.lite.stationdemo.modules.ui.navigation.Screen

@Composable
fun RegistrationScreen(
    navHostController: NavHostController
) {
    Scaffold(
        topBar = {
            Column {
                StatusBar(uiState = StatusBarUiState())
                TopBar(
                    title = "Registration",
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
        RegistrationNavGraph(
            paddingValues = it,
            navHostController = rememberNavController()
        )
    }
}