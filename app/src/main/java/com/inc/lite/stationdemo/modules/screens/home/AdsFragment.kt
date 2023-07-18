package com.inc.lite.stationdemo.modules.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.inc.lite.stationdemo.R
import com.inc.lite.stationdemo.modules.components.VideoElement
import com.inc.lite.stationdemo.modules.models.AdsItem
import com.inc.lite.stationdemo.modules.models.AdsLayouts
import com.inc.lite.stationdemo.modules.models.AdsType
import com.inc.lite.stationdemo.modules.models.AdsUI
import com.inc.lite.stationdemo.modules.viewModels.MainViewModel
import com.inc.lite.stationdemo.util.AdjScreenSize

@Composable
fun AdsFragment(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {
    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)
    val uiState by viewModel.uiState.collectAsState()
    Box(
        modifier
            .height(size.dp(1142))
            .width(size.dp(800)),
        contentAlignment = Alignment.Center
    ) {
        when(uiState.ads.adsLayout){
            AdsLayouts.SingleImage -> {
                Layout1(uiState.ads.adsList)
            }
            AdsLayouts.TwoImage -> {
                Layout2(uiState.ads.adsList)
            }
            AdsLayouts.ThreeImage -> {
                Layout3(uiState.ads.adsList)
            }
            AdsLayouts.FourImage -> {
                Layout4(uiState.ads.adsList)
            }
        }
    }
}

@Composable
fun Layout1(
    ads: List<AdsItem> = listOf(
    AdsItem("", AdsType.Image)
)
) {
    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)

    if(ads.first().type == AdsType.Image){
        Image(
            painter = rememberAsyncImagePainter(
                model = ads.first().url
            ),
            contentDescription = "add",
            modifier  = Modifier
                .height(size.dp(1142))
                .width(size.dp(800)),
            contentScale = ContentScale.FillBounds
        )
    } else if (ads.first().type == AdsType.Video){
        VideoElement(
            modifier = Modifier
                .height(size.dp(1142))
                .width(size.dp(800)),
            url = ads.first().url
        )
    }



}
@Composable
fun Layout2(
    ads: List<AdsItem> = listOf(
        AdsItem("", AdsType.Image),
        AdsItem("", AdsType.Image)
    )
) {
    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)
    Column(Modifier.fillMaxSize()) {
        ads.forEach {
            if(it.type == AdsType.Image){
                Image(
                    modifier = Modifier
                        .height(size.dp(571))
                        .width(size.dp(800)),
                    painter = rememberAsyncImagePainter(
                        model = it.url
                    ),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds
                )
            } else {
                VideoElement(
                    modifier = Modifier
                        .height(size.dp(571))
                        .width(size.dp(800)),
                    url = it.url
                )
            }
        }
    }
}
@Composable
fun Layout3(
    ads: List<AdsItem> = listOf(
        AdsItem("", AdsType.Image),
        AdsItem("", AdsType.Image),
        AdsItem("", AdsType.Image)
    )
) {
    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)
    Column(Modifier.fillMaxSize()) {

        if(ads[0].type == AdsType.Image){
            Image(
                painter = rememberAsyncImagePainter(
                    model = ads[0].url
                ),
                contentDescription = "",
                modifier = Modifier
                    .height(size.dp(571))
                    .width(size.dp(800)),
            )
        } else {
            VideoElement(
                modifier = Modifier
                    .height(size.dp(571))
                    .width(size.dp(800)),
                url = ads[0].url
            )
        }

        Row(Modifier.height(size.dp(571))) {
            if(ads[1].type == AdsType.Image){
                Image(
                    painter = rememberAsyncImagePainter(
                        model = ads[1].url
                    ),
                    contentDescription = "",
                    modifier = Modifier
                        .height(size.dp(571))
                        .width(size.dp(400)),
                )
            }else{
                VideoElement(
                    modifier = Modifier
                        .height(size.dp(571))
                        .width(size.dp(400)),
                    url = ads[1].url
                )
            }

            if(ads[2].type == AdsType.Image){
                Image(
                    painter = rememberAsyncImagePainter(
                        model = ads[2].url
                    ),
                    contentDescription = "",
                    modifier = Modifier
                        .height(size.dp(571))
                        .width(size.dp(400)),
                )
            }else{
                VideoElement(
                    modifier = Modifier
                        .height(size.dp(571))
                        .width(size.dp(400)),
                    url = ads[2].url
                )
            }

        }

    }
}@Composable
fun Layout4(
    ads: List<AdsItem> = listOf(
        AdsItem("", AdsType.Image),
        AdsItem("", AdsType.Image),
        AdsItem("", AdsType.Image),
        AdsItem("", AdsType.Image)
    )
) {
    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)
    Column(Modifier.fillMaxSize()) {

        Row(Modifier.height(size.dp(571))) {
            if(ads[0].type == AdsType.Image){
                Image(
                    painter = rememberAsyncImagePainter(
                        model = ads[0].url
                    ),
                    contentDescription = "",
                    modifier = Modifier
                        .height(size.dp(571))
                        .width(size.dp(400)),
                )
            }else{
                VideoElement(
                    modifier = Modifier
                        .height(size.dp(571))
                        .width(size.dp(400)),
                    url = ads[0].url
                )
            }

            if(ads[1].type == AdsType.Image){
                Image(
                    painter = rememberAsyncImagePainter(
                        model = ads[1].url
                    ),
                    contentDescription = "",
                    modifier = Modifier
                        .height(size.dp(571))
                        .width(size.dp(400)),
                )
            }else{
                VideoElement(
                    modifier = Modifier
                        .height(size.dp(571))
                        .width(size.dp(400)),
                    url = ads[1].url
                )
            }

        }
        Row(Modifier.height(size.dp(571))) {
            if(ads[2].type == AdsType.Image){
                Image(
                    painter = rememberAsyncImagePainter(
                        model = ads[2].url
                    ),
                    contentDescription = "",
                    modifier = Modifier
                        .height(size.dp(571))
                        .width(size.dp(400)),
                )
            }else{
                VideoElement(
                    modifier = Modifier
                        .height(size.dp(571))
                        .width(size.dp(400)),
                    url = ads[2].url
                )
            }

            if(ads[3].type == AdsType.Image){
                Image(
                    painter = rememberAsyncImagePainter(
                        model = ads[3].url
                    ),
                    contentDescription = "",
                    modifier = Modifier
                        .height(size.dp(571))
                        .width(size.dp(400)),
                )
            }else{
                VideoElement(
                    modifier = Modifier
                        .height(size.dp(571))
                        .width(size.dp(400)),
                    url = ads[3].url
                )
            }

        }

    }
}

