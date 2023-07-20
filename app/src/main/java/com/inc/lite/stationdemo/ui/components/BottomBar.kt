package com.inc.lite.stationdemo.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.inc.lite.stationdemo.R
import com.inc.lite.stationdemo.ui.theme.MainColor
import com.inc.lite.stationdemo.ui.theme.pingFangTCFamily
import com.inc.lite.stationdemo.util.QRCodeUtil

@Preview(widthDp = 800, heightDp = 1280)
@Composable
fun BottomBar(
    modifier: Modifier = Modifier
) {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp + 72
    val bitmap = QRCodeUtil.createQRImage(
        "http://www.riisu.co/rent?now=86546307",
        140,
        140,
        null,
        MainColor.toArgb()
    ).asImageBitmap()

    Surface(
        tonalElevation =  BottomAppBarDefaults.ContainerElevation,
        modifier = modifier,
        color = Color.White
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .height((screenHeight/6.33).dp)
                .padding(
                    vertical = (screenHeight/40).dp,
                    horizontal = (screenHeight/21.33).dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Row(Modifier.height((screenHeight/13.6).dp)) {

                Image(
                    modifier = Modifier.size((screenHeight/13.6).dp),
                    painter = painterResource(id = R.drawable.lite_app_logo),
                    contentDescription = null
                )
                Column(
                    Modifier
                        .padding(start = (screenHeight/64).dp)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier.size((screenHeight/42.66).dp),
                        painter = painterResource(id = R.drawable.gplay_logo),
                        contentDescription = ""
                    )
                    Image(
                        modifier = Modifier.size((screenHeight/42.66).dp),
                        painter = painterResource(id = R.drawable.apple_apps_store),
                        contentDescription = ""
                    )
                }
            }
            Box() {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "While someone else is using\nthe screen, you can scan the\nQR code to start the rental",
                        modifier = Modifier
                            .width((screenHeight/5.66).dp)
                            .padding(end = (screenHeight/256).dp),
                        fontSize = (screenHeight/80).sp,
                        style = TextStyle(
                            fontFamily = pingFangTCFamily,
                            fontWeight = FontWeight.Normal
                        )
                    )
                    Image(
                        bitmap,
                        contentDescription = "",
                        modifier = Modifier.size((screenHeight/9.14).dp)
                    )
                }
            }

        }

    }
}