package com.inc.lite.stationdemo.ui.screens.rentingSystem.loginReg

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.inc.lite.stationdemo.activities.MainActivity
import com.inc.lite.stationdemo.ui.components.BottomBar
import com.inc.lite.stationdemo.ui.components.StatusBar
import com.inc.lite.stationdemo.ui.components.TopBar
import com.inc.lite.stationdemo.model.StatusBarUiState
import com.inc.lite.stationdemo.ui.navigation.RegistrationNavGraph
import com.inc.lite.stationdemo.ui.navigation.Screen

@Composable
fun RegistrationScreen(
    navHostController: NavHostController
) {
    val context = LocalContext.current
    val intent = Intent(context, MainActivity::class.java)

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
                        context.startActivity(intent)
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