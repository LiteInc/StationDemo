package com.inc.lite.stationdemo.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.inc.lite.stationdemo.ui.screens.rentingSystem.loginReg.EnterPhoneNumber
import com.inc.lite.stationdemo.ui.screens.rentingSystem.rent.coupons.AskCoupons
import com.inc.lite.stationdemo.ui.screens.rentingSystem.rent.coupons.ChooseWhichCouponsScreen
import com.inc.lite.stationdemo.viewModels.LoginViewModel
import com.inc.lite.stationdemo.viewModels.RentViewModel

@Composable
fun CouponsNavGraph(
    paddingValues: PaddingValues,
    navController: NavHostController,
    viewModel: RentViewModel,
    mainNavHost: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.AskCoupons.route,
    ) {
        composable(Screen.AskCoupons.route) {
            AskCoupons(
                viewModel = viewModel,
                paddingValues = paddingValues
            )
        }
        composable(Screen.ChoseWitchCoupons.route) {
            ChooseWhichCouponsScreen(
                viewModel = viewModel,
                paddingValues = paddingValues
            )
        }
        composable(Screen.DoYouHaveCoupons.route) {}
    }
}
