package com.inc.lite.stationdemo.modules.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inc.lite.stationdemo.modules.ui.theme.White
import com.inc.lite.stationdemo.util.AdjScreenSize

@Preview
@Composable
fun DropDownList(
    modifier: Modifier = Modifier,
    content: List<Pair<String,String>> = emptyList(),
    onDismiss: ()-> Unit = {},
    onItemSelected: (Pair<String,String>) -> Unit = {_->}
) {

    val config= LocalConfiguration.current
    val size = AdjScreenSize(config)
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        Box (
            Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.21f))
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    onDismiss()
                }
            )
        Card(
            Modifier
                .height(size.dp(352))
                .width(size.dp(332))
                .offset(y = size.dp(-140)),
            elevation = CardDefaults.elevatedCardElevation(2.dp),
            colors = CardDefaults.cardColors(containerColor = White)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = size.dp(40),
                        vertical = size.dp(20)
                    ),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                items(content){
                    CountryItem(
                        it.first,
                        it.second,
                        onItemClick = onItemSelected
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun CountryItem(
    title: String = "Ukraine",
    code: String = "380",
    onItemClick: (Pair<String,String>) -> Unit = {_->},
) {

    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)

    Row(
        Modifier
            .fillMaxWidth()
            .clickable {
                onItemClick(Pair(title, code))
            }
    ) {
        Text(
            modifier = Modifier.width(size.dp(155)),
            text = title,
            fontSize = size.sp(28)
        )
        Text(
            text = code,
            fontWeight = FontWeight.Bold,
            fontSize = size.sp(28)
        )
    }
}