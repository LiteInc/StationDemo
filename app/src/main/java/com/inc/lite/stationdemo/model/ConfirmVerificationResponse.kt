package com.inc.lite.stationdemo.model

data class ConfirmVerificationResponse(
    val token: String,
    val user: User
)