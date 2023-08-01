package com.inc.lite.stationdemo.model.uiState

data class LoginUiState(
    val isNumberSent: Boolean = false,
    val countriesList: List<Pair<String, String>> = listOf(
        Pair("Datong", "350"),
        Pair("Shuozhou", "351"),
        Pair("Xinzhou", "352"),
        Pair("Ukraine", "353"),
        Pair("Netherlands", "354"),
        Pair("USA", "355"),
        Pair("Italy", "353"),
        Pair("France", "354"),
        Pair("Germany", "355"),
    ),
    val number: Array<String?> = arrayOfNulls(9),
    val countyCode: String = "380",
    val countryName: String = "Ukraine",
    val smsCode: Array<String?> = arrayOfNulls(4),
    val password: Array<String?> = arrayOf("1","2","","","",""),
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
        if (!number.contentEquals(other.number)) return false
        if (countyCode != other.countyCode) return false
        if (!smsCode.contentEquals(other.smsCode)) return false
        if (!password.contentEquals(other.password)) return false
        if (errorMessage != other.errorMessage) return false
        if (isErrorShow != other.isErrorShow) return false

        return true
    }

    override fun hashCode(): Int {
        var result = isNumberSent.hashCode()
        result = 31 * result + countriesList.hashCode()
        result = 31 * result + number.contentHashCode()
        result = 31 * result + countyCode.hashCode()
        result = 31 * result + smsCode.hashCode()
        result = 31 * result + password.hashCode()
        result = 31 * result + errorMessage.hashCode()
        result = 31 * result + isErrorShow.hashCode()
        return result
    }
    fun getFullNumber(): String{
        var numberBody = ""
        number.forEach {
            numberBody += it ?: ""
        }
        return "$countyCode $numberBody"
    }
}
