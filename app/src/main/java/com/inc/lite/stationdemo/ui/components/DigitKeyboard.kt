package com.inc.lite.stationdemo.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inc.lite.stationdemo.R
import com.inc.lite.stationdemo.ui.theme.Black
import com.inc.lite.stationdemo.ui.theme.LightKey
import com.inc.lite.stationdemo.util.AdjScreenSize

@Preview(showBackground = true, widthDp = 800, heightDp = 1280)
@Composable
fun DigitKeyboard(
    modifier: Modifier = Modifier,
    onDigitClicked: (String) -> Unit = {_->}
) {
    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)
    val buttonsList = listOf(
        "1", "2", "3", "4", "5", "6", "7", "8", "9"," ","0","d"
    )

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = size.dp(56))
    ){

        LazyVerticalGrid(columns = GridCells.Fixed(3), horizontalArrangement = Arrangement.Center){
            items(buttonsList){
                if(it != "d"){
                    DigitKeyButton(
                        text = it,
                        onButtonClick = { key ->
                            onDigitClicked(key)
                        }
                    )
                }else{
                    DigitKeyButton(
                        text = "d",
                        image = R.drawable.remove,
                        onButtonClick = { key ->
                            onDigitClicked(key)
                        }
                    )
                }
            }
        }
    }


}

@Preview
@Composable
fun DigitKeyButton(
    text: String = "1",
    image: Int? = null,
    onButtonClick: (String) -> Unit = {_->}
) {

    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)

    Button(
        modifier = Modifier
            .height(size.dp(95))
            .width(size.dp(208))
            .padding(
                vertical = size.dp(10),
                horizontal = size.dp(16)
            ),
        onClick = {
            onButtonClick(text)
        },
        shape = RoundedCornerShape(size.dp(6)),
        colors = ButtonDefaults
            .buttonColors(
                containerColor = LightKey,
                contentColor = Black
            ),
        elevation = ButtonDefaults
            .elevatedButtonElevation(
                defaultElevation = 3.dp
            )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){

            if(image == null){
                Text(
                    text = text,
                    fontSize = size.sp(40),
                    fontWeight = FontWeight.Normal
                )
            }else {
                Icon(
                    painter = painterResource(id = image),
                    contentDescription = "",
                    modifier = Modifier.size(size.dp(43))
                )
            }


        }
    }



}