package com.inc.lite.stationdemo.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import com.inc.lite.stationdemo.util.AdjScreenSize

//@Preview(showBackground = true, widthDp = 800)
@Composable
fun EnteringDigits(
    modifier: Modifier = Modifier,
    countyCode: String? = "380",
    number: CharArray = CharArray(9)
) {
    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)
    val countyCodeNotEmpty = countyCode ?: "   "

    Row(
        modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier
                .padding(
                    end = size.dp(14),
                    bottom = size.dp(4)
                ),
            text = "+",
            fontSize = size.sp(28),
        )

        countyCodeNotEmpty.toCharArray().forEach {
            DigitItem(digit = it.toString(), width = 28)
        }

        Spacer(modifier = Modifier.width(size.dp(20)))

        for(n in 0..8){

            when (n){
                0,3,6 -> {
                    DigitItem(
                        modifier = Modifier
                            .padding(
                                start = size.dp(20),
                                end = size.dp(5)
                            ),
                        digit = number[n].toString(),
                        width = 24
                    )
                }
                1,4,7 -> {
                    DigitItem(
                        modifier = Modifier
                            .padding(
                                start = size.dp(5),
                                end = size.dp(5)
                            ),
                        digit = number[n].toString(),
                        width = 24
                    )
                }
                2,5,8 -> {
                    DigitItem(
                        modifier = Modifier
                            .padding(
                                end = size.dp(20),
                                start = size.dp(5)
                            ),
                        digit = number[n].toString(),
                        width = 24
                    )
                }
            }
        }
    }
}

