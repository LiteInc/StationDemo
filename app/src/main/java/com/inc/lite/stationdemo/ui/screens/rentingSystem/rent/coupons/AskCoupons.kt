package com.inc.lite.stationdemo.ui.screens.rentingSystem.rent.coupons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.inc.lite.stationdemo.R
import com.inc.lite.stationdemo.ui.theme.MainColor
import com.inc.lite.stationdemo.ui.theme.mainTextStyle
import com.inc.lite.stationdemo.util.AdjScreenSize
import com.inc.lite.stationdemo.viewModels.RentViewModel

@Composable
fun AskCoupons(
    viewModel: RentViewModel,
    paddingValues: PaddingValues
) {
    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)
    Surface(Modifier.fillMaxSize().padding(paddingValues)) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(size.dp(204)))
            Text(
                text = stringResource(id = R.string.do_you_want_to_use_coupon),
                fontSize = size.sp(36),
                style = mainTextStyle,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(size.dp(80)))
            Row(
                Modifier
                    .width(size.dp(497))
                    .height(size.dp(90)),
            ) {
                
                OutlinedButton(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f),
                    onClick = {
                        viewModel.skipCoupons()
                    },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MainColor,
                        containerColor = Color.Transparent
                    ),
                    border = BorderStroke(1.dp, MainColor)
                ) {
                    Text(
                        text = stringResource(id = R.string.no_skip),
                        fontSize = size.sp(24),
                        fontWeight = FontWeight.Light,
                        style = mainTextStyle
                    )
                }
                Spacer(modifier = Modifier.width(size.dp(32)))
                Button(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f),
                    onClick = {
                        viewModel.useCoupons()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MainColor),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.yes_use),
                        fontSize = size.sp(24),
                        style = mainTextStyle
                    )
                }
            }
        }
    }
}