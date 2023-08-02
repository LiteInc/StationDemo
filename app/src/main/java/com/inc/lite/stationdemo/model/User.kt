package com.inc.lite.stationdemo.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("avatar_url")
    val avatarUrl: String? = null,
    var email: String? = null,
    var id: Int = 0,
    val nickname: String? = null,
    @SerializedName("phone_number")
    val phoneNumber: String = "",
    @SerializedName("user_locale")
    val locale: String? = null
)