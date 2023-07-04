package com.inc.lite.stationdemo.modules.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.inc.lite.stationdemo.R
import com.inc.lite.stationdemo.modules.ui.theme.MainColor
import com.inc.lite.stationdemo.util.QRCodeUtil

@Preview(widthDp = 800, heightDp = 1280)
@Composable
fun BottomBar(
    modifier: Modifier = Modifier
) {

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
                .height(202.dp)
                .padding(
                    vertical = 32.dp,
                    horizontal = 60.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Row(Modifier.height(94.dp)) {
                Image(
                    modifier = Modifier.size(94.dp),
                    painter = painterResource(id = R.drawable.lite_app_logo),
                    contentDescription = null
                )
                Column(
                    Modifier
                        .padding(start = 20.dp)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    Image(painter = painterResource(id = R.drawable.gplay_logo), contentDescription = "")
                    Image(painter = painterResource(id = R.drawable.apple_apps_store), contentDescription = "")
                }
            }
            Box() {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "While someone else is using the screen, you can scan the QR code to start the rental",
                        modifier = Modifier
                            .width(226.dp)
                            .padding(end = 5.dp),
                        fontSize = 16.sp
                    )
                    Image(
                        bitmap,
                        contentDescription = "",
                        modifier = Modifier.size(140.dp)
                    )
                }
            }

        }

    }
}