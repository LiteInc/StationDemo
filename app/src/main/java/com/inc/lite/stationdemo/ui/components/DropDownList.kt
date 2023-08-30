package com.inc.lite.stationdemo.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inc.lite.stationdemo.R
import com.inc.lite.stationdemo.ui.screens.home.Keyboard
import com.inc.lite.stationdemo.ui.screens.home.keyboardAsState
import com.inc.lite.stationdemo.ui.theme.Background
import com.inc.lite.stationdemo.ui.theme.LightGrayColor
import com.inc.lite.stationdemo.ui.theme.MainColor
import com.inc.lite.stationdemo.ui.theme.White
import com.inc.lite.stationdemo.ui.theme.mainTextStyle
import com.inc.lite.stationdemo.ui.theme.pingFangTCFamily
import com.inc.lite.stationdemo.util.AdjScreenSize
import com.inc.lite.stationdemo.util.CountryPhones

@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@Preview
@Composable
fun DropDownList(
    modifier: Modifier = Modifier,
    content: List<CountryPhones> = emptyList(),
    onDismiss: ()-> Unit = {},
    onItemSelected: (CountryPhones) -> Unit = {_->}
) {

    val config= LocalConfiguration.current
    val size = AdjScreenSize(config)
    val isKeyboardOpen by keyboardAsState()


    var searchCharacter by remember { mutableStateOf("") }
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
                .height(size.dp(900))
                .width(size.dp(750))
                .padding(top = if (isKeyboardOpen == Keyboard.Opened) size.dp(200) else size.dp(0))
                .offset(y = size.dp(-50)),
            elevation = CardDefaults.elevatedCardElevation(2.dp),
            colors = CardDefaults.cardColors(containerColor = White)
        ) {



            Row(verticalAlignment = Alignment.CenterVertically){
                OutlinedTextField(
                    value = searchCharacter,
                    onValueChange = { newValue ->
                        searchCharacter = newValue
                    },
                    placeholder = {
                        Text(
                            stringResource(id = R.string.search),
                            fontSize = size.sp(24),
                            color = LightGrayColor
                        )
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = size.dp(5), top = size.dp(10))
                        .padding(horizontal = size.dp(20)),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Background,
                        unfocusedContainerColor = Background,
                        focusedIndicatorColor = MainColor,
                        unfocusedIndicatorColor = LightGrayColor,
                        focusedTextColor = Color.Black,
                        cursorColor = MainColor,
                        disabledContainerColor = Background

                    ),
                    shape = RoundedCornerShape(20),
                    textStyle = TextStyle(
                        fontSize = size.sp(32),
                        fontFamily = pingFangTCFamily
                    ),
                    maxLines = 1,
                    leadingIcon = {
                        Image(
                            painter = painterResource(id = R.drawable.search_icon),
                            contentDescription = "",
                            modifier = Modifier.size(size.dp(50))
                        )
                    }
                )
                Spacer(Modifier.width(size.dp(10)))
                Text(
                    text = stringResource(id = R.string.cancel),
                    fontSize = size.sp(32),
                    style = mainTextStyle,
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null // Disables standard click interaction
                    ){
                        onDismiss()
                    }
                )
                Spacer(Modifier.width(size.dp(30)))

            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = size.dp(40),
                        vertical = size.dp(20)
                    ),
//                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ){

                val filteredItems = content.filter {
                    it.name.startsWith(searchCharacter, ignoreCase = true)
                }



                val groupedItems = filteredItems.groupBy { it.name.first().toUpperCase() }

                groupedItems.forEach { (header, groupItems) ->
                    stickyHeader {
                        // Render sticky header for each new first letter
                        HeaderItem(text = header)
                    }

                    items(groupItems) { item ->
                        // Render your regular list item here
                        CountryItem(
                            countryPhones = item,
                            onItemClick = {
                                onItemSelected(it)
                            }
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun CountryItem(
    countryPhones: CountryPhones,
    onItemClick: (CountryPhones) -> Unit = {_->},
) {

    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)

    Column {
        Spacer(modifier = Modifier.height(size.dp(15)))
        Row(
            Modifier
                .fillMaxWidth()
                .clickable {
                    onItemClick(countryPhones)
                },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.width(size.dp(400)),
                text = countryPhones.name,
                fontSize = size.sp(36),
                style = mainTextStyle
            )
            Text(
                text = countryPhones.phoneCode,
                fontWeight = FontWeight.Bold,
                fontSize = size.sp(36),
                style = mainTextStyle
            )
        }
        Spacer(modifier = Modifier.height(size.dp(15)))
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(Color.Gray)
        )


    }
}

@Composable
fun HeaderItem(
    text: Char
) {
    val configuration = LocalConfiguration.current
    val size = AdjScreenSize(configuration)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(White)
            .padding(vertical = size.dp(15)),
        contentAlignment = Alignment.CenterStart
    ){
        Text(
            text = text.toString(),
            fontSize = size.sp(36),
            style = mainTextStyle
        )
    }
}