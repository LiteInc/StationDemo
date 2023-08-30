package com.inc.lite.stationdemo.ui.screens.rentingSystem.loginReg

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.inc.lite.stationdemo.R
import com.inc.lite.stationdemo.ui.components.DigitKeyboard
import com.inc.lite.stationdemo.ui.components.DropDownList
import com.inc.lite.stationdemo.ui.components.LoadingIndicator
import com.inc.lite.stationdemo.ui.screens.home.Keyboard
import com.inc.lite.stationdemo.ui.screens.home.keyboardAsState
import com.inc.lite.stationdemo.ui.theme.Black
import com.inc.lite.stationdemo.ui.theme.LightGrayColor
import com.inc.lite.stationdemo.ui.theme.MainColor
import com.inc.lite.stationdemo.ui.theme.mainTextStyle
import com.inc.lite.stationdemo.viewModels.AuthViewModel
import com.inc.lite.stationdemo.util.AdjScreenSize


//@Preview(widthDp = 800, heightDp = 1280, showBackground = false)
@Composable
fun EnterPhoneNumber(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel,
    navHostController: NavHostController,
    mainNavHost: NavHostController
) {
    val uiState by viewModel.uiState.collectAsState()

    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)

    var countriesMenu by remember {
        mutableStateOf(false)
    }

    var isButtonEnabled by remember { mutableStateOf(false) }

    viewModel.passMainNavHost(mainNavHost)

    Surface(
        modifier
            .fillMaxSize()
            .padding(top = size.dp(60))

    ) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ){

            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    modifier = Modifier,
                    text = stringResource(id = R.string.enter_your_phone_number),
                    color = Black,
                    fontSize = size.sp(36),
                    textAlign = TextAlign.Center
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(top = size.dp(60))
                        .clickable {
                            countriesMenu = true
                        },
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = uiState.county.name,
                        fontSize = size.sp(36),
                        style = mainTextStyle,
                        color = LightGrayColor
                    )
                    Image(
                        modifier = Modifier
                            .padding(start = size.dp(8))
                            .size(size.dp(28)),
                        painter = painterResource(id = R.drawable.down_arrow_small),
                        contentDescription = ""
                    )

                }

                Text(
                    modifier = Modifier.padding(top = size.dp(60)),
                    text = viewModel.phoneNumber.value,
                    fontSize = size.sp(36)
                )

                Box(Modifier.padding(top = size.dp(80)), contentAlignment = Alignment.Center){
                    if (!viewModel.isLoading.value){
                        isButtonEnabled = viewModel.phoneNumber.value.length >= 11
                        Button(
                            modifier = Modifier
                                .height(size.dp(90))
                                .width(size.dp(182)),
                            enabled = isButtonEnabled,
                            onClick = {
                                viewModel.confirmPhoneNumber(navHostController)
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MainColor,
                                disabledContainerColor = MainColor.copy(alpha = 0.6f)
                            )
                        ) {
                            Text(
                                modifier = Modifier,
                                text = stringResource(id = R.string.confirm),
                                fontSize = size.sp(32),
                                style = mainTextStyle
                            )
                        }
                    }
                    LoadingIndicator(isLoading = viewModel.isLoading.value)
                }

            }
            DigitKeyboard(
                onDigitClicked = {key ->
                    viewModel.inputNumber(key)
                }
            )
        }

    }

    AnimatedVisibility(
        visible = countriesMenu,
        enter =
        fadeIn(
            animationSpec =
            tween(
                durationMillis = 100,
                easing = LinearEasing
            )
        ),
        exit =
        fadeOut(
            animationSpec =
            tween(
                durationMillis = 100,
                easing = LinearEasing
            )
        )
    ) {
        DropDownList(
            content = uiState.countriesList,
            onDismiss = {
                countriesMenu = false
            },
            onItemSelected = {
                countriesMenu = false
                viewModel.onDropDownItemClick(it)
            }
        )
    }


}



