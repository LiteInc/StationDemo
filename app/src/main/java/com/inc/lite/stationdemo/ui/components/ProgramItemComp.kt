package com.inc.lite.stationdemo.ui.components

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
import androidx.navigation.NavHostController
import com.inc.lite.stationdemo.model.ProgramItem
import com.inc.lite.stationdemo.util.AdjScreenSize
import com.inc.lite.stationdemo.viewModels.HomeViewModel


@Composable
fun ProgramItemComponent(
    modifier: Modifier = Modifier,
    programItem: ProgramItem = ProgramItem(title = "Google Maps"),
    navHostController: NavHostController,
    viewModel: HomeViewModel
) {
    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)
    Surface(
        modifier = modifier
            .height(size.dp(152))
            .width(size.dp(152))
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                viewModel.navigateToWeb(programItem)
            },
        color =  Color.Transparent
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .size(size.dp(100))
                    .padding(bottom = size.dp(20)),
                painter = painterResource(id = programItem.logo),
                contentDescription = ""
            )
//            Image(
//                painter = rememberAsyncImagePainter(
//                    model = programItem.logo
//                ),
//                contentDescription = "logo",
//                modifier  = Modifier
//                    .height(size.dp(100))
//                    .width(size.dp(100))
//                    .padding(bottom = size.dp(20)),
//            )
            Text(
                text = programItem.title,
                fontSize = size.sp(32),
                fontWeight = FontWeight.Light
            )
        }
    }
}