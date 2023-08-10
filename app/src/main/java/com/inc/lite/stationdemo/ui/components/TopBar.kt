package com.inc.lite.stationdemo.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.CutCornerShape

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.inc.lite.stationdemo.ui.theme.MainColor
import com.inc.lite.stationdemo.ui.theme.pingFangTCFamily
import com.inc.lite.stationdemo.util.AdjScreenSize

@Preview(widthDp = 400, heightDp = 640)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    onBackArrowClick: ()-> Unit = {},
    onReturnHomeClick: ()-> Unit = {},
    returnHomeText: String = "回到主選單",
    title: String = "",
    image: Int? = null
) {
    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)
    Surface(
        modifier = modifier,
        color = Color.White
    ) {
        Box() {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(size.dp(77))
                    .padding(horizontal = size.dp(36)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
//                Image(
//                    painter = painterResource(id = R.drawable.back_arrow),
//                    contentDescription = "",
//                    modifier = Modifier
//                        .height((screenHeight/42).dp)
//                        .clickable(
//                            indication = null,
//                            interactionSource = remember { MutableInteractionSource() }
//                        ) {
//                            onBackArrowClick()
//                        }
//                )
                ClickableSVGImage {
                    onBackArrowClick()
                }
                Text(
                    text = returnHomeText,
                    textAlign = TextAlign.Center,
                    color = MainColor,
                    fontSize = size.sp(18),
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(size.dp(77)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    if(image != null){
                        Image(
                            modifier = Modifier
                                .height(size.dp(34))
                                .padding(end = size.dp(16)),
                            painter = painterResource(id = image),
                            contentDescription = "title image"
                        )
                    }
                    Text(
                        text = title,
                        fontSize = size.sp(32),
                        textAlign = TextAlign.Center,
                        style = TextStyle(fontFamily = pingFangTCFamily)
                    )
                }
            }

        }
    }
}

@Preview
@Composable
fun ClickableSVGImage(
    onClick: ()-> Unit = {}
) {
    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)
    // Define the click state and animation values
//    var isClicked by remember { mutableStateOf(false) }
//    val alphaValue by animateFloatAsState(if (isClicked) 0.5f else 1f, label = "")
        Image(
            painter = painterResource(R.drawable.back_arrow),
            contentDescription = null,
            modifier = Modifier
                .size(size.dp(34))
                .clip(CutCornerShape(topStart = 70f, bottomStart = 70f))
                .clickable {
                    onClick()
                }
//                .clickable(
//                    indication = null,
//                    interactionSource = remember { MutableInteractionSource() }
//                ) {
//                    isClicked = !isClicked
//                    onClick()
//                }
                    // When clicked, toggle the click state
//                .alpha(alphaValue)
        )
}


