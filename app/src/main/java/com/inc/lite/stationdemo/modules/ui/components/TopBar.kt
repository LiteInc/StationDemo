package com.inc.lite.stationdemo.modules.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.inc.lite.stationdemo.R
import com.inc.lite.stationdemo.modules.ui.theme.MainColor

@Preview(widthDp = 800, heightDp = 1280)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    onBackArrowClick: ()-> Unit = {}
) {
    Surface(
        modifier = modifier,
        color = Color.White
    ) {
        Box() {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(77.dp)
                    .padding(horizontal = 36.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Image(
                    painter = painterResource(id = R.drawable.back_arrow),
                    contentDescription = "",
                    modifier = Modifier
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            onBackArrowClick()
                        }
                )
                Text(
                    text = "Return to\nhome screen",
                    textAlign = TextAlign.Center,
                    color = MainColor,
                    fontSize = 18.sp
                )
            }
            Box {
                Row(
                    modifier = Modifier.fillMaxWidth().height(77.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        modifier = Modifier.padding(end = 16.dp),
                        painter = painterResource(id = R.drawable.apple_apps_store),
                        contentDescription = ""
                    )
                    Text(
                        text = "inLine",
                        fontSize = 32.sp
                    )
                }
            }

        }
    }
}