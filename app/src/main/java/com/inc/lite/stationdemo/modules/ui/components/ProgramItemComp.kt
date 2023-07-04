package com.inc.lite.stationdemo.modules.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.inc.lite.stationdemo.modules.ui.models.ProgramItem

@Preview
@Composable
fun ProgramItemComponent(
    modifier: Modifier = Modifier,
    programItem: ProgramItem = ProgramItem("Google Maps")
) {
    Surface(
        modifier = modifier
            .height(152.dp)
            .width(152.dp),
        color =  Color.Transparent
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .size(100.dp)
                    .padding(bottom = 20.dp),
                painter = painterResource(id = programItem.imageUrl),
                contentDescription = ""
            )
            Text(
                text = programItem.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Light
            )
        }
    }
}