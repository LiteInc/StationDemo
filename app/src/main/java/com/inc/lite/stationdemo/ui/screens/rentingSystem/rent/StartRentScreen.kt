package com.inc.lite.stationdemo.ui.screens.rentingSystem.rent

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.inc.lite.stationdemo.ui.components.StatusBar
import com.inc.lite.stationdemo.ui.components.TopBar
import com.inc.lite.stationdemo.ui.navigation.LoginNavGraph
import com.inc.lite.stationdemo.ui.navigation.Screen
import com.inc.lite.stationdemo.util.AdjScreenSize
import com.inc.lite.stationdemo.viewModels.RentViewModel

@Composable
fun StartRentScreen(
    navHostController: NavHostController,
    viewModel: RentViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    val context = LocalContext.current
    val intent = Intent(context, MainActivity::class.java)
    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)
    
    Scaffold(
        topBar = {
            Column {
                StatusBar(uiState = StatusBarUiState())
                TopBar(
                    returnHomeText = stringResource(id = R.string.logout),
                    onReturnHomeClick = {
                        viewModel.logOut()
                        context.startActivity(intent)

                    }
                )
            }
        },
        bottomBar = {
            BottomBar()
        }
    ) {
        Image(
            painter = painterResource(id = R.drawable.rent_background),
            contentDescription = ""
        )
        Column(Modifier.padding(it)) {

        }
    }
}