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
import com.inc.lite.stationdemo.ui.theme.MainColor
import com.inc.lite.stationdemo.ui.theme.mainTextStyle
import com.inc.lite.stationdemo.util.AdjScreenSize
import com.inc.lite.stationdemo.viewModels.RentViewModel


@Composable
fun ChooseWhichCouponsScreen(
    viewModel: RentViewModel,
    paddingValues: PaddingValues,
    couponItemsList: List<CouponItemData> = emptyList()
//        listOf(
//            CouponItemData(),
//            CouponItemData(),
//        )
) {

    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)

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


            if(couponItemsList.isEmpty()){
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
                item {
                    CouponItem(
                        couponItemsList[0],
                        isSelected1,
                        onClick = { couponItemData ->
                            if(!isSelected1){
                                isSelected1 = true
                                isSelected2 = false
                                isSelected3 = false
                            }else{
                                isSelected1 = false
                                isSelected2 = false
                                isSelected3 = false
                            }
                        }
                    )
                }
                item {
                    CouponItem(
                        couponItemsList[1],
                        isSelected2,
                        onClick = { couponItemData ->
                            if(!isSelected2){
                                isSelected1 = false
                                isSelected2 = true
                                isSelected3 = false
                            }else{
                                isSelected1 = false
                                isSelected2 = false
                                isSelected3 = false
                            }
                        }
                    )
                }
                item {
                    CouponManualItem(
                        isSelected3,
                        onClick = {s, b ->
                            if(!isSelected3){
                                isSelected1 = false
                                isSelected2 = false
                                isSelected3 = true
                            }else{
                                isSelected1 = false
                                isSelected2 = false
                                isSelected3 = false
                            }
                        }
                    )
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
//                    viewModel.useCoupons()
                },
                enabled = false,
                colors = ButtonDefaults.buttonColors(containerColor = MainColor),
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
//        Button(
//            modifier = Modifier
//                .height(size.dp(90))
//                .width(size.dp(198)),
//            onClick = {
//                viewModel.startRental()
//            },
//            colors = ButtonDefaults.buttonColors(containerColor = MainColor),
//            elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
//        ) {
//            Text(
//                text = stringResource(id = R.string.apply),
//                fontSize = size.sp(24),
//                style = mainTextStyle
//            )
//        }
        Spacer(modifier = Modifier.height(size.dp(92)))

    }
}