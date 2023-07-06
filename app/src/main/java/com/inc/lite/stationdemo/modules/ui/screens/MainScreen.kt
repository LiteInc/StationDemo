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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
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

    Column(modifier = Modifier.fillMaxSize()) {
        StatusBar(
            uiState = uiState.statusUiState,
            modifier = Modifier.weight(0.056f)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.82f)

        ) {
            Image(
                painter = painterResource(id = R.drawable.add_main),
                contentDescription = "Main add",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.121f)
                .background(Color.White)
        ) {
            Row(
                Modifier
                    .fillMaxSize()
                    .padding(vertical = 18.dp)) {

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
                fontSize = 14.sp,
                modifier = Modifier.width(75.dp),
                textAlign = TextAlign.Center,
                                style = TextStyle(
                                    fontFamily = pingFangTCFamily,
                                    fontWeight = FontWeight.Normal
                                )
            )
            Spacer(Modifier.width(10.dp))
            Image(
                modifier = Modifier
                    .height(79.dp),
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
                        horizontal = 20.dp,
                        vertical = 20.dp
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = uiState.onStartClick,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(167.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MainColor)
                ) {
                    Text(
                        text = uiState.startButtonString,
                        fontSize = 24.sp
                    )
                }
                Text(
                    text = "Or scan\nQR Code！",
                    modifier = Modifier,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Start,
                    style = TextStyle(
                        fontFamily = pingFangTCFamily,
                        fontWeight = FontWeight.Normal
                    )
                )

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        modifier = Modifier.size(79.dp),
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
    Box(
        modifier.fillMaxHeight()
    ) {
        Column(
            Modifier
                .padding(top = 14.dp, bottom = 14.dp, start = 16.dp, end = 16.dp)
                .fillMaxHeight()
        ) {
            Text(
                modifier = Modifier.padding(start = 6.dp),
                text = "使用其他應用",
                fontSize = 14.sp,
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
                        .width(109.dp)
                        .padding(top = 4.dp),
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
                        .height(16.dp)
                        .width(14.dp)
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
    Box(
        Modifier
            .height(36.dp)
            .width(32.dp)
            .background(Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.icon_brand),
            contentDescription = "Icons with brands",
            modifier = Modifier
                .size(22.dp)
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
            strokeWidth = 2f,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 2f)
        )
    }
}
