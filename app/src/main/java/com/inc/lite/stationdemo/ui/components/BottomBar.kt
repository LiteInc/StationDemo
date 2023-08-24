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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.inc.lite.stationdemo.R
import com.inc.lite.stationdemo.ui.theme.MainColor
import com.inc.lite.stationdemo.ui.theme.mainTextStyle
import com.inc.lite.stationdemo.ui.theme.pingFangTCFamily
import com.inc.lite.stationdemo.util.AdjScreenSize
import com.inc.lite.stationdemo.util.QRCodeUtil

@Preview(widthDp = 800, heightDp = 1280)
@Composable
fun BottomBar(
    modifier: Modifier = Modifier
) {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp + 72


    val size = AdjScreenSize(configuration)

    Surface(
        tonalElevation =  BottomAppBarDefaults.ContainerElevation,
        modifier = modifier,
        color = Color.White
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(size.dp(202))
                .padding(
                    vertical = (screenHeight / 40).dp,
                    horizontal = (screenHeight / 21.33).dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Row(Modifier.height((screenHeight/13.6).dp)) {

                Image(
                    modifier = Modifier.size((screenHeight/13.6).dp),
                    painter = painterResource(id = R.drawable.lite_logo),
                    contentDescription = null
                )
                Column(
                    Modifier
                        .padding(start = (screenHeight / 64).dp)
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
                        text = stringResource(id = R.string.bottom_info),
                        modifier = Modifier
                            .width(size.dp(226))
                            .padding(end = size.dp(5)),
                        fontSize = size.sp(20),
                        style = mainTextStyle
                    )
                    QrElement(
                        url = "http://www.riisu.co/rent?now=86099016",
                        modifier = Modifier.size(size.dp(140)),
                        height = 140.dp,
                        width = 140.dp,
                        foregroundColor = MainColor
                    )
                }
            }

        }

    }
}