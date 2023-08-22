package com.inc.lite.stationdemo.ui.screens.rentingSystem.rent

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.inc.lite.stationdemo.R
import com.inc.lite.stationdemo.model.PaymentCard
import com.inc.lite.stationdemo.model.PaymentType
import com.inc.lite.stationdemo.ui.components.BottomBar
import com.inc.lite.stationdemo.ui.components.StatusBar
import com.inc.lite.stationdemo.ui.components.TopBar
import com.inc.lite.stationdemo.ui.theme.LightBackgroundSelected
import com.inc.lite.stationdemo.ui.theme.LightGrayBackground
import com.inc.lite.stationdemo.ui.theme.LightGrayChooseText
import com.inc.lite.stationdemo.ui.theme.MainColor
import com.inc.lite.stationdemo.ui.theme.White
import com.inc.lite.stationdemo.ui.theme.mainTextStyle
import com.inc.lite.stationdemo.ui.theme.pingFangTCFamily
import com.inc.lite.stationdemo.util.AdjScreenSize
import com.inc.lite.stationdemo.viewModels.RentViewModel

@Composable
fun ChoosePaymentScreen(
    viewModel: RentViewModel,
    navHostController: NavHostController
) {
    val uiState by viewModel.rentUiState.collectAsState()

    val context = LocalContext.current

    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)

    Scaffold(
        topBar = {
            Column {
                StatusBar(uiState = viewModel.statusBarUiState.value)
                TopBar(
                    returnHomeText = stringResource(id = R.string.logout),
                    onReturnHomeClick = {
                        viewModel.logOut(context)
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
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.BottomStart
        ){
            Image(
                painter = painterResource(id = R.drawable.choose_payment_back),
                contentDescription = "",
                modifier = Modifier
                    .height(size.dp(497))
                    .width(size.dp(170))
            )
        }
        Column(Modifier.padding(it)) {
            Spacer(modifier = Modifier.height(size.dp(30)))
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(size.dp(400)),
                contentAlignment = Alignment.BottomStart
            ){
                Surface(
                    color = White,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = size.dp(72))) {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                bottom = size.dp(117),
                                start = size.dp(170)
                            ),
                        contentAlignment = Alignment.BottomStart
                    ){
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = White
                            ),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                            modifier = Modifier
                                .padding(top = size.dp(3))
                                .width(size.dp(377))
                                .height(size.dp(288))
                        ) {
                            Column(
                                horizontalAlignment = Alignment.Start,
                                modifier = Modifier.padding(vertical = size.dp(40))
                            ) {
                                Text(
                                    text = stringResource(id = R.string.free_trail_15),
                                    modifier = Modifier.padding(start = size.dp(60)),
                                    style = mainTextStyle,
                                    fontSize = size.sp(28)
                                )
                                Row {
                                    Spacer(Modifier.width(size.dp(116)))
                                    Column {
                                        Spacer(modifier = Modifier.height(size.dp(53)))
                                        Text(
                                            stringResource(id = R.string.per_hour),
                                            style = mainTextStyle,
                                            fontSize = size.sp(28),
                                            color = LightGrayChooseText
                                        )
                                        Spacer(Modifier.height(size.dp(48)))
                                        Text(
                                            stringResource(id = R.string.hours_24),
                                            style = mainTextStyle,
                                            fontSize = size.sp(28),
                                            color = LightGrayChooseText
                                        )

                                    }
                                    Spacer(modifier = Modifier.width(size.dp(33)))
                                    Column {
                                        Spacer(modifier = Modifier.height(size.dp(48)))
                                        Text(
                                            text =  "$" + uiState.tariff.tariffPerHour.toString(),
                                            fontSize = size.sp(36),
                                            color = MainColor,
                                            style = TextStyle(fontFamily = pingFangTCFamily, fontWeight = FontWeight.Bold)
                                        )
                                        Spacer(Modifier.height(size.dp(38)))
                                        Text(
                                            text = "$" + uiState.tariff.tariffPerDay.toString(),
                                            fontSize = size.sp(36),
                                            color = MainColor,
                                            style = TextStyle(fontFamily = pingFangTCFamily, fontWeight = FontWeight.Bold)
                                        )
                                    }
                                }


                            }
                        }

                    }
                    Box(
                        modifier = Modifier
                            .width(size.dp(213))
                            .height(size.dp(360))
                            .padding(top = size.dp(40))
                    ){
                        Image(
                            painterResource(id = R.drawable.rental_guy),
                            alignment = Alignment.BottomStart,
                            contentDescription = null,
                            modifier = Modifier
                                .height(size.dp(360))
                                .width(size.dp(213))
                        )

                    }
                }
            }

            Row(Modifier.padding(horizontal = size.dp(43))) {
                PaymentCard(
                    paymentCard = uiState.listOfPayments[0],
                    viewModel = viewModel
                )

                Spacer(modifier = Modifier.width(size.dp(27)))
                PaymentCard(
                    paymentCard = uiState.listOfPayments[1],
                    viewModel = viewModel
                )

                Spacer(modifier = Modifier.width(size.dp(27)))
                PaymentCard(
                    paymentCard = uiState.listOfPayments[2],
                    viewModel = viewModel
                )

            }
            Spacer(modifier = Modifier.height(size.dp(60)))
            Button(
                modifier = Modifier
                    .height(size.dp(90))
                    .padding(start = size.dp(275))
                    .width(size.dp(250)),
                onClick = { viewModel.onPaymentConfirm() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MainColor,
                    contentColor = White
                )
            ) {
                Text(text = "支付")
            }
        }
    }
}

