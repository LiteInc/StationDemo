package com.inc.lite.stationdemo.modules.ui.screens.rentingSystem.loginReg

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.inc.lite.stationdemo.modules.ui.components.EnterText
import com.inc.lite.stationdemo.modules.ui.viewModel.AuthViewModel
import com.inc.lite.stationdemo.modules.ui.viewModel.LoginViewModel
import com.inc.lite.stationdemo.util.AdjScreenSize

@Composable
fun EnterEmail(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
    viewModel: AuthViewModel,
) {
    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)
    val uiState by viewModel.uiState.collectAsState()
    EnterText(
        modifier = modifier.padding(vertical = size.dp(20)),
        title = "Please enter your email",
        value = uiState.email,
        onNextButtonClick = {
            viewModel.onValueEmailSubmit(it)
        }
    )
}
