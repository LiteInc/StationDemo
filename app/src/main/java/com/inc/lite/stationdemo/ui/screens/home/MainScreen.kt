package com.inc.lite.stationdemo.ui.screens.home

import android.content.Intent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.inc.lite.stationdemo.model.MainUiState
import com.inc.lite.stationdemo.ui.theme.MainColor
import com.inc.lite.stationdemo.ui.theme.StationLiteTheme
import com.inc.lite.stationdemo.ui.theme.pingFangTCFamily
import com.inc.lite.stationdemo.viewModels.MainViewModel
import com.inc.lite.stationdemo.R
import com.inc.lite.stationdemo.activities.AuthActivity
import com.inc.lite.stationdemo.ui.components.QrElement
import com.inc.lite.stationdemo.ui.components.StatusBar
import com.inc.lite.stationdemo.ui.navigation.Screen
import com.inc.lite.stationdemo.ui.theme.SponsoredBack
import com.inc.lite.stationdemo.ui.theme.SponsoredFront
import com.inc.lite.stationdemo.util.AdjScreenSize


@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    navHostController: NavHostController
) {

    val uiState by viewModel.uiState.collectAsState()

    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)


    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            AdsFragment()
        }

    }
    StatusBar(
        uiState = uiState.statusUiState,
        modifier = Modifier,
        backgroundColor = Color.Black,
        contentColor = Color.White,
        backgroundAlpha = 0.3f
    )


    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Column {
            Box(modifier = Modifier
                .height(30.dp)
                .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    modifier = Modifier.background(Color.Black.copy(alpha = 0.6f)),
                    text = "Screen: ${size.screenWidth} x ${size.screenHeight}",
                    color = Color.White,
                    fontSize = size.sp(20)
                )
            }
            BottomBarMain(
                modifier = Modifier.height(size.dp(156)),
                uiState = uiState,
                navHostController = navHostController
            )
        }

    }
}

@Composable
fun BottomBarMain(
    modifier: Modifier = Modifier,
    uiState: MainUiState,
    navHostController: NavHostController
) {

    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 10.0f, topEnd = 10.0f))
            .background(Color.White)
    ) {
        Row(
            Modifier
                .fillMaxSize()
        ) {

            //First Box With text and QR
            FirstBottomBox(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.32f)
            )
            StrippedVerticalLine(Modifier.padding(vertical = size.dp(18)))
            CentralBottomBox(
                modifier = Modifier.weight(0.472f),
                uiState = uiState
            )
//                StrippedVerticalLine()
            RightBottomBox(
                modifier = Modifier.weight(0.208f),
                uiState,
                onAppsClick = {
                    navHostController.navigate(Screen.Programs.route)
                }
            )

        }
    }
}

@Preview(widthDp = 800, heightDp = 1280, showBackground = true)
@Composable
fun MainPreview(){
    StationLiteTheme() {
        MainScreen(
            hiltViewModel(),
            navHostController = rememberNavController()
        )
    }
}

@Preview(widthDp = 257)
@Composable
fun FirstBottomBox(
    modifier: Modifier = Modifier,
//    uiState: MainUiState = MainUiState(),
    isShowAdd: Boolean = false
) {

    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)
    Box(
        modifier.fillMaxHeight()
    ) {

        if(isShowAdd){
            Box(
                modifier = Modifier
                    .background(SponsoredBack)
                    .height(size.dp(22))
                    .width(size.dp(74)),
                contentAlignment = Alignment.Center
            ){
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Sponsored",
                    color = SponsoredFront,
                    fontSize = size.sp(10),
                    textAlign = TextAlign.Center
                )
            }
            Row(
                Modifier
                    .padding(start = size.dp(18)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Download\nsome\napplication",
                    fontSize = size.sp(14),
                    modifier = Modifier.width(size.dp(75)),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontFamily = pingFangTCFamily,
                        fontWeight = FontWeight.Normal
                    )
                )
                Spacer(Modifier.width(size.dp(10)))
                QrElement(url = "")
            }

        } else{
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(
                        start = size.dp(0)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                 verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Download our application",
                    fontSize = size.sp(14),
                    style = TextStyle(
                        fontFamily = pingFangTCFamily
                    )

                )
                Spacer(modifier = Modifier.height(size.dp(15)))
                Row(verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.Center){
                    QrElement(
                        modifier = Modifier
                            .height(size.dp(56))
                            .width(size.dp(56))
                            .padding(end = size.dp(10)),
                        url = "https://play.google.com/store/apps/details?id=com.inc.riisu",
                        height = size.dp(70),
                        width = size.dp(70)
                        
                    )
                    Image(
                        painterResource(id = R.drawable.gplay_logo),
                        contentDescription = null,
                        modifier = Modifier
                            .height(size.dp(30))
                            .width(size.dp(30))

                    )
                    Spacer(Modifier.width(size.dp(30)))
                    QrElement(
                        modifier = Modifier
                            .height(size.dp(56))
                            .width(size.dp(56))
                            .padding(end = size.dp(10)),
                        url = "https://play.google.com/store/apps/details?id=com.inc.riisu",
                        height = size.dp(70),
                        width = size.dp(70)
                    )

                    Image(
                        painterResource(id = R.drawable.apple_apps_store),
                        contentDescription = null,
                        modifier = Modifier
                            .height(size.dp(30))
                            .width(size.dp(30))
                    )


                }

            }
        }



    }
}

