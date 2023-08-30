package com.inc.lite.stationdemo.ui.screens.home

import android.content.Intent
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
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.inc.lite.stationdemo.model.uiState.MainUiState
import com.inc.lite.stationdemo.ui.theme.MainColor
import com.inc.lite.stationdemo.ui.theme.pingFangTCFamily
import com.inc.lite.stationdemo.viewModels.HomeViewModel
import com.inc.lite.stationdemo.R
import com.inc.lite.stationdemo.activities.AuthActivity
import com.inc.lite.stationdemo.model.ProgramItem
import com.inc.lite.stationdemo.model.SponsoredSurfaceData
import com.inc.lite.stationdemo.ui.components.QrElement
import com.inc.lite.stationdemo.ui.components.StatusBar
import com.inc.lite.stationdemo.ui.navigation.Screen
import com.inc.lite.stationdemo.ui.theme.Black
import com.inc.lite.stationdemo.ui.theme.SponsoredBack
import com.inc.lite.stationdemo.ui.theme.SponsoredFront
import com.inc.lite.stationdemo.ui.theme.White
import com.inc.lite.stationdemo.ui.theme.mainTextStyle
import com.inc.lite.stationdemo.util.AdjScreenSize


@Composable
fun MainScreen(
    viewModel: HomeViewModel,
    navHostController: NavHostController
) {

    val uiState by viewModel.uiState.collectAsState()

    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)

    LaunchedEffect(key1 = true){
        viewModel.initiateNavHost(navHostController)
    }


    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            AdsFragment(viewModel = viewModel)
            Column(
                modifier = Modifier.padding(
                    start = size.dp(20),
                    top = size.dp(70)
                )
            ) {
                Button(
                    onClick = {
                              viewModel.layout1()
                    },
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors( containerColor = Black.copy(alpha = 0.3f))
                ) {
                    Text(
                        text = "1",
                        fontSize = size.sp(32),
                        color = White
                    )
                }
                Button(
                    onClick = {
                              viewModel.layout2()
                    },
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors( containerColor = Black.copy(alpha = 0.3f))
                ) {
                    Text(
                        text = "2",
                        fontSize = size.sp(32),
                        color = White
                    )
                }
                Button(
                    onClick = {
                              viewModel.layout3()
                    },
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors( containerColor = Black.copy(alpha = 0.3f))
                ) {
                    Text(
                        text = "3",
                        fontSize = size.sp(32),
                        color = White
                    )
                }
                Button(
                    onClick = {
                              viewModel.layout4()
                    },
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors( containerColor = Black.copy(alpha = 0.3f))
                ) {
                    Text(
                        text = "4",
                        fontSize = size.sp(32),
                        color = White
                    )
                }
            }
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
//                Text(
//                    modifier = Modifier.background(Color.Black.copy(alpha = 0.6f)),
//                    text = "Screen: ${size.screenWidth} x ${size.screenHeight}",
//                    color = Color.White,
//                    fontSize = size.sp(20)
//                )
            }
            BottomBarMain(
                modifier = Modifier.height(size.dp(156)),
                uiState = uiState,
                navHostController = navHostController,
                viewModel = viewModel
            )
        }

    }
}



@Composable
fun BottomBarMain(
    modifier: Modifier = Modifier,
    uiState: MainUiState,
    navHostController: NavHostController,
    viewModel: HomeViewModel
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
            LeftBottomBox(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.32f)
            )
            LineFromImage()
            CentralBottomBox(
                modifier = Modifier.weight(0.472f),
                uiState = uiState,
                viewModel
            )
            LineFromImage()
            RightBottomBox(
                modifier = Modifier.weight(0.208f),
                uiState,
                onAppsClick = {
                    viewModel.navigateToPrograms()
                },
                onCertainAppClick = {
                    viewModel.navigateToPrograms()
                }
//                onSertainAppClick = {item ->
//                    navHostController.navigate(Screen.Programs.route)
////                    navHostController.navigate(Screen.WebView.route)
////                    viewModel.setProgramForWebView(item)
//                }
            )

        }
    }
}




@Composable
fun LeftBottomBox(
    modifier: Modifier = Modifier,
//    uiState: MainUiState = MainUiState(),
    isShowAdd: Boolean = true
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
                    text = "廣告",
                    color = SponsoredFront,
                    fontSize = size.sp(10),
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontFamily = pingFangTCFamily)
                )
            }
            Row(
                Modifier
                    .padding(start = size.dp(18)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
//                Text(
//                    text = "Download\nsome\napplication",
//                    fontSize = size.sp(14),
//                    modifier = Modifier.width(size.dp(75)),
//                    textAlign = TextAlign.Center,
//                    style = TextStyle(
//                        fontFamily = pingFangTCFamily,
//                        fontWeight = FontWeight.Normal
//                    )
//                )
//                Spacer(Modifier.width(size.dp(10)))
//                QrElement(url = "nryyt")
//                Image(
//                    modifier = Modifier.padding(
//                        vertical = size.dp(30),
//                        horizontal = size.dp(27)
//                    ),
//                    painter = painterResource(id = R.drawable.uber_ads),
//                    contentDescription = ""
//                )
                SponsoredSurface(sponsoredData = SponsoredSurfaceData())
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
                    text = "下載我們的應用程序",
                    fontSize = size.sp(14),
                    style = mainTextStyle

                )
                Spacer(modifier = Modifier.height(size.dp(15)))
                Row(verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.Center){
                    QrElement(
                        modifier = Modifier
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
    viewModel: HomeViewModel
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
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Button(
                    onClick = {
                        viewModel.stopAdsTimer()
                        context.startActivity(intent)
                    },
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(size.dp(180))
                        .height(size.dp(79))
                        .padding(
                            vertical = size.dp(38)
                        )
                        .padding(end = size.dp(16))
                    ,
                    colors = ButtonDefaults.buttonColors(containerColor = MainColor)
                ) {
                    Text(
                        text = uiState.startButtonString,
                        fontSize = size.sp(24)
                    )
                }
                Text(
                    text = "右邊掃碼，也\n能使用手機租\n借行動電源哦\n哦！",
                    modifier = Modifier
                        .padding(
                            vertical = size.dp(38)
                        ),
                    fontSize = size.sp(14),
                    textAlign = TextAlign.Start,
                    style = mainTextStyle
                )
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(top = size.dp(40)),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    QrElement(
                        Modifier
                            .height(size.dp(62))
                            .width(size.dp(62)),
                        url = uiState.stationQR,
                        foregroundColor = MainColor,
                        width = size.dp(62),
                        height = size.dp(62)
                    )
                    Image(
                        modifier = Modifier
                            .padding(size.dp(8))
                            .height(size.dp(18))
                            .width(size.dp(27)),
                        painter = painterResource(id = R.drawable.lite_small_logo),
                        contentDescription = ""
                    )
                }

            }
        }
    }
}


