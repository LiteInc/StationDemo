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
import com.inc.lite.stationdemo.modules.models.AdsLayouts
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
        when(uiState.adsLayout){
            AdsLayouts.SingleImage -> {
                Layout1()
            }
            AdsLayouts.TwoImage -> {
                Layout2("http://res.cloudinary.com/riisu/image/upload/v1683550824/vsbdycwwiexcpkkhldyn.jpg", "http://res.cloudinary.com/riisu/image/upload/v1683550813/uqrhemv8erk4qil2xmsi.jpg")
            }
            AdsLayouts.ThreeImage -> {
                Layout3()
            }
            AdsLayouts.FourImage -> {
                Layout4()
            }
        }
    }
}

@Composable
fun Layout1(
    image: String = ""
) {
    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)
    Image(
        painter = rememberAsyncImagePainter(
            model = image
        ),
        contentDescription = "add",
        modifier  = Modifier
            .height(size.dp(1142))
            .width(size.dp(800)),
        contentScale = ContentScale.FillBounds
    )
}
@Composable
fun Layout2(
    image1: String = "",
    image2: String = ""
) {
    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)
    Column(Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.weight(1f),
            painter = rememberAsyncImagePainter(
                model = image1
            ),
            contentDescription = "",
            contentScale = ContentScale.FillBounds
        )

        Image(
            painter = rememberAsyncImagePainter(
                model = image2
            ),
            contentDescription = "",
            modifier = Modifier.weight(1f),
            contentScale = ContentScale.FillBounds
        )

    }
}
@Composable
fun Layout3(
    image1: String = "",
    image2: String = "",
    image3: String = ""
) {
    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)
    Column(Modifier.fillMaxSize()) {
        Image(
            painter = rememberAsyncImagePainter(
                model = image1
            ),
            contentDescription = "",
            modifier = Modifier.weight(1f),
        )

        Row(Modifier.height(size.dp(571))) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = image2
                ),
                contentDescription = "",
                modifier = Modifier.weight(1f),
            )
            Image(
                painter = rememberAsyncImagePainter(
                    model = image3
                ),
                contentDescription = "",
                modifier = Modifier.weight(1f),
            )

        }

    }
}@Composable
fun Layout4(
    image1: String = "",
    image2: String = "",
    image3: String = "",
    image4: String = ""
) {
    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)
    Column(Modifier.fillMaxSize()) {

        Row(Modifier.height(size.dp(571))) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = image1
                ),
                contentDescription = "",
                modifier = Modifier.weight(1f),
                contentScale = ContentScale.FillBounds
            )
            Image(
                painter = rememberAsyncImagePainter(
                    model = image2
                ),
                contentDescription = "",
                modifier = Modifier.weight(1f),
                contentScale = ContentScale.FillBounds
            )

        }
        Row(Modifier.height(size.dp(571))) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = image3
                ),
                contentDescription = "",
                modifier = Modifier.weight(1f),
                contentScale = ContentScale.FillBounds
            )
            Image(
                painter = rememberAsyncImagePainter(
                    model = image4
                ),
                contentDescription = "",
                modifier = Modifier.weight(1f),
                contentScale = ContentScale.FillBounds
            )

        }

    }
}