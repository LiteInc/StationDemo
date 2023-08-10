package com.inc.lite.stationdemo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.inc.lite.stationdemo.ui.screens.home.MainScreen
import com.inc.lite.stationdemo.ui.screens.home.ProgramsScreen
import com.inc.lite.stationdemo.ui.screens.home.WebViewScreen
import com.inc.lite.stationdemo.viewModels.HomeViewModel

@Composable
fun HomeNavGraph(
    navHostController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Main.route
    ){
        composable(Screen.Main.route){
            MainScreen(navHostController = navHostController, viewModel = viewModel)
        }
        composable(Screen.Programs.route){
            ProgramsScreen(navHostController = navHostController, viewModel = viewModel)
        }
        composable(Screen.WebView.route){
            WebViewScreen(navHostController = navHostController, viewModel = viewModel)
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



