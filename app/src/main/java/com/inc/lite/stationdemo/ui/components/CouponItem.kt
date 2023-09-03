package com.inc.lite.stationdemo.ui.components

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inc.lite.stationdemo.R
import com.inc.lite.stationdemo.ui.theme.Background
import com.inc.lite.stationdemo.ui.theme.Black
import com.inc.lite.stationdemo.ui.theme.GrayDotColor
import com.inc.lite.stationdemo.ui.theme.LightGrayColor
import com.inc.lite.stationdemo.ui.theme.MainColor
import com.inc.lite.stationdemo.ui.theme.White
import com.inc.lite.stationdemo.ui.theme.pingFangTCFamily
import com.inc.lite.stationdemo.util.AdjScreenSize


data class CouponItemData(
    val expiredData: String = "2023-12-31",
    val typeText: String = "一小時免費"

)

@OptIn(ExperimentalMaterial3Api::class)
@Preview(widthDp = 800, heightDp = 1280)
@Composable
fun CouponItem(
    couponData: CouponItemData = CouponItemData(),
    isSelected :Boolean = false,
    onClick: (CouponItemData) -> Unit = {  }
) {

    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)
    val borderColor by animateColorAsState(
        targetValue = if (isSelected) MainColor else White,
        animationSpec = tween(durationMillis = 300), label = ""
    )

    Box(modifier = Modifier
        .fillMaxWidth()) {
        Card(
            onClick = {
                onClick(couponData)
                      },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = size.dp(36), vertical = size.dp(14))
                .border(2.dp, borderColor, RoundedCornerShape(size.dp(20))),
            shape = RoundedCornerShape(size.dp(20)),
            colors = CardDefaults.cardColors(
                containerColor = White
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Row(
                Modifier
                    .padding(size.dp(32))
                    .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column(
                    modifier = Modifier,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = couponData.typeText,
                        fontSize = size.sp(36)
                    )
                    Spacer(modifier = Modifier.height(size.dp(65)))
                    Text(
                        text = stringResource(id = R.string.expiration_date) + couponData.expiredData,
                        fontSize = size.sp(32)
                    )
                }
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.loading_circle_lite),
                        modifier = Modifier
                            .height(size.dp(66))
                            .width(size.dp(60)),
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.height(size.dp(59)))
                    Image(
                        painter = painterResource(id = R.drawable.next_arrow),
                        modifier = Modifier
                            .height(size.dp(24))
                            .width(size.dp(10)),
                        contentDescription = ""
                    )
                }
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(widthDp = 800, heightDp = 1280)
@Composable
fun CouponManualItem(
    isSelected: Boolean = false,
    onClick: (String, Boolean) -> Unit = { _,_-> }
) {

    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)
    val borderColor by animateColorAsState(
        targetValue = if (isSelected) MainColor else White,
        animationSpec = tween(durationMillis = 300), label = ""
    )
    var couponManual by remember { mutableStateOf("") }

    Box(modifier = Modifier
        .fillMaxWidth()
    ) {
        Card(
            onClick = {
                onClick("", isSelected)
            },
            modifier = Modifier
                .height(size.dp(250))
                .fillMaxWidth()
                .padding(horizontal = size.dp(36), vertical = size.dp(14))
                .border(2.dp, borderColor, RoundedCornerShape(size.dp(20))),
            shape = RoundedCornerShape(size.dp(20)),
            colors = CardDefaults.cardColors(
                containerColor = White
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(size.dp(250)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(size.dp(10)))
                Text(
                    text = stringResource(id = R.string.enter_manually_coupon),
                    fontSize = size.sp(32)
                )
                TextField(
                    modifier = Modifier
                        .width(size.dp(300)),
                    value = couponManual,
                    enabled = false,
                    onValueChange = {
                        couponManual = it
//                    Log.d("EnterText", "text : $text")
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Background,
                        unfocusedContainerColor = Background,
                        focusedIndicatorColor = MainColor,
                        unfocusedIndicatorColor = LightGrayColor,
                        focusedTextColor = Color.Black,
                        cursorColor = MainColor,
                        disabledContainerColor = Background

                        ),
                    textStyle = TextStyle(
                        fontSize = size.sp(36),
                        textAlign = TextAlign.Center,
                        fontFamily = pingFangTCFamily
                    ),
                    maxLines = 1
                )
                    Spacer(modifier = Modifier.height(size.dp(10)))
            }
        }
    }

}