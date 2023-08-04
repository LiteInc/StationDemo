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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
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
import com.inc.lite.stationdemo.ui.theme.mainTextStyle
import com.inc.lite.stationdemo.ui.theme.pingFangTCFamily
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
//                Text(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(top = size.dp(117)),
//                    textAlign = TextAlign.Center,
//                    text = "Do you already have an account?",
//                    fontSize = size.sp(32),
//                )
                Box(
                    Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        Modifier
                            .width(size.dp(513))
                            .padding(top = size.dp(117))
                            .padding(top = size.dp(45))
                            .height(size.dp(79)),
                    ) {
                        Button(
                            modifier = Modifier
                                .padding(end = size.dp(20))
                                .fillMaxHeight()
                                .weight(1f),
                            onClick = {
                                navHostController.navigate(Screen.LoginScreen.route)
                                      },
                            colors = ButtonDefaults.buttonColors(containerColor = MainColor),
                            elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.yes_login),
                                fontSize = size.sp(24),
                                style = mainTextStyle
                            )
                        }
                        OutlinedButton(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(start = size.dp(20))
                                .weight(1f),
                            onClick = {
//                                navHostController.navigate(Screen.Registration.route)
                                },
                            colors = ButtonDefaults.buttonColors(
                                contentColor = MainColor,
                                containerColor = Color.Transparent
                            ),
                            border = BorderStroke(1.dp, MainColor)
                        ) {
                            Text(
                                text = stringResource(id = R.string.no_register),
                                fontSize = size.sp(24),
                                fontWeight = FontWeight.Light,
                                style = mainTextStyle
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
            text = "手機號碼註冊，立即體驗Lite快速便\n捷租借！",
            fontSize = size.sp(24),
            style = mainTextStyle
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
            text = "輕鬆快速登入，手機沒電也能自由\n使用！",
            fontSize = size.sp(24),
            style = mainTextStyle
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
            text = "每天最高只需36元！下載Lite App\n邀請朋友領取免費充電券！",
            fontSize = size.sp(24),
            style = mainTextStyle
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