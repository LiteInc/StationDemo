package com.inc.lite.stationdemo.modules.ui.screens.rentingSystem.loginReg

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.inc.lite.stationdemo.R
import com.inc.lite.stationdemo.modules.ui.components.DigitKeyboard
import com.inc.lite.stationdemo.modules.ui.components.DropDownList
import com.inc.lite.stationdemo.modules.ui.components.EnteringDigits
import com.inc.lite.stationdemo.modules.ui.models.LoginUiState
import com.inc.lite.stationdemo.modules.ui.navigation.Screen
import com.inc.lite.stationdemo.modules.ui.theme.LightGrayColor
import com.inc.lite.stationdemo.modules.ui.theme.MainColor
import com.inc.lite.stationdemo.modules.ui.viewModel.LoginViewModel
import com.inc.lite.stationdemo.util.AdjScreenSize
import dagger.hilt.android.lifecycle.HiltViewModel


@Preview(widthDp = 800, heightDp = 1280, showBackground = false)
@Composable
fun EnterPhoneNumber(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
    navHostController: NavHostController = rememberNavController()
) {
    val uiState by viewModel._uiState.collectAsState()
    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)

    var countriesMenu by remember {
        mutableStateOf(false)
    }

    Surface(
        modifier
            .fillMaxSize()
            .padding(
                top = size.dp(60)
            )
    ) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ){

            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    modifier = Modifier,
                    text = "Enter your phone number",
                    color = LightGrayColor,
                    fontSize = size.sp(28),
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
                        text = "Taiwan",
                        fontSize = size.sp(28)
                    )
                    Image(
                        modifier = Modifier
                            .padding(start = size.dp(16))
                            .size(size.dp(24)),
                        painter = painterResource(id = R.drawable.down_arrow_small),
                        contentDescription = ""
                    )

                }

                EnteringDigits(
                    Modifier.padding(top = size.dp(60)),
                    number = arrayOf(
                        "1","2","3","4","5","6","7","8","9"
                    )
                )
                Button(
                    modifier = Modifier
                        .padding(top = size.dp(80))
                        .height(size.dp(80))
                        .width(size.dp(182)),
                    onClick = {
                        navHostController.navigate(Screen.LoginEnterSMS.route)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MainColor)
                ) {
                    Text(
                        modifier = Modifier,
                        text = "Log in",
                        fontSize = size.sp(24)
                    )
                }

            }
            DigitKeyboard()
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
            }
        )
    }

}



