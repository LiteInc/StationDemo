package com.inc.lite.stationdemo.ui.screens.rentingSystem.loginReg

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.inc.lite.stationdemo.R
import com.inc.lite.stationdemo.ui.components.DigitKeyboard
import com.inc.lite.stationdemo.ui.components.LoadingIndicator
import com.inc.lite.stationdemo.ui.components.PasswordItem
import com.inc.lite.stationdemo.ui.navigation.Screen
import com.inc.lite.stationdemo.ui.theme.LightGrayColor
import com.inc.lite.stationdemo.ui.theme.MainColor
import com.inc.lite.stationdemo.ui.theme.mainTextStyle
import com.inc.lite.stationdemo.ui.theme.pingFangTCFamily
import com.inc.lite.stationdemo.viewModels.AuthViewModel
import com.inc.lite.stationdemo.util.AdjScreenSize

//@Preview(widthDp = 800, heightDp = 1280, showBackground = false)
@Composable
fun CreatePassword(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel,
) {
    val uiState by viewModel.uiState.collectAsState()
    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)

    var password by viewModel.password
    var isButtonEnabled by remember { mutableStateOf(false) }

    Surface(
        modifier
            .fillMaxSize()
            .padding(
                top = size.dp(60)
            ),
    ) {
        Scaffold(
            bottomBar = {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer(modifier = Modifier.height(size.dp(20)))
                    Box(
                        Modifier,
                        contentAlignment = Alignment.Center
                    ){
                        isButtonEnabled = viewModel.password.value.toCharArray().last() != ' '
                        Button(
                            modifier = Modifier
                                .height(size.dp(80)),
                            enabled = isButtonEnabled,
                            onClick = {
                                viewModel.createPassword()
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MainColor,
                                disabledContainerColor = MainColor.copy(alpha = 0.6f)
                            )
                        ) {
                            Text(
                                modifier = Modifier.padding(horizontal = size.dp(55)),
                                text = stringResource(id = R.string.confirm),
                                fontSize = size.sp(24)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(size.dp(40)))
                    DigitKeyboard(
                        onDigitClicked = {key ->
                            viewModel.onKeyBoardClick(key, Screen.LoginEnterPass)
                            password = viewModel.addValueByKey(password,key)
                        }
                    )
                }
            }
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(top = size.dp(120)),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.create_your_password),
                    fontSize = size.sp(28),
                    color = LightGrayColor,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(size.dp(70)))
                PassEntering(
                    password = password.toCharArray()
                )

            }
        }
    }
}

