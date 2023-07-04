package com.inc.lite.stationdemo.modules.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.inc.lite.stationdemo.modules.ui.screens.MainScreen

@Composable
fun NavigationGraph(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = Screen.Main.route){
        composable(Screen.Main.route){
            MainScreen()
        }
    }
}

sealed class Screen(val route: String){
    object Main: Screen("main_screen")
}