package com.inc.lite.stationdemo.modules.ui.screens

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.inc.lite.stationdemo.modules.ui.components.BottomBar
import com.inc.lite.stationdemo.modules.ui.components.ProgramItemComponent
import com.inc.lite.stationdemo.modules.ui.components.StatusBar
import com.inc.lite.stationdemo.modules.ui.components.TopBar
import com.inc.lite.stationdemo.modules.ui.models.StatusBarUiState

//@Preview(widthDp = 800, heightDp = 1280)
@Composable
fun ProgramsScreen(
    navHostController: NavHostController
) {

    Scaffold(
        bottomBar = { BottomBar() },
        topBar = { StatusBar(uiState = StatusBarUiState()) }

    ) {
        Surface(
            Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column {
                TopBar(onBackArrowClick = { navHostController.popBackStack() })
                Spacer(modifier = Modifier.height(60.dp))
                LazyVerticalGrid(
                    modifier = Modifier.padding(horizontal = 76.dp, vertical = 60.dp),
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