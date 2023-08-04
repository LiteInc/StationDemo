package com.inc.lite.stationdemo.ui.screens.rentingSystem.rent

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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.inc.lite.stationdemo.R
import com.inc.lite.stationdemo.activities.MainActivity
import com.inc.lite.stationdemo.model.StatusBarUiState
import com.inc.lite.stationdemo.ui.components.BottomBar
import com.inc.lite.stationdemo.ui.components.StatusBar
import com.inc.lite.stationdemo.ui.theme.LightGrayBackground
import com.inc.lite.stationdemo.ui.theme.LightGrayColor
import com.inc.lite.stationdemo.ui.theme.MainColor
import com.inc.lite.stationdemo.ui.theme.mainTextStyle
import com.inc.lite.stationdemo.util.AdjScreenSize
import com.inc.lite.stationdemo.viewModels.RentViewModel

@Composable
fun ProfileScreen(
    viewModel: RentViewModel = hiltViewModel(),
    navHostController: NavHostController
) {

    val context = LocalContext.current
    val intent = Intent(context, MainActivity::class.java)
    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)

    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomBar()
        }
    ){
        Image(
            painter = painterResource(id = R.drawable.profile_background),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentScale = ContentScale.FillBounds
        )
        Box(
            modifier = Modifier
                .height(size.dp(1078))
                .width(size.dp(800))
                .padding(it),
            contentAlignment = Alignment.Center
        ){
            Column(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.fillMaxSize()
            ) {
                StatusBar(
                    uiState = StatusBarUiState(),
                    backgroundColor = Color.Black,
                    backgroundAlpha = 0f
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = size.dp(28), end = size.dp(66)),
                    contentAlignment = Alignment.CenterEnd
                ){
                    Text(
                        text = stringResource(id = R.string.logout),
                        fontSize = size.sp(28),
                        style = mainTextStyle,
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            context.startActivity(intent)
                        },
                        color = MainColor
                    )
                }
                Box(
                    Modifier
                        .padding(
                            top = size.dp(106),
                            start = size.dp(64),
                            end = size.dp(64)
                        )
                ) {
                    Row {
                        Card(
                            modifier = Modifier
                                .weight(1f)
                                .height(size.dp(401)),
                            colors = CardDefaults.cardColors(
                                containerColor = LightGrayBackground
                            ),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 5.dp)
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                if(uiState.user.avatarUrl == null){
                                    Box(modifier = Modifier
                                        .size(size.dp(180))
                                        .clip(CircleShape)
                                        .background(LightGrayColor),
                                        contentAlignment = Alignment.Center
                                    ){
                                        Text(
                                            text = if(uiState.user.nickname == null) "N" else uiState.user.nickname!!.toCharArray().first().toString(),
                                            fontSize = size.sp(60),
                                            color = LightGrayBackground
                                        )
                                    }

                                }else{
                                    Image(
                                        painter = rememberAsyncImagePainter(
                                            model = uiState.user.avatarUrl
                                        ),
                                        contentDescription = "avatar",
                                        modifier  = Modifier
                                            .size(size.dp(180))
                                            .clip(CircleShape),
                                        contentScale = ContentScale.Crop
                                    )
                                }

                                Text(text = uiState.user.nickname?: "Name")
                            }
                        }

                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = size.dp(32)),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Card(
                                modifier = Modifier
                                    .height(size.dp(193))
                                    .fillMaxWidth(),
                                colors = CardDefaults.cardColors(
                                    containerColor = LightGrayBackground),
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 5.dp)
                                ) {
                                Row(
                                    Modifier.fillMaxSize(),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.lite_text_logo),
                                        contentDescription = "",
                                        modifier = Modifier.height(size.dp(24))
                                    )
                                    Spacer(modifier = Modifier.width(size.dp(6)))
                                    Text(
                                        text = stringResource(id = R.string.lite_wallet),
                                        style = mainTextStyle,
                                        fontSize = size.sp(28)
                                    )

                                }
                            }
                            Button(
                                modifier = Modifier
                                    .padding(top = size.dp(67))
                                    .width(size.dp(283))
                                    .height(size.dp(90)),
                                onClick = {
                                    //Todo rent
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MainColor
                                ),
                                elevation = ButtonDefaults.buttonElevation(
                                    defaultElevation = 5.dp
                                )
                            ) {
                                Text(
                                    text = stringResource(id = R.string.next_step),
                                    style = mainTextStyle,
                                    fontSize = size.sp(32)
                                )
                            }
                        }
                    }
                }
            }

        }
    }
}

