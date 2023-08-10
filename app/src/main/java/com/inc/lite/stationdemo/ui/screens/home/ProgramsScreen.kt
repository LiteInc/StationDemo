package com.inc.lite.stationdemo.ui.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.inc.lite.stationdemo.R
import com.inc.lite.stationdemo.ui.components.BottomBar
import com.inc.lite.stationdemo.ui.components.ProgDialogTimer
import com.inc.lite.stationdemo.ui.components.ProgramItemComponent
import com.inc.lite.stationdemo.ui.components.StatusBar
import com.inc.lite.stationdemo.ui.components.TopBar
import com.inc.lite.stationdemo.ui.navigation.Screen
import com.inc.lite.stationdemo.util.AdjScreenSize
import com.inc.lite.stationdemo.viewModels.HomeViewModel

//@Preview(widthDp = 800, heightDp = 1280)

@Composable
fun ProgramsScreen(
    navHostController: NavHostController,
    viewModel: HomeViewModel
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp + 72
    val size = AdjScreenSize(configuration)

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(true){
        viewModel.startTimer()
    }

    Scaffold(
        bottomBar = { BottomBar() },
        topBar = {
            Column() {
                StatusBar(uiState = uiState.statusUiState)
                TopBar(
                    onBackArrowClick = { navHostController.popBackStack() },
                    onReturnHomeClick = { navHostController.navigate(Screen.Main.route) },
                    title = stringResource(id = R.string.programs_title)
                )
            }
        }
    ) {
        Surface(
            Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            AnimatedVisibility(visible = viewModel.isShowProgramsTimer.value) {
                ProgDialogTimer(
                    onContinueClick = { viewModel.onContinueTimerClick() },
                    onExitClick = { viewModel.onExitTimerClick() },
                    timerItem = viewModel.timerTime.value
                )
            }
            Column {
                Spacer(modifier = Modifier.height((screenHeight/21.3).dp))
                LazyVerticalGrid(
                    modifier = Modifier.padding(horizontal = (screenHeight/16.8).dp, vertical = (screenHeight/21.3).dp),
                    columns = GridCells.Fixed(4),
                    horizontalArrangement = Arrangement.Center
                ){
                    items(uiState.programsList){
                        ProgramItemComponent(navHostController = navHostController, programItem = it, viewModel = viewModel)
                    }
                }
            }
        }
    }
}