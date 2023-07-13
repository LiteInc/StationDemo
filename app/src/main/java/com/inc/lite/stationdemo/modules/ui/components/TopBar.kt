package com.inc.lite.stationdemo.modules.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.inc.lite.stationdemo.R
import com.inc.lite.stationdemo.modules.ui.theme.MainColor
import com.inc.lite.stationdemo.modules.ui.theme.pingFangTCFamily

@Preview(widthDp = 400, heightDp = 640)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    onBackArrowClick: ()-> Unit = {},
    onReturnHomeClick: ()-> Unit = {},
    returnHomeText: String = "Return to\nhome screen",
    title: String = "Google",
    image: Int? = null
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp + 72
    Surface(
        modifier = modifier,
        color = Color.White
    ) {
        Box() {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height((screenHeight/16.6).dp)
                    .padding(horizontal = (screenHeight/35.5).dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Image(
                    painter = painterResource(id = R.drawable.back_arrow),
                    contentDescription = "",
                    modifier = Modifier
                        .height((screenHeight/42).dp)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            onBackArrowClick()
                        }
                )
                Text(
                    text = returnHomeText,
                    textAlign = TextAlign.Center,
                    color = MainColor,
                    fontSize = (screenHeight/71).sp,
                    modifier = Modifier
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            onReturnHomeClick()
                        },
                    style = TextStyle(
                        fontFamily = pingFangTCFamily,
                        fontWeight = FontWeight.Normal
                    )
                )
            }
            Box {
                Row(
                    modifier = Modifier.fillMaxWidth().height((screenHeight/16.6).dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    if(image != null){
                        Image(
                            modifier = Modifier
                                .height((screenHeight/37.64).dp)
                                .padding(end = ((screenHeight/80)).dp),
                            painter = painterResource(id = image),
                            contentDescription = "title image"
                        )
                    }
                    Text(
                        text = title,
                        fontSize = (screenHeight/40).sp,
                        textAlign = TextAlign.Center
                    )
                }
            }

        }
    }
}