@Composable
fun CentralBottomBox(
    modifier: Modifier = Modifier,
    uiState: MainUiState,
) {

    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)

    val context = LocalContext.current
    val intent = Intent(context, AuthActivity::class.java)

    Box(
        modifier.fillMaxHeight()
    ) {
        Row(
            Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = size.dp(20),
                        vertical = size.dp(38)
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Button(
                    onClick = {
                        context.startActivity(intent)
                    },
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(size.dp(180))
                        .height(size.dp(79))
                        .padding(end = size.dp(16)),
                    colors = ButtonDefaults.buttonColors(containerColor = MainColor)
                ) {
                    Text(
                        text = uiState.startButtonString,
                        fontSize = size.sp(24)
                    )
                }
                Text(
                    text = "Or scan\nQR Code！",
                    modifier = Modifier,
                    fontSize = size.sp(14),
                    textAlign = TextAlign.Start,
                    style = TextStyle(
                        fontFamily = pingFangTCFamily,
                        fontWeight = FontWeight.Normal
                    )
                )
                QrElement(
                    Modifier.height(size.dp(80)).width(size.dp(80)),
                    url = uiState.stationQR,
                    foregroundColor = MainColor,
                    width = size.dp(140),
                    height = size.dp(140)
                )

            }
        }
    }
}



@Composable
fun RightBottomBox(
    modifier: Modifier = Modifier,
    uiState: MainUiState,
    onAppsClick: ()-> Unit = {}
) {
    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)
    Box(
        modifier.fillMaxHeight()
    ) {
        Column(
            Modifier
                .padding(vertical = size.dp(31), horizontal = size.dp(16))
                .fillMaxHeight()
        ) {
            Text(
                modifier = Modifier.padding(start = size.dp(6)),
                text = "使用其他應用",
                fontSize = size.sp(14),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontFamily = pingFangTCFamily,
                    fontWeight = FontWeight.Normal
                )
            )
            Row(
                Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(size.dp(109))
                        .padding(top = size.dp(4)),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalArrangement = Arrangement.SpaceAround
                ){
                    items(6){
                        ProgramItem(uiState.onAppPreviewClick)
                    }
                }
                Image(
                    painter = painterResource(id = R.drawable.play_arrow),
                    contentDescription = "Open programs",
                    modifier = Modifier
                        .height(size.dp(16))
                        .width(size.dp(14))
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            onAppsClick()
                        }
                )
            }

        }

    }
}

@Preview
@Composable
fun ProgramItem(
    onAppPreviewClick: ()-> Unit = {}
) {
    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)
    Box(
        Modifier
            .height(size.dp(36))
            .width(size.dp(32))
            .background(Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.icon_brand),
            contentDescription = "Icons with brands",
            modifier = Modifier
                .size(size.dp(22))
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    onAppPreviewClick()
                }
        )
    }
}

@Composable
fun StrippedVerticalLine(
    modifier: Modifier = Modifier
) {

    Canvas(modifier = modifier.fillMaxHeight()){
        val canvasWidth = size.width
        val canvasHeight = size.height

        val startX = canvasWidth / 2f
        val startY = 0f
        val stopX = canvasWidth / 2f
        val stopY = canvasHeight

        drawLine(
            color = MainColor,
            start = Offset(startX, startY),
            end = Offset(stopX, stopY),
            strokeWidth = 1f,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 2f)
        )
    }
}
