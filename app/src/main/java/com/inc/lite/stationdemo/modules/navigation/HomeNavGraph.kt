package com.inc.lite.stationdemo.modules.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.inc.lite.stationdemo.modules.screens.home.MainScreen
import com.inc.lite.stationdemo.modules.screens.home.ProgramsScreen
import com.inc.lite.stationdemo.modules.screens.home.WebViewScreen

@Composable
fun HomeNavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Main.route
    ){
        composable(Screen.Main.route){
            MainScreen(navHostController = navHostController)
        }
        composable(Screen.Programs.route){
            ProgramsScreen(navHostController = navHostController)
        }
        composable(Screen.WebView.route){
            WebViewScreen(navHostController = navHostController, "https://www.google.com.tw/?hl=zh_TW")
        }
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



