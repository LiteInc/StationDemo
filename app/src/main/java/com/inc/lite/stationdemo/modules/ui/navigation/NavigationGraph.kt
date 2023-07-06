package com.inc.lite.stationdemo.modules.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.inc.lite.stationdemo.modules.ui.screens.MainScreen
import com.inc.lite.stationdemo.modules.ui.screens.ProgramsScreen
import com.inc.lite.stationdemo.modules.ui.screens.WebViewScreen

@Composable
fun NavigationGraph(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = Screen.WebView.route){
        composable(Screen.Main.route){
            MainScreen(navHostController = navHostController)
        }
        composable(Screen.Programs.route){
            ProgramsScreen(navHostController = navHostController)
        }
        composable(Screen.WebView.route){
            WebViewScreen(navHostController = navHostController, "https://www.google.com.tw/?hl=zh_TW")
        }
//        composable(
//            route = Screen.WebView.route +"/{url}",
//            arguments = listOf(navArgument("url") { type = NavType.StringType })
//        ) { entry ->
//            WebViewScreen(
//                url = entry.arguments?.getString("url") ?: "https://www.google.com.tw/?hl=zh_TW",
//                navHostController = navHostController
//            )
//        }
    }
}



sealed class Screen(val route: String){
    object Main: Screen("main_screen")
    object Programs: Screen("programs_screen")
    object WebView: Screen("webview_screen")
}