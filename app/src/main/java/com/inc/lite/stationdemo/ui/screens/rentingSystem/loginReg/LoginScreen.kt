package com.inc.lite.stationdemo.ui.screens.rentingSystem.loginReg

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.inc.lite.stationdemo.R
import com.inc.lite.stationdemo.activities.MainActivity
import com.inc.lite.stationdemo.ui.components.BottomBar
import com.inc.lite.stationdemo.ui.components.StatusBar
import com.inc.lite.stationdemo.ui.components.TopBar
import com.inc.lite.stationdemo.model.StatusBarUiState
import com.inc.lite.stationdemo.ui.navigation.LoginNavGraph
import com.inc.lite.stationdemo.ui.navigation.Screen
import com.inc.lite.stationdemo.ui.screens.home.Keyboard
import com.inc.lite.stationdemo.ui.screens.home.keyboardAsState
import com.inc.lite.stationdemo.util.AdjScreenSize
import com.inc.lite.stationdemo.viewModels.LoginViewModel


@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navHostController: NavHostController
) {

    val context = LocalContext.current
    val intent = Intent(context, MainActivity::class.java)

    val isKeyboardOpen by keyboardAsState()
    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)

    Scaffold(
        modifier = Modifier.padding(bottom = if (isKeyboardOpen == Keyboard.Opened) size.dp(550) else size.dp(0) ),
        topBar = {
            Column {
                StatusBar(uiState = viewModel.statusBarUiState.value)
                TopBar(
                    title = stringResource(id = R.string.log_in),
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
        LoginNavGraph(
            paddingValues = it,
            navController = rememberNavController(),
            viewModel = viewModel,
            mainNavHost = navHostController
        )
    }
}


@Preview
@Composable
fun LoginPreview() {
    LoginScreen(navHostController = rememberNavController())
}