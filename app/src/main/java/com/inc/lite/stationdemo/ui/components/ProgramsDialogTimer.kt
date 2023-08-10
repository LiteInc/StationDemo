package com.inc.lite.stationdemo.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inc.lite.stationdemo.R
import com.inc.lite.stationdemo.ui.theme.LittleTextLight
import com.inc.lite.stationdemo.ui.theme.MainColor
import com.inc.lite.stationdemo.ui.theme.White
import com.inc.lite.stationdemo.ui.theme.mainTextStyle
import com.inc.lite.stationdemo.ui.theme.pingFangTCFamily
import com.inc.lite.stationdemo.util.AdjScreenSize

@Composable
fun ProgDialogTimer(
    onContinueClick:  ()-> Unit,
    onExitClick:  ()-> Unit,
    timerItem: String,
) {

    val config= LocalConfiguration.current
    val size = AdjScreenSize(config)

    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.21f)),
        contentAlignment = Alignment.Center
    ){
        Box(
            modifier = Modifier
                .height(size.dp(320))
                .fillMaxWidth()
                .background(White),
            contentAlignment = Alignment.Center
        ){
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stringResource(id = R.string.are_you_here),
                    modifier = Modifier,
                    fontSize = size.sp(32)
                )
                Spacer(modifier = Modifier.height(size.dp(24)))
                Text(
                    modifier = Modifier,
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = LittleTextLight,
                                fontSize = size.sp(18),
                                fontWeight = FontWeight.Normal,
                                fontFamily = pingFangTCFamily
                            )
                        ){
                            append(stringResource(id = R.string.you_will_return))
                        }
                        withStyle(
                            style = SpanStyle(
                                color = MainColor,
                                fontSize = size.sp(20),
                                fontWeight = FontWeight.Bold
                            )
                        ){
                            append(timerItem)
                        }
                        withStyle(
                            style = SpanStyle(
                                color = LittleTextLight,
                                fontSize = size.sp(18),
                                fontWeight = FontWeight.Normal,
                                fontFamily = pingFangTCFamily
                            )
                        ){
                            append(stringResource(id = R.string.seconds))
                        }
                    }
                )
                Spacer(modifier = Modifier.height(size.dp(66)))
                Row(
                    Modifier
                        .width(size.dp(425))
                        .height(size.dp(80)),
                ) {
                    Button(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1.38f),
                        onClick = {
                            onContinueClick()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = MainColor),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.text_continue),
                            fontSize = size.sp(24),
                            style = mainTextStyle,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.width(size.dp(57)))
                    OutlinedButton(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f),
                        onClick = {
                            onExitClick()
                        },
                        colors = ButtonDefaults.buttonColors(
                            contentColor = MainColor,
                            containerColor = Color.Transparent
                        ),
                        border = BorderStroke(1.dp, MainColor)
                    ) {
                        Text(
                            text = stringResource(id = R.string.exit),
                            fontSize = size.sp(24),
                            fontWeight = FontWeight.Light,
                            style = mainTextStyle
                        )
                    }
                }
            }
        }
    }

}

@Preview
@Composable
fun PreviewOfTimer() {
    ProgDialogTimer(
        onContinueClick = { /*TODO*/ },
        onExitClick = { /*TODO*/ },
        timerItem = "15"
    )
}