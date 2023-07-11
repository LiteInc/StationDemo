package com.inc.lite.stationdemo.modules.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.inc.lite.stationdemo.modules.ui.screens.MainScreen
import com.inc.lite.stationdemo.modules.ui.screens.ProgramsScreen
import com.inc.lite.stationdemo.modules.ui.screens.WebViewScreen


fun NavGraphBuilder.homeNavGraph(
    navHostController: NavHostController
) {
    navigation(
        startDestination = Screen.Main.route,
        route = HOME_ROUTE
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



