package com.inc.lite.stationdemo.ui.screens.rentingSystem.rent

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.inc.lite.stationdemo.R
import com.inc.lite.stationdemo.activities.MainActivity
import com.inc.lite.stationdemo.model.StatusBarUiState
import com.inc.lite.stationdemo.ui.components.BottomBar
import com.inc.lite.stationdemo.ui.components.StationPopUpDialogWindow
import com.inc.lite.stationdemo.ui.components.StatusBar
import com.inc.lite.stationdemo.ui.components.TopBar
import com.inc.lite.stationdemo.ui.navigation.LoginNavGraph
import com.inc.lite.stationdemo.ui.navigation.Screen
import com.inc.lite.stationdemo.ui.theme.mainTextStyle
import com.inc.lite.stationdemo.util.AdjScreenSize
import com.inc.lite.stationdemo.viewModels.RentViewModel

@Composable
fun StartRentScreen(
    navHostController: NavHostController,
    viewModel: RentViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)
    
    LaunchedEffect(true){
        viewModel.putContext(context)
        viewModel.popUpPowerBank(context)
        viewModel.loadingAnimation()
    }
    
    Scaffold(
        topBar = {
            Column {
                StatusBar(uiState = viewModel.statusBarUiState.value)
//                TopBar(
//                    returnHomeText = stringResource(id = R.string.logout),
//                    onReturnHomeClick = {
//                        viewModel.logOut(context)
//                    }
//                )
            }
        },
        bottomBar = {
            BottomBar()
        }
    ) {
        Image(
            modifier = Modifier.padding(it),
            painter = painterResource(id = R.drawable.rental_loading_back),
            contentDescription = ""
        )
        Column(
            Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.let_you_charge_up_now) + viewModel.loadingString.value,
                style = mainTextStyle,
                fontSize = size.sp(36)
            )
            Spacer(modifier = Modifier.height(size.dp(60)))
            Image(
                painter = painterResource(id = R.drawable.loading_circle_lite),
                contentDescription = "",
                modifier = Modifier
                    .width(size.dp(116))
                    .height(size.dp(129))
            )
        }
        StationPopUpDialogWindow(modifier = Modifier.padding(it),viewModel )

    }
}