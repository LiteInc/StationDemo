package com.inc.lite.stationdemo.modules.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.inc.lite.stationdemo.modules.ui.components.BottomBar
import com.inc.lite.stationdemo.modules.ui.components.ProgramItemComponent
import com.inc.lite.stationdemo.modules.ui.components.StatusBar
import com.inc.lite.stationdemo.modules.ui.components.TopBar
import com.inc.lite.stationdemo.modules.ui.models.StatusBarUiState
import com.inc.lite.stationdemo.modules.ui.navigation.Screen

//@Preview(widthDp = 800, heightDp = 1280)
@Composable
fun ProgramsScreen(
    navHostController: NavHostController
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp + 72
    Scaffold(
        bottomBar = { BottomBar() },
        topBar = {
            Column() {
                StatusBar(uiState = StatusBarUiState())
                TopBar(
                    onBackArrowClick = { navHostController.popBackStack() },
                    onReturnHomeClick = { navHostController.navigate(Screen.Main.route) }
                )
            }
        }
    ) {
        Surface(
            Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column {
                Spacer(modifier = Modifier.height((screenHeight/21.3).dp))
                LazyVerticalGrid(
                    modifier = Modifier.padding(horizontal = (screenHeight/16.8).dp, vertical = (screenHeight/21.3).dp),
                    columns = GridCells.Fixed(4),
                ){
                    items(6){
                        ProgramItemComponent(navHostController = navHostController)
                    }
                }
            }
        }
    }
}