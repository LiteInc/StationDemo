package com.inc.lite.stationdemo.modules.ui.screens

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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.inc.lite.stationdemo.modules.ui.models.MainUiState
import com.inc.lite.stationdemo.modules.ui.theme.MainColor
import com.inc.lite.stationdemo.modules.ui.theme.StationLIteTheme
import com.inc.lite.stationdemo.modules.ui.theme.pingFangTCFamily
import com.inc.lite.stationdemo.modules.ui.viewModel.MainViewModel
import com.inc.lite.stationdemo.R
import com.inc.lite.stationdemo.modules.ui.components.StatusBar
import com.inc.lite.stationdemo.modules.ui.navigation.Screen
import com.inc.lite.stationdemo.util.QRCodeUtil


@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    navHostController: NavHostController
) {

    val uiState by viewModel._uiState.collectAsState()
    val bitmap = QRCodeUtil.createQRImage(uiState.stationQR, 140, 140, null, MainColor.toArgb()).asImageBitmap()

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp + 72


    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            Image(
                painter = painterResource(id = R.drawable.main_add),
                contentDescription = "Main add",
                modifier = Modifier.height((screenHeight/1.12).dp).width(screenWidth.dp),
//                contentScale = ContentScale.Crop
            )
        }

    }
    StatusBar(
        uiState = uiState.statusUiState,
        modifier = Modifier,
        backgroundColor = Color.Black,
        contentColor = Color.White,
        backgroundAlpha = 0.12f
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
                    text = "Screen: $screenWidth x $screenHeight",
                    color = Color.White,
                    fontSize = 20.sp
                )
            }
            BottomBarMain(
                modifier = Modifier.height((screenHeight/8.20).dp),
                bitmap = bitmap,
                uiState = uiState,
                navHostController = navHostController
            )
        }

    }
}

@Composable
fun BottomBarMain(
    modifier: Modifier = Modifier,
    bitmap: ImageBitmap,
    uiState: MainUiState,
    navHostController: NavHostController
) {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp + 72
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 10.0f, topEnd = 10.0f))
            .background(Color.White)
    ) {
        Row(
            Modifier
                .fillMaxSize()
                .padding(vertical = (screenHeight/71).dp)) {

            //First Box With text and QR
            FirstBottomBox(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.27f)
            )
            StrippedVerticalLine()
            CentralBottomBox(
                modifier = Modifier.weight(0.525f),
                uiState = uiState,
                bitmap = bitmap,
            )
//                StrippedVerticalLine()
            RightBottomBox(
                modifier = Modifier.weight(0.205f),
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
    StationLIteTheme() {
        MainScreen(
            viewModel(),
            navHostController = rememberNavController()
        )
    }
}

@Composable
fun FirstBottomBox(
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp + 72
    Box(
        modifier.fillMaxHeight()
    ) {
        Row(
            Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Download\nsome\napplication",
                fontSize = (screenHeight/91.42).sp,
                modifier = Modifier.width((screenHeight/17).dp),
                textAlign = TextAlign.Center,
                                style = TextStyle(
                                    fontFamily = pingFangTCFamily,
                                    fontWeight = FontWeight.Normal
                                )
            )
            Spacer(Modifier.width((screenHeight/128).dp))
            Image(
                modifier = Modifier
                    .height((screenHeight/16.2).dp),
                painter = painterResource(id = R.drawable.upload__your_app),
                contentDescription = "download qrs"
            )
        }

    }
}

@Composable
fun CentralBottomBox(
    modifier: Modifier = Modifier,
    uiState: MainUiState,
    bitmap: ImageBitmap
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp + 72
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
                        horizontal = (screenHeight/64).dp,
                        vertical = (screenHeight/64).dp
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = uiState.onStartClick,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width((screenHeight/7.5).dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MainColor)
                ) {
                    Text(
                        text = uiState.startButtonString,
                        fontSize = (screenHeight/53.3).sp
                    )
                }
                Text(
                    text = "Or scan\nQR Code！",
                    modifier = Modifier,
                    fontSize = (screenHeight/91.42).sp,
                    textAlign = TextAlign.Start,
                    style = TextStyle(
                        fontFamily = pingFangTCFamily,
                        fontWeight = FontWeight.Normal
                    )
                )

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        modifier = Modifier.size((screenHeight/16.2).dp),
                        bitmap = bitmap,
                        contentDescription = "Station URL in QR"
                    )
//                    Image(
//                        modifier = Modifier
//                            .padding(top = 7.dp)
//                            .height(17.dp)
//                            .width(30.dp),
//                        painter = painterResource(id = R.drawable.lite_logo_small),
//                        contentDescription = "Lite logo under QR"
//                    )
                }
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
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp + 72
    Box(
        modifier.fillMaxHeight()
    ) {
        Column(
            Modifier
                .padding(vertical = (screenHeight/91.42).dp, horizontal = (screenHeight/80).dp)
                .fillMaxHeight()
        ) {
            Text(
                modifier = Modifier.padding(start = (screenHeight/213.3).dp),
                text = "使用其他應用",
                fontSize = (screenHeight/91.42).sp,
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
                        .width((screenHeight/11.74).dp)
                        .padding(top = (screenHeight/320).dp),
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
                        .height((screenHeight/80).dp)
                        .width((screenHeight/91.42).dp)
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
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp + 72
    Box(
        Modifier
            .height((screenHeight/35.5).dp)
            .width((screenHeight/40).dp)
            .background(Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.icon_brand),
            contentDescription = "Icons with brands",
            modifier = Modifier
                .size((screenHeight/58).dp)
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
