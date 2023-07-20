package com.inc.lite.stationdemo.ui.screens.rentingSystem.loginReg

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.inc.lite.stationdemo.ui.components.EnterText
import com.inc.lite.stationdemo.viewModels.AuthViewModel
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
        onNextButtonClick = {
            viewModel.onValueEmailSubmit(it)
        }
    )
}
