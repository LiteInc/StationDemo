package com.inc.lite.stationdemo.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inc.lite.stationdemo.R
import com.inc.lite.stationdemo.model.StationType
import com.inc.lite.stationdemo.ui.theme.MainColor
import com.inc.lite.stationdemo.ui.theme.mainTextStyle
import com.inc.lite.stationdemo.util.AdjScreenSize
import com.inc.lite.stationdemo.viewModels.RentViewModel

@Composable
fun StationPopUpDialogWindow(
    modifier: Modifier = Modifier,
    viewModel: RentViewModel
) {
    AnimatedVisibility(visible = viewModel.showStationSlotsInfo.value) {
        StationDialogComponent(modifier, viewModel)
    }
}

@Composable
fun StationDialogComponent(
    modifier: Modifier = Modifier,
    viewModel: RentViewModel
) {
    val config= LocalConfiguration.current
    val size = AdjScreenSize(config)

    Box(
        modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.21f)),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = size.dp(28))
                .height(size.dp(957))
                .fillMaxWidth()
                .clip(RoundedCornerShape(size.dp(27))),
            contentAlignment = Alignment.Center
        ){
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(size.dp(27))),
                painter = painterResource(id = R.drawable.background_slots),
                contentDescription = ""
            )
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                StationFrame(stationType = StationType.twelveSlots)
            }
            Spacer(modifier = Modifier.height(size.dp(40)))
            Button(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = size.dp(30))
                    .height(size.dp(79))
                    .width(size.dp(237)),
                onClick = {
                    viewModel.onFinishRent()
                },
                colors = ButtonDefaults.buttonColors(containerColor = MainColor),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.logout_after_n_sec, viewModel.rentalTimer.value.toString()),
                    fontSize = size.sp(24),
                    style = mainTextStyle
                )
            }
        }
    }
}
@Preview
@Composable
fun StationFrame(
    stationType: StationType = StationType.twelveSlots
) {
    val config= LocalConfiguration.current
    val size = AdjScreenSize(config)

    Box(
        Modifier
            .fillMaxWidth()
            .height(size.dp(675)),
        contentAlignment = Alignment.Center
    ) {
        when (stationType){
            StationType.eightSlots -> {
                Image(
                    modifier = Modifier.height(size.dp(675)),
                    painter = painterResource(id = R.drawable.station_8_slots_frame),
                    contentDescription = ""
                )
            }
            StationType.twelveSlots -> {
                Image(
                    modifier = Modifier.height(size.dp(675)),
                    painter = painterResource(id = R.drawable.station_12_slots_frame),
                    contentDescription = ""
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Column {
                    StationSlot(isIndicated = true, stationType = stationType)
                    Spacer(Modifier.height(size.dp(15)))
                    StationSlot(isIndicated = false, stationType = stationType)
                }
                Spacer(Modifier.width(size.dp(25)))
                Column {
                    StationSlot(isIndicated = false, stationType = stationType)
                    Spacer(Modifier.height(size.dp(15)))
                    StationSlot(isIndicated = false, stationType = stationType)
                }
            }
            Spacer(modifier = Modifier.height(size.dp(50)))
            Row {
                Column {
                    StationSlot(isIndicated = false, stationType = stationType)
                    Spacer(Modifier.height(size.dp(15)))
                    StationSlot(isIndicated = false, stationType = stationType)
                }
                Spacer(Modifier.width(size.dp(25)))
                Column {
                    StationSlot(isIndicated = false, stationType = stationType)
                    Spacer(Modifier.height(size.dp(15)))
                    StationSlot(isIndicated = false, stationType = stationType)
                }
            }
            Spacer(modifier = Modifier.height(size.dp(50)))
            Row {
                Column {
                    StationSlot(isIndicated = false, stationType = stationType)
                    Spacer(Modifier.height(size.dp(15)))
                    StationSlot(isIndicated = false, stationType = stationType)
                }
                Spacer(Modifier.width(size.dp(25)))
                Column {
                    StationSlot(isIndicated = false, stationType = stationType)
                    Spacer(Modifier.height(size.dp(15)))
                    StationSlot(isIndicated = false, stationType = stationType)
                }
            }
            Spacer(modifier = Modifier.height(size.dp(38)))
        }
    }

}


@Composable
fun SlotItem(
    isSelected: Boolean,
    stationType: StationType
) {

    val config= LocalConfiguration.current
    val size = AdjScreenSize(config)

    if (stationType == StationType.twelveSlots){
        if(isSelected){
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .height(size.dp(17))
                    .width(size.dp(86))
            ) {
                Image(
                    modifier = Modifier,
                    painter = painterResource(id = R.drawable.small_slot),
                    contentDescription = ""
                )
                BlinkingIndicator(stationType)
            }


        }else{
            Image(
                modifier = Modifier,
                painter = painterResource(id = R.drawable.small_slot),
                contentDescription = ""
            )
        }


    }else{
        Image(
            modifier = Modifier

                .height(size.dp(20))
                .width(size.dp(100)),
            painter = painterResource(id = R.drawable.big_slot),
            contentDescription = ""
        )
    }

}

@Composable
fun BlinkingIndicator(
//    height: Int,
//    width: Int,
    stationType: StationType
) {
    val config= LocalConfiguration.current
    val size = AdjScreenSize(config)
    val infiniteTransition = rememberInfiniteTransition(label = "")

    val alpha by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )
    if(stationType == StationType.eightSlots){
        Image(
            modifier = Modifier
                .graphicsLayer(alpha = alpha),
            painter = painterResource(id = R.drawable.big_indication),
            contentDescription = "",
            contentScale = ContentScale.FillBounds
        )

    }else{
        Image(
            modifier = Modifier
                .graphicsLayer(alpha = alpha),
            painter = painterResource(id = R.drawable.small_indication),
            contentDescription = "",
            contentScale = ContentScale.FillBounds
        )
    }
}

//@Preview(showBackground = true, heightDp = 1280, widthDp = 800)
//@Composable
//fun SlotPreview() {
//    SlotItem(isSelected = false, stationType = StationType.twelveSlots)
//}

//@Preview
@Composable
fun StationSlot(
    isIndicated: Boolean = true,
    stationType: StationType = StationType.eightSlots
) {
    val config= LocalConfiguration.current
    val size = AdjScreenSize(config)

    val infiniteTransition = rememberInfiniteTransition(label = "")

    val alpha by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    if(stationType == StationType.twelveSlots){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(size.dp(22))
                .width(size.dp(92))
        ){

            Image(
                modifier = Modifier
                    .height(size.dp(17))
                    .width(size.dp(86)),
                painter = painterResource(id = R.drawable.small_slot),
                contentDescription = ""
            )
            if(isIndicated){
                Image(
                    modifier = Modifier
                        .graphicsLayer(alpha = alpha),
                    painter = painterResource(id = R.drawable.small_indication),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds
                )

            }

        }

    }else{
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(size.dp(23))
                .width(size.dp(105))
        ){

            Image(
                modifier = Modifier
                    .height(size.dp(20))
                    .width(size.dp(100)),
                painter = painterResource(id = R.drawable.big_slot),
                contentDescription = ""
            )
            if(isIndicated){
                Image(
                    modifier = Modifier
                        .graphicsLayer(alpha = alpha),
                    painter = painterResource(id = R.drawable.big_indication),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds
                )

            }

        }
    }


}


