package com.inc.lite.stationdemo.model.uiState

import com.inc.lite.stationdemo.util.CountryCodes

data class LoginUiState(
    val isNumberSent: Boolean = false,
    val countriesList: List<Pair<String, String>> = CountryCodes().countryListChinese,
    val countyCode: String = "886",
    val countryName: String = "乌克兰",
//    val countyCode: String = "380",
//    val countryName: String = "乌克兰",
    val errorMessage: String = "",
    val isErrorShow: Boolean = false,
    val nickName: String = "",
    val email: String = ""
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LoginUiState

        if (isNumberSent != other.isNumberSent) return false
        if (countriesList != other.countriesList) return false
        if (countyCode != other.countyCode) return false
        if (errorMessage != other.errorMessage) return false
        if (isErrorShow != other.isErrorShow) return false

        return true
    }

    override fun hashCode(): Int {
        var result = isNumberSent.hashCode()
        result = 31 * result + countriesList.hashCode()
        result = 31 * result + countyCode.hashCode()
        result = 31 * result + errorMessage.hashCode()
        result = 31 * result + isErrorShow.hashCode()
        return result
    }

}
