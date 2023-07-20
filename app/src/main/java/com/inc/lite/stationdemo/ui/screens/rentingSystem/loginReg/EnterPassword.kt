package com.inc.lite.stationdemo.ui.screens.rentingSystem.loginReg

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.inc.lite.stationdemo.ui.components.DigitKeyboard
import com.inc.lite.stationdemo.ui.components.PasswordItem
import com.inc.lite.stationdemo.ui.navigation.Screen
import com.inc.lite.stationdemo.ui.theme.LightGrayColor
import com.inc.lite.stationdemo.ui.theme.MainColor
import com.inc.lite.stationdemo.viewModels.AuthViewModel
import com.inc.lite.stationdemo.util.AdjScreenSize

//@Preview(widthDp = 800, heightDp = 1280, showBackground = false)
@Composable
fun EnterPassword(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
    viewModel: AuthViewModel,
    onNextClick: ()-> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)

    var digit by remember {
        mutableStateOf("      ")
    }

    Surface(
        modifier
            .fillMaxSize()
            .padding(
                top = size.dp(60)
            ),
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(top = size.dp(120)),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Create your six digit passcode",
                fontSize = size.sp(28),
                color = LightGrayColor,
                textAlign = TextAlign.Center
            )
            PassEntering(
                password = digit.toCharArray()
            )
            Button(
                modifier = Modifier
                    .padding(top = size.dp(40))
                    .height(size.dp(80)),
                onClick = onNextClick,
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
                    viewModel.onKeyBoardClick(key, Screen.LoginEnterPass)
                    digit = viewModel.addValueByKey(digit,key)
                }
            )

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