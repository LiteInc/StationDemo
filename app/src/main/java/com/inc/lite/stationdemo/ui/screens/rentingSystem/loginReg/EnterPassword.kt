package com.inc.lite.stationdemo.ui.screens.rentingSystem.loginReg

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.inc.lite.stationdemo.ui.theme.RedInfoColor
import com.inc.lite.stationdemo.ui.theme.mainTextStyle
import com.inc.lite.stationdemo.viewModels.AuthViewModel
import com.inc.lite.stationdemo.util.AdjScreenSize

//@Preview(widthDp = 800, heightDp = 1280, showBackground = false)
@Composable
fun EnterPassword(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
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
                        if(!viewModel.isLoading.value){

                            isButtonEnabled = viewModel.password.value.toCharArray().last() != ' '
                            Button(
                                modifier = Modifier
                                    .height(size.dp(80)),
                                enabled = isButtonEnabled,
                                onClick = {
                                    viewModel.confirmCurrentPassword()
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MainColor,
                                    disabledContainerColor = MainColor.copy(alpha = 0.6f)
                                )
                            ) {
                                Text(
                                    modifier = Modifier.padding(horizontal = size.dp(55)),
                                    text = stringResource(id = R.string.log_in),
                                    fontSize = size.sp(24)
                                )
                            }

                        }
                        LoadingIndicator(isLoading = viewModel.isLoading.value)
                    }
                    Spacer(modifier = Modifier.height(size.dp(40)))
                    DigitKeyboard(
                        onDigitClicked = {key ->
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
                    text = stringResource(id = R.string.enter_your_password),
                    fontSize = size.sp(28),
                    color = LightGrayColor,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(size.dp(70)))
                PassEntering(
                    password = password.toCharArray()
                )
                Spacer(modifier = Modifier.height(size.dp(60)))
                AnimatedVisibility(
                    modifier = Modifier,
                    visible = viewModel.isCodeError.value
                ) {
                    InfoPassword(message = "Wrong password")
                }
            }
        }
    }
}

@Preview
@Composable
fun PassEntering(
    modifier: Modifier = Modifier,
    password: CharArray = charArrayOf(' ',' ',' ',' ',' ',' '),
) {
    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)


    Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
        Row {
            for(n in 0..5){
                if (password[n] == ' '){
                    PasswordItem(
                        digit = password[n].toString()
                    )
                }else{
                    PasswordItem(
                        digit = password[n].toString(),
                        color = MainColor
                    )
                }
            }
        }
    }
}
@Preview
@Composable
fun InfoPassword(
    modifier: Modifier = Modifier,
    message: String = ""
) {
    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)
    Row(
        modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .height(size.dp(40))
                .width(size.dp(40))
                .padding(
                    end = size.dp(16),
                ),
            painter = painterResource(id = R.drawable.info_outlined),
            contentDescription = ""
        )
        Text(
            text = message,
            modifier = Modifier,
            fontSize = size.sp(24),
            color = RedInfoColor,
            style = mainTextStyle
        )
    }
}