package com.inc.lite.stationdemo.model.uiState

import com.inc.lite.stationdemo.util.CountryPhones
import com.inc.lite.stationdemo.util.CountryCodes

data class LoginUiState(
    val isNumberSent: Boolean = false,
    val countriesList: List<CountryPhones> = CountryCodes().countryCodesEng,
    val county: CountryPhones = CountryPhones("Ukraine", "+380", "UA"),
//    val county: CountryPhones = CountryPhones("台灣", "+886", "TW"),
    val errorMessage: String = "",
    val isErrorShow: Boolean = false,
    val nickName: String = "",
    val email: String = ""
)
