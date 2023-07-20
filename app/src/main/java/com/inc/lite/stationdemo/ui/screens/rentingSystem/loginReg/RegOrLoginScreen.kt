package com.inc.lite.stationdemo.ui.screens.rentingSystem.loginReg


import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.inc.lite.stationdemo.R
import com.inc.lite.stationdemo.activities.MainActivity
import com.inc.lite.stationdemo.ui.components.BottomBar
import com.inc.lite.stationdemo.ui.components.StatusBar
import com.inc.lite.stationdemo.ui.components.TopBar
import com.inc.lite.stationdemo.model.StatusBarUiState
import com.inc.lite.stationdemo.ui.navigation.Screen
import com.inc.lite.stationdemo.ui.theme.MainColor
import com.inc.lite.stationdemo.util.AdjScreenSize

//@Preview(widthDp = 800, heightDp = 1280)
@Composable
fun RegOrLoginScreen(
    navHostController: NavHostController
) {

    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)
    val context = LocalContext.current
    val intent = Intent(context, MainActivity::class.java)

    Scaffold(
        topBar = {
            Column {
                StatusBar(uiState = StatusBarUiState())
                TopBar(
                    returnHomeText = "",
                    title = "",
                    onBackArrowClick = {
                        context.startActivity(intent)
                    }
                )
            }
        },
        bottomBar = {
            BottomBar()
        }
    ) {
        Box(
            Modifier
                .padding(it)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(Modifier) {
                AskingRow1()
                AskingRow2()
                AskingRow3()
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = size.dp(117)),
                    textAlign = TextAlign.Center,
                    text = "Do you already have an account?",
                    fontSize = size.sp(32),
                )
                Box(
                    Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        Modifier
                            .width(size.dp(513))
                            .padding(top = size.dp(45))
                            .height(size.dp(79)),
                    ) {
                        Button(
                            modifier = Modifier
                                .padding(end = size.dp(20))
                                .fillMaxHeight()
                                .weight(1f),
                            onClick = { navHostController.navigate(Screen.LoginScreen.route) },
                            colors = ButtonDefaults.buttonColors(containerColor = MainColor),
                            elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
                        ) {
                            Text(
                                text = "Yes, Log in",
                                fontSize = size.sp(24)
                            )
                        }
                        OutlinedButton(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(start = size.dp(20))
                                .weight(1f),
                            onClick = { navHostController.navigate(Screen.Registration.route) },
                            colors = ButtonDefaults.buttonColors(
                                contentColor = MainColor,
                                containerColor = Color.Transparent
                            ),
                            border = BorderStroke(1.dp, MainColor)
                        ) {
                            Text(
                                text = "No, register",
                                fontSize = size.sp(24),
                                fontWeight = FontWeight.Light
                            )
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun AskingRow1() {
    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Register or Log in with\nyour phone number",
            fontSize = size.sp(24)
        )
        Image(
            modifier = Modifier
                .padding(start = size.dp(50))
                .size(size.dp(179)),
            painter = painterResource(id = R.drawable.register_mini_image),
            contentDescription = ""
        )
    }
}
@Composable
fun AskingRow2() {
    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .padding(end = size.dp(63))
                .size(size.dp(159)),
            painter = painterResource(id = R.drawable.use_powerbank_mini),
            contentDescription = ""
        )
        Text(
            text = "Register or Log in with\nyour phone number",
            fontSize = size.sp(24)
        )
    }
}
@Composable
fun AskingRow3() {
    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = size.dp(26)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Leave a power bank\nat any nearest station",
            fontSize = size.sp(24)
        )
        Image(
            modifier = Modifier
                .padding(start = size.dp(72))
                .size(size.dp(139)),
            painter = painterResource(id = R.drawable.leave_powerbank_mini_image),
            contentDescription = ""
        )
    }
}