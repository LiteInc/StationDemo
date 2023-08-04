package com.inc.lite.stationdemo.repository

import com.inc.lite.stationdemo.model.AdsItem
import com.inc.lite.stationdemo.model.ConfirmVerificationResponse
import com.inc.lite.stationdemo.model.ProgramItem
import com.inc.lite.stationdemo.model.StartVerificationResponse
import com.inc.lite.stationdemo.model.UserUpdate
import com.inc.lite.stationdemo.model.UserUpdateResponse

interface  MainRepository {
    suspend fun getAds(result: (List<AdsItem>, String?) -> Unit)
    suspend fun getPrograms(result: (List<ProgramItem>, String?) -> Unit)
    suspend fun startVerification(phoneNumber: String, result: (StartVerificationResponse?, String?) -> Unit)
    suspend fun confirmVerification(phoneNumber: String, verificationCode: String, result: (ConfirmVerificationResponse?, String?) -> Unit)
    suspend fun confirmPassword(password: String, userId: String, result: (ConfirmVerificationResponse?, String?) -> Unit)
    suspend fun updateUser(token: String, userId: String,userInformation: UserUpdate, result: (UserUpdateResponse?, String?) -> Unit)

}