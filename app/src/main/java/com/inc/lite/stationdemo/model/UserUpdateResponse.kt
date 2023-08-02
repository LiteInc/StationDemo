package com.inc.lite.stationdemo.model

import com.google.gson.annotations.SerializedName

data class UserUpdateResponse(
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    val email: String?,
    val id: Int,
    val latitude: Any,
    val longitude: Any,
    val nickname: String?,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("user_locale")
    val userLocale: String?
)