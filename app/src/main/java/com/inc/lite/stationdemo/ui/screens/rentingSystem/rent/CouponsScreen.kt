package com.inc.lite.stationdemo.ui.screens.rentingSystem.rent

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
import com.inc.lite.stationdemo.model.StatusBarUiState
import com.inc.lite.stationdemo.ui.components.BottomBar
import com.inc.lite.stationdemo.ui.components.StatusBar
import com.inc.lite.stationdemo.ui.components.TopBar
import com.inc.lite.stationdemo.ui.navigation.CouponsNavGraph
import com.inc.lite.stationdemo.viewModels.RentViewModel

@Composable
fun CouponsScreen(
    navHostController: NavHostController,
    viewModel: RentViewModel,
) {

    val context = LocalContext.current
    val intent = Intent(context, MainActivity::class.java)
    val navHost = rememberNavController()
    viewModel.getNavHost(
        navHostMain = navHostController,
        navHostController = navHost
    )
    Scaffold(
        topBar = {
            Column {
                StatusBar(uiState = viewModel.statusBarUiState.value)
                TopBar(
                    title = stringResource(id = viewModel.topTitle.value),
                    onBackArrowClick = {
                        viewModel.navigateBack()
                    },
                    onReturnHomeClick = {
                        viewModel.logOut(context)
                    }
                )
            }
        },
        bottomBar = {
            BottomBar()
        }
    ) {
        CouponsNavGraph(
            paddingValues = it,
            navController = navHost,
            viewModel = viewModel,
            mainNavHost = navHostController
        )
    }
}