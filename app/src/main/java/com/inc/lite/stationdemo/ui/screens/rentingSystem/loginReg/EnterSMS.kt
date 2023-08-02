package com.inc.lite.stationdemo.ui.screens.rentingSystem.loginReg

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.inc.lite.stationdemo.R
import com.inc.lite.stationdemo.ui.components.DigitItem
import com.inc.lite.stationdemo.ui.components.DigitKeyboard
import com.inc.lite.stationdemo.ui.theme.LightGrayColor
import com.inc.lite.stationdemo.ui.theme.MainColor
import com.inc.lite.stationdemo.ui.theme.RedInfoColor
import com.inc.lite.stationdemo.ui.theme.pingFangTCFamily
import com.inc.lite.stationdemo.viewModels.AuthViewModel
import com.inc.lite.stationdemo.util.AdjScreenSize


@Preview(widthDp = 800, heightDp = 1280, showBackground = false)
@Composable
fun EnterSMS(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = hiltViewModel(),
    navHostController: NavHostController = rememberNavController()
) {
    val uiState by viewModel.uiState.collectAsState()
    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)
    var digit by remember {
        mutableStateOf("    ")
    }

    Surface(
        modifier
            .fillMaxSize()
            .padding(
                top = size.dp(60)
            ),
    ){
        Column(
            Modifier
                .fillMaxSize()
                .padding(top = size.dp(120)),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Enter the verification code sent to +${uiState.getFullNumber()} ",
                fontSize = size.sp(28),
                color = LightGrayColor,
                textAlign = TextAlign.Center
            )
            SmsEntering(
                Modifier
                    .padding(
                        bottom = size.dp(40),
                        top = size.dp(50)
                    ),
                smsCode = digit.toCharArray()
            )
            AnimatedVisibility(
                modifier = Modifier
                    .padding(top = size.dp(20)),
                visible = viewModel.isCodeError.value
            ) {
                InfoRow()
            }
            Button(
                modifier = Modifier
                    .padding(top = size.dp(40))
                    .height(size.dp(80)),
                onClick = {
                    viewModel.confirmSMSCode(digit)
                },
                colors = ButtonDefaults.buttonColors(containerColor = MainColor)
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = size.dp(55)),
                    text = "Continue",
                    fontSize = size.sp(24)
                )
            }
            DigitKeyboard(
                onDigitClicked = {key ->
                    digit = viewModel.addValueByKey(digit,key)
                }
            )

        }
    }
}

@Preview
@Composable
fun SmsEntering(
    modifier: Modifier = Modifier,
    smsCode: CharArray = CharArray(4),
    onSmsEntered: (String) -> Unit = {_->}
) {

    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)
    Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
        Row() {
            for(n in 0..3){
                DigitItem(
                    modifier = Modifier
                        .padding(
                            start = size.dp(24),
                            end = size.dp(24)
                        ),
                    digit = smsCode[n].toString()
                )
            }
        }
    }
}

@Preview
@Composable
fun InfoRow(
    modifier: Modifier = Modifier,
    message: String = "Sms code does not match with\nour system, try sending it again"
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
                    bottom = size.dp(10)
                ),
            painter = painterResource(id = R.drawable.info_outlined),
            contentDescription = ""
        )
        Text(
            text = message,
            modifier = Modifier
                .width(size.dp(350))
                .height(size.dp(66)),
            fontSize = size.sp(24),
            color = RedInfoColor,
            style = TextStyle(
                fontFamily = pingFangTCFamily,
                fontWeight = FontWeight.Normal
            )
        )
    }
}