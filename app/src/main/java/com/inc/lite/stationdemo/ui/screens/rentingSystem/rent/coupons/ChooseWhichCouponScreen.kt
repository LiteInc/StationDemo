package com.inc.lite.stationdemo.ui.screens.rentingSystem.rent.coupons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.inc.lite.stationdemo.R
import com.inc.lite.stationdemo.ui.components.CouponItem
import com.inc.lite.stationdemo.ui.components.CouponItemData
import com.inc.lite.stationdemo.ui.components.CouponManualItem
import com.inc.lite.stationdemo.ui.theme.LightGrayBackgroundDisabled
import com.inc.lite.stationdemo.ui.theme.MainColor
import com.inc.lite.stationdemo.ui.theme.White
import com.inc.lite.stationdemo.ui.theme.mainTextStyle
import com.inc.lite.stationdemo.util.AdjScreenSize
import com.inc.lite.stationdemo.viewModels.RentViewModel


@Composable
fun ChooseWhichCouponsScreen(
    viewModel: RentViewModel,
    paddingValues: PaddingValues,
) {

    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)
    LaunchedEffect(key1 = true){
        viewModel.setTitle(R.string.voucher)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(paddingValues)
    ) {

        var isSelected1 by remember { mutableStateOf(false) }
        var isSelected2 by remember { mutableStateOf(false) }
        var isSelected3 by remember { mutableStateOf(false) }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.Center
        ){


            if(viewModel.couponsList.value.isEmpty()){
                item {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        Spacer(modifier = Modifier.height(size.dp(108)))
                        Text(
                            modifier = Modifier.padding(start = size.dp(35)),
                            text = stringResource(id = R.string.you_do_not_have_any_coupons_yet),
                            fontSize = size.sp(40),
                            style = mainTextStyle
                        )
                        Spacer(modifier = Modifier.height(size.dp(108)))
                        Image(
                            modifier = Modifier
                                .height(size.dp(286))
                                .width(size.dp(382)),
                            painter = painterResource(id = R.drawable.empty_coupons_list),
                            contentDescription = ""
                        )

                    }

                }
            }else{

                if(viewModel.couponsList.value.size == 1){
                    item {
                        CouponItem(
                            viewModel.couponsList.value[0],
                            isSelected1,
                            onClick = {
                                isSelected1 = !isSelected1
                                viewModel.couponAvailable.value = isSelected1
                            }
                        )
                    }
                }else if(viewModel.couponsList.value.size == 2){
                    item {
                        CouponItem(
                            viewModel.couponsList.value[0],
                            isSelected1,
                            onClick = {
                                if(!isSelected1){
                                    isSelected1 = true
                                    isSelected2 = false
                                    viewModel.couponAvailable.value = true
                                }else{
                                    isSelected1 = false
                                    isSelected2 = false
                                    viewModel.couponAvailable.value = false
                                }
                            }
                        )
                    }
                    item {
                        CouponItem(
                            viewModel.couponsList.value[1],
                            isSelected2,
                            onClick = {
                                if(!isSelected2){
                                    isSelected1 = false
                                    isSelected2 = true
                                    viewModel.couponAvailable.value = true
                                }else{
                                    isSelected1 = false
                                    isSelected2 = false
                                    viewModel.couponAvailable.value = false
                                }
                            }
                        )
                    }
                }else {

                    item {
                        CouponItem(
                            viewModel.couponsList.value[0],
                            isSelected1,
                            onClick = {
                                if(!isSelected1){
                                    isSelected1 = true
                                    isSelected2 = false
                                    isSelected3 = false
                                    viewModel.couponAvailable.value = true
                                }else{
                                    isSelected1 = false
                                    isSelected2 = false
                                    isSelected3 = false
                                    viewModel.couponAvailable.value = false
                                }
                            }
                        )
                    }
                    item {
                        CouponItem(
                            viewModel.couponsList.value[1],
                            isSelected2,
                            onClick = {
                                if(!isSelected2){
                                    isSelected1 = false
                                    isSelected2 = true
                                    isSelected3 = false
                                    viewModel.couponAvailable.value = true
                                }else{
                                    isSelected1 = false
                                    isSelected2 = false
                                    isSelected3 = false
                                    viewModel.couponAvailable.value = false
                                }
                            }
                        )
                    }
                    item {
                        CouponItem(
                            viewModel.couponsList.value[2],
                            isSelected3,
                            onClick = {
                                if(!isSelected2){
                                    isSelected1 = false
                                    isSelected2 = false
                                    isSelected3 = true
                                    viewModel.couponAvailable.value = true
                                }else{
                                    isSelected1 = false
                                    isSelected2 = false
                                    isSelected3 = false
                                    viewModel.couponAvailable.value = false
                                }
                            }
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(size.dp(35)))


        Column(
            Modifier
                .width(size.dp(300))
                .height(size.dp(213)),
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                onClick = {
                    viewModel.startRental()
                },
                enabled = viewModel.couponAvailable.value,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MainColor,
                    disabledContainerColor = LightGrayBackgroundDisabled,
                    disabledContentColor = White
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.use),
                    fontSize = size.sp(32),
                    style = mainTextStyle
                )
            }
            Spacer(modifier = Modifier.height(size.dp(32)))
            OutlinedButton(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                onClick = {
                    viewModel.addManualCoupon()
                },
                colors = ButtonDefaults.buttonColors(
                    contentColor = MainColor,
                    containerColor = Color.Transparent
                ),
                border = BorderStroke(1.dp, MainColor)
            ) {
                Text(
                    text = stringResource(id = R.string.enter_promo_code),
                    fontSize = size.sp(32),
                    fontWeight = FontWeight.Light,
                    style = mainTextStyle
                )
            }

        }
        Spacer(modifier = Modifier.height(size.dp(92)))

    }
}