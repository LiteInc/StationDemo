package com.inc.lite.stationdemo.modules.ui.screens.rentingSystem.loginReg

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.inc.lite.stationdemo.modules.ui.components.EnterText
import com.inc.lite.stationdemo.modules.ui.navigation.Screen
import com.inc.lite.stationdemo.modules.ui.viewModel.AuthViewModel
import com.inc.lite.stationdemo.util.AdjScreenSize


@Composable
fun EnterNickName(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
    viewModel: AuthViewModel,
) {
    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)
    val uiState by viewModel.uiState.collectAsState()
    EnterText(
        modifier = modifier.padding(vertical = size.dp(20)),
        title = "What`s your nickname?",
        value = uiState.nickName,
        onNextButtonClick = {
            viewModel.onValueNickNameSubmit(it)
            navHostController.navigate(Screen.RegEnterEmail.route)
        }
    )
}