@Composable
fun RightBottomBox(
    modifier: Modifier = Modifier,
    uiState: MainUiState,
    onAppsClick: ()-> Unit = {},
    onCertainAppClick: ()-> Unit = {}
//    onSertainAppClick: (ProgramItem)-> Unit = {_->}
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
                style = mainTextStyle
            )
            val interactions = remember { MutableInteractionSource() }

                Row(
                    Modifier
                        .fillMaxSize()
                        .clickable { onCertainAppClick() },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(size.dp(109))
                            .padding(top = size.dp(4)),
//                        .clickable(
//                            indication = null,
//                            interactionSource = interactions
//                        ) {
//                            onAppsClick()
//                        },
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalArrangement = Arrangement.SpaceAround
                    ){
                        items(uiState.programsList){
                            ProgramItem(
                                itemState = it,
//                            onAppPreviewClick = {item ->
//                                onSertainAppClick(item)
//                            }
                            )
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
                                interactionSource = interactions
                            ) {
//                                onAppsClick()
                            }
                    )
                }

        }
    }
}

@Composable
fun SponsoredSurface(
    modifier: Modifier = Modifier,
    sponsoredData: SponsoredSurfaceData
) {
    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)
    Box(
        modifier = modifier,
    ){
        Row(
            Modifier.fillMaxHeight(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Box(
                    modifier = Modifier,
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = sponsoredData.title,
                        modifier = Modifier.width(size.dp(75)),
                        fontSize = size.sp(14),
                        maxLines = 2,
                        textAlign = TextAlign.Center,
                        style = mainTextStyle
                    )

                }
                Spacer(modifier = Modifier.height(size.dp(4)))
                Image(
                    painter = painterResource(id = sponsoredData.logo),
                    contentDescription = "sponsored logo",
                    modifier = Modifier
                        .size(size.dp(42))
                )
            }
            Spacer(modifier = Modifier.width(size.dp(10)))
            QrElement(
                modifier = Modifier,
                url = sponsoredData.url,
                height = size.dp(79),
                width = size.dp(79)
            )
            Spacer(modifier = Modifier.width(size.dp(10)))
            Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxHeight()) {
                Image(
                    painter = painterResource(id = R.drawable.gplay_logo),
                    contentDescription = "sponsored gplay",
                    modifier = Modifier
                        .size(size.dp(18))

                )
                Spacer(modifier = Modifier.height(size.dp(12)))
                Image(
                    painter = painterResource(id = R.drawable.apple_apps_store),
                    contentDescription = "sponsored gplay",
                    modifier = Modifier
                        .size(size.dp(18))
                )
            }
        }
    }
}
@Preview
@Composable
fun TestSponsored() {
    SponsoredSurface(sponsoredData = SponsoredSurfaceData())
}

@Preview
@Composable
fun ProgramItem(
    itemState: ProgramItem = ProgramItem(),
//    onAppPreviewClick: (ProgramItem)-> Unit = {_->}
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
            painter = painterResource(id = itemState.logo),
            contentDescription = "add",
            modifier  = Modifier
                .size(size.dp(22))
//                .clickable(
//                    indication = null,
//                    interactionSource = remember { MutableInteractionSource() }
//                ) {
//                    onAppPreviewClick(
//                        itemState
//                    )
//                },
        )

    }
}


@Composable
fun LineFromImage(){
    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)
    Box(
        Modifier
            .fillMaxHeight()
            .padding(vertical = size.dp(18)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.line_4),
            contentDescription = ""
        )
    }
}

//@Composable
//fun StrippedVerticalLine(
//    modifier: Modifier = Modifier
//) {
//
//    Canvas(modifier = modifier.fillMaxHeight()){
//        val canvasWidth = size.width
//        val canvasHeight = size.height
//
//        val startX = canvasWidth / 2f
//        val startY = 0f
//        val stopX = canvasWidth / 2f
//        val stopY = canvasHeight
//
//        drawLine(
//            color = MainColor,
//            start = Offset(startX, startY),
//            end = Offset(stopX, stopY),
//            strokeWidth = 1f,
//            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 2f)
//        )
//    }
//}
