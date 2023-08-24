package com.inc.lite.stationdemo.ui.screens.rentingSystem.rent.coupons

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
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inc.lite.stationdemo.R
import com.inc.lite.stationdemo.ui.components.CouponItem
import com.inc.lite.stationdemo.ui.components.CouponItemData
import com.inc.lite.stationdemo.ui.theme.MainColor
import com.inc.lite.stationdemo.ui.theme.mainTextStyle
import com.inc.lite.stationdemo.util.AdjScreenSize
import com.inc.lite.stationdemo.viewModels.RentViewModel


@Composable
fun ChooseWhichCouponsScreen(
    viewModel: RentViewModel,
    paddingValues: PaddingValues,
    couponItemsList: List<CouponItemData> = listOf(
        CouponItemData(),
        CouponItemData(),
    )
) {

    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(paddingValues)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth().weight(1f),
            verticalArrangement = Arrangement.Center
        ){
            items(couponItemsList){
                CouponItem(
                    it,
                    isSelected = false,
                    onClick = {}
                )
            }
        }
        Spacer(modifier = Modifier.height(size.dp(10)))
        Button(
            modifier = Modifier
                .height(size.dp(90))
                .width(size.dp(198)),
            onClick = {

            },
            colors = ButtonDefaults.buttonColors(containerColor = MainColor),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
        ) {
            Text(
                text = stringResource(id = R.string.apply),
                fontSize = size.sp(24),
                style = mainTextStyle
            )
        }
        Spacer(modifier = Modifier.height(size.dp(20)))

    }
}