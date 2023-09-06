package com.inc.lite.stationdemo.ui.screens.rentingSystem.rent.coupons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.inc.lite.stationdemo.R
import com.inc.lite.stationdemo.ui.screens.home.Keyboard
import com.inc.lite.stationdemo.ui.screens.home.keyboardAsState
import com.inc.lite.stationdemo.ui.theme.Background
import com.inc.lite.stationdemo.ui.theme.LightGrayBackgroundDisabled
import com.inc.lite.stationdemo.ui.theme.MainColor
import com.inc.lite.stationdemo.ui.theme.White
import com.inc.lite.stationdemo.ui.theme.mainTextStyle
import com.inc.lite.stationdemo.ui.theme.pingFangTCFamily
import com.inc.lite.stationdemo.util.AdjScreenSize
import com.inc.lite.stationdemo.viewModels.RentViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable

fun EnterManuallyCoupons(
    viewModel: RentViewModel,
    paddingValues: PaddingValues
) {
    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)
    val isKeyboardOpen by keyboardAsState()
    val keyboardController = LocalSoftwareKeyboardController.current

    var coupon by remember { mutableStateOf("") }
    LaunchedEffect(key1 = true){
        viewModel.setTitle(R.string.enter_promo_code)
    }
    Box(
        Modifier
            .fillMaxSize()
            .background(White)
            .padding(paddingValues),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if(isKeyboardOpen == Keyboard.Opened){
                Spacer(Modifier.height(size.dp(100)))
            }
            Card(
                modifier = Modifier
                    .padding(horizontal = size.dp(48))
                    .height(size.dp(152))
                    .fillMaxWidth(),
                shape = RoundedCornerShape(size.dp(20)),
                colors = CardDefaults.cardColors(
                    containerColor = White,
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),

            ){
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    TextField(
                        modifier = Modifier,
                        value = coupon,
                        onValueChange = {
                            val uppercaseText = it.uppercase()
                            coupon = uppercaseText
                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Background,
                            unfocusedContainerColor = Background,
                            focusedIndicatorColor = Background,
                            unfocusedIndicatorColor = Background,
                            focusedTextColor = Color.Black,
                            cursorColor = MainColor,
                            disabledContainerColor = Background,


                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = androidx.compose.ui.text.input.ImeAction.Done // Set the desired IME action here
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                // Define the action to perform when the "Done" button is pressed
                                keyboardController?.hide()
                                // You can also perform other actions here
                            }
                        ),
                        textStyle = TextStyle(
                            fontSize = size.sp(40),
                            textAlign = TextAlign.Center,
                            fontFamily = pingFangTCFamily
                        ),
                        maxLines = 1
                    )
                }
            }
            Spacer(modifier = Modifier.height(size.dp(65)))
            Button(
                enabled = coupon.toCharArray().size >= 5,
                modifier = Modifier
                    .height(size.dp(90))
                    .width(size.dp(385)),
                onClick = {
                    viewModel.confirmManualCoupon(coupon)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MainColor,
                    disabledContainerColor = LightGrayBackgroundDisabled,
                    disabledContentColor = White
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.exchange),
                    fontSize = size.sp(32),
                    style = mainTextStyle
                )
            }
            Spacer(modifier = Modifier.height(size.dp(182)))
            Image(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = size.dp(87))
                    .height(size.dp(279))
                    .width(size.dp(534)),
                painter = painterResource(id = R.drawable.enter_coupons_back),
                contentDescription = ""
            )
        }
    }
}