package com.inc.lite.stationdemo.modules.ui.models

data class LoginUiState(
    val isNumberSent: Boolean = false,
    val countriesList: List<Pair<String, String>> = listOf(
        Pair("Datong", "350"),
        Pair("Shuozhou", "351"),
        Pair("Xinzhou", "352"),
        Pair("Datong", "353"),
        Pair("Datong", "354"),
        Pair("Datong", "355"),
        Pair("Datong", "353"),
        Pair("Datong", "354"),
        Pair("Datong", "355"),
    ),
    val number: String = "572 872 094",
    val countyCode: String = "380",
    val smsCode: String = "",
    val passWord: String = "",
    val errorMessage: String = "",
    val isErrorShow: Boolean = true
)
