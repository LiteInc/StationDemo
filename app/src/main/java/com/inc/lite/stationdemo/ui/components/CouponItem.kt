package com.inc.lite.stationdemo.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inc.lite.stationdemo.R
import com.inc.lite.stationdemo.ui.theme.White
import com.inc.lite.stationdemo.util.AdjScreenSize


data class CouponItemData(
    val expiredData: String = "2019-12-31",
    val typeText: String = "一小時免費"

)

@OptIn(ExperimentalMaterial3Api::class)
@Preview(widthDp = 800, heightDp = 1280)
@Composable
fun CouponItem(
    couponData: CouponItemData = CouponItemData(),
    isSelected: Boolean = true,
    onClick: () -> Unit = {}
) {

    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)

    Box(modifier = Modifier
        .fillMaxWidth()) {
        Card(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = size.dp(36), vertical = size.dp(14)),
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