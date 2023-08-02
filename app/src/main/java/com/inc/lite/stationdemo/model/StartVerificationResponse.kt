package com.inc.lite.stationdemo.model

import com.google.gson.annotations.SerializedName

data class StartVerificationResponse(
    @SerializedName("need_confirmation")
    val needConfirmation: Boolean,
    @SerializedName("user_id")
    val userId: Int,
)
