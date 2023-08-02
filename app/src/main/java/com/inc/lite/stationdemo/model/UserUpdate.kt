package com.inc.lite.stationdemo.model

import android.provider.ContactsContract.CommonDataKinds.Nickname

data class UserUpdate(
    val password: String,
    val nickname: String?,
    val email: String?
)
