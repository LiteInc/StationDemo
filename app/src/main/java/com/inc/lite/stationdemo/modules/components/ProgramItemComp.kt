package com.inc.lite.stationdemo.modules.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.inc.lite.stationdemo.modules.models.ProgramItem


@Composable
fun ProgramItemComponent(
    modifier: Modifier = Modifier,
    programItem: ProgramItem = ProgramItem("Google Maps"),
    navHostController: NavHostController,
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp + 72
    Surface(
        modifier = modifier
            .height((screenHeight/8.42).dp)
            .width((screenHeight/8.42).dp)
            .clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ) {
            navHostController.navigate("webview_screen")
        },
        color =  Color.Transparent
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .size((screenHeight/12.8).dp)
                    .padding(bottom = (screenHeight/64).dp),
                painter = painterResource(id = programItem.imageUrl),
                contentDescription = ""
            )
            Text(
                text = programItem.title,
                fontSize = (screenHeight/53.3).sp,
                fontWeight = FontWeight.Light
            )
        }
    }
}