@Composable
fun PaymentCardItem(
    isSelected: Boolean,
    onClicked: ()-> Unit,
    content: @Composable ()-> Unit,
) {

    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)

    if(isSelected){
        Card(
            modifier = Modifier
                .size(size.dp(220))
                .border(1.dp, MainColor, RoundedCornerShape(size = size.dp(20)))
                .clickable {
                    onClicked()
                },
            colors = CardDefaults.cardColors(
                containerColor = LightBackgroundSelected
            ),
            shape = RoundedCornerShape(size = size.dp(20)),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        ) {
            content()

        }

    }else{
        Card(
            modifier = Modifier
                .size(size.dp(220))
                .clickable {
                    onClicked()
                },
            colors = CardDefaults.cardColors(
                containerColor = LightGrayBackground
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        ) {
            content()
        }

    }

}

@Composable
fun PaymentCard(
    paymentCard: PaymentCard,
    viewModel: RentViewModel
) {
    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)
    when(paymentCard.paymentType){
        PaymentType.LinePay -> {
            PaymentCardItem(isSelected = viewModel.selectedPayment.value == PaymentType.LinePay, onClicked = { viewModel.onLinePaySelect() }) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Image(
                        modifier = Modifier
                            .height(size.dp(40))
                            .width(size.dp(135)),
                        painter = painterResource(id = R.drawable.line_pay_logo),
                        contentDescription = ""
                    )
                }
            }
        }
        PaymentType.LiteWallet -> {
            PaymentCardItem(isSelected = viewModel.selectedPayment.value == PaymentType.LiteWallet, onClicked = { viewModel.onLiteWalletSelect() }) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Image(
                        modifier = Modifier
                            .height(size.dp(39))
                            .width(size.dp(130)),
                        painter = painterResource(id = R.drawable.lite_pay),
                        contentDescription = ""
                    )
                }
            }
        }
        PaymentType.Coupons -> {
            PaymentCardItem(isSelected = viewModel.selectedPayment.value == PaymentType.Coupons, onClicked = { viewModel.onCouponsSelect() }) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Image(
                        modifier = Modifier.size(size.dp(54)),
                        painter = painterResource(id = R.drawable.dollar_logo),
                        contentDescription = ""
                    )
                }
            }
        }

    }

}