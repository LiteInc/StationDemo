package com.inc.lite.stationdemo.modules.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.inc.lite.stationdemo.modules.ui.theme.Background
import com.inc.lite.stationdemo.modules.ui.theme.LightGrayColor
import com.inc.lite.stationdemo.modules.ui.theme.MainColor
import com.inc.lite.stationdemo.util.AdjScreenSize


@Preview(widthDp = 800, heightDp = 1280, showBackground = false)
@Composable
fun EnterText(
    modifier: Modifier = Modifier,
    title: String = "What`s your nickname?",
    value: String = "",
    onNextButtonClick: (String)-> Unit = {_->}
) {

    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)

    var text by remember {
        mutableStateOf("")
    }

    Surface(
        modifier
            .fillMaxSize()
            .padding(
                top = size.dp(60)
            ),
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(top = size.dp(150)),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = title,
                fontSize = size.sp(28),
                color = LightGrayColor,
                textAlign = TextAlign.Center
            )

            TextField(
                modifier = Modifier
                    .padding(top = size.dp(75))
                    .width(size.dp(630)),
                value = value,
                onValueChange = {
                    text = it
                    Log.d("EnterText", "text : $text")
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Background,
                    unfocusedContainerColor = Background,
                    focusedIndicatorColor = MainColor,
                    unfocusedIndicatorColor = LightGrayColor,
                    focusedTextColor = Color.Black,

                ),
                textStyle = TextStyle(
                    fontSize = size.sp(28),
                    textAlign = TextAlign.Center
                ),
                maxLines = 1
            )
            
            Button(
                modifier = Modifier
                    .padding(top = size.dp(80))
                    .height(size.dp(80)),
                onClick = {
                    onNextButtonClick(text)
                },
                colors = ButtonDefaults.buttonColors(containerColor = MainColor)
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = size.dp(55)),
                    text = "Next",
                    fontSize = size.sp(24)
                )
            }
        }
    }
}

