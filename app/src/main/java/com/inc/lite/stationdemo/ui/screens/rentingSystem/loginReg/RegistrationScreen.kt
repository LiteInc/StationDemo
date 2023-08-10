package com.inc.lite.stationdemo.ui.screens.rentingSystem.loginReg

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.inc.lite.stationdemo.R
import com.inc.lite.stationdemo.activities.MainActivity
import com.inc.lite.stationdemo.ui.components.BottomBar
import com.inc.lite.stationdemo.ui.components.StatusBar
import com.inc.lite.stationdemo.ui.components.TopBar
import com.inc.lite.stationdemo.model.StatusBarUiState
import com.inc.lite.stationdemo.ui.navigation.RegistrationNavGraph
import com.inc.lite.stationdemo.ui.navigation.Screen
import com.inc.lite.stationdemo.viewModels.RegistrationViewModel

@Composable
fun RegistrationScreen(
    viewModel: RegistrationViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    val context = LocalContext.current
    val intent = Intent(context, MainActivity::class.java)

    Scaffold(
        topBar = {
            Column {
                StatusBar(uiState = StatusBarUiState())
                TopBar(
                    title = stringResource(id = R.string.register),
                    onBackArrowClick = {
                        navHostController.navigate(Screen.RegOrLogin.route)
                        viewModel.cleanUser()
                    },
                    onReturnHomeClick = {
                        viewModel.cleanUser()
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
            navHostController = rememberNavController(),
            mainNavHost = navHostController,
            viewModel = viewModel
        )
    }
}