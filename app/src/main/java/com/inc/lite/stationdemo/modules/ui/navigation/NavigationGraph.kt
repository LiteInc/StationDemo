package com.inc.lite.stationdemo.modules.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.inc.lite.stationdemo.modules.ui.screens.MainScreen
import com.inc.lite.stationdemo.modules.ui.screens.ProgramsScreen

@Composable
fun NavigationGraph(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = Screen.Main.route){
        composable(Screen.Main.route){
            MainScreen(navHostController = navHostController)
        }
        composable(Screen.Programs.route){
            ProgramsScreen(navHostController = navHostController)
        }
    }
}

sealed class Screen(val route: String){
    object Main: Screen("main_screen")
    object Programs: Screen("programs_screen")
}