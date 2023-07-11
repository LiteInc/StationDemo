package com.inc.lite.stationdemo.modules.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun RootNavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = HOME_ROUTE,
        route = ROOT_ROUTE
    ){
        homeNavGraph(navHostController = navHostController)
        rentingNavBuilder(navHostController = navHostController)
    }
}

const val RENTING_ROUTE = "renting"
const val ROOT_ROUTE = "root"
const val HOME_ROUTE = "home"