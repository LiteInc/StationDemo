package com.inc.lite.stationdemo.repository

import android.util.Log
import com.inc.lite.stationdemo.MyApplication
import com.inc.lite.stationdemo.api.AuthApi
import com.inc.lite.stationdemo.model.ConfirmVerificationResponse
import com.inc.lite.stationdemo.model.StartVerificationResponse
import com.inc.lite.stationdemo.model.UserUpdate
import com.inc.lite.stationdemo.model.UserUpdateResponse
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authApi: AuthApi,
    private val app: MyApplication
) {

    private val TAG = "AuthRepository"

    suspend fun startVerification(phoneNumber: String): Pair<StartVerificationResponse?, String> {
        Log.d(TAG, "Start verification : $phoneNumber")
        return try {
            val response = authApi.startVerificationAsync(phoneNumber)
            if (response.isSuccessful) {
                val result = response.body()
                Log.d(TAG, "result: $result")
                Pair(result, "Successful") // Return the result and an empty message
            } else {
                val message = response.errorBody()?.string() ?: "Unknown error occurred"
                Log.d(TAG, "message: $message")
                Pair(null, message) // Return null result and the error message
            }
        } catch (e: Exception) {
            val message = "Network error occurred: ${e.message}"
            Pair(null, message) // Return null result and the error message
        }
    }

    suspend fun confirmVerification(phoneNumber: String, verificationCode: String): Pair<ConfirmVerificationResponse?, String> {
        Log.d(TAG, "Start verification : $phoneNumber")
        return try {
            val response = authApi.confirmVerificationAsync(phoneNumber, verificationCode)
            if (response.isSuccessful) {
                val result = response.body()
                Log.d(TAG, "result: $result")
                Pair(result, "Successful") // Return the result and an empty message
            } else {
                val message = response.errorBody()?.string() ?: "Unknown error occurred"
                Log.d(TAG, "message: $message")
                Pair(null, message) // Return null result and the error message
            }
        } catch (e: Exception) {
            val message = "Network error occurred: ${e.message}"
            Pair(null, message) // Return null result and the error message
        }
    }
    suspend fun confirmPassword(password: String, userId: String): Pair<ConfirmVerificationResponse?, String> {
        Log.d(TAG, "Confirm password : $password")
        return try {
            val response = authApi.confirmPasswordAsync(password, userId)
            if (response.isSuccessful) {
                val result = response.body()
                Log.d(TAG, "result: $result")
                Pair(result, "Successful") // Return the result and an empty message
            } else {
                val message = response.errorBody()?.string() ?: "Unknown error occurred"
                Log.d(TAG, "message: $message")
                Pair(null, message) // Return null result and the error message
            }
        } catch (e: Exception) {
            val message = "Network error occurred: ${e.message}"
            Pair(null, message) // Return null result and the error message
        }
    }
    suspend fun updateUser(token: String, userId: String, userInformation: UserUpdate): Pair<UserUpdateResponse?, String> {
        Log.d(TAG, "Update user : $userId")
        return try {
            val response = authApi.updateUser(token,userId, userInformation)
            if (response.isSuccessful) {
                val result = response.body()
                Log.d(TAG, "result: $result")
                Pair(result, "Successful") // Return the result and an empty message
            } else {
                val message = response.errorBody()?.string() ?: "Unknown error occurred"
                Log.d(TAG, "message: $message")
                Pair(null, message) // Return null result and the error message
            }
        } catch (e: Exception) {
            val message = "Network error occurred: ${e.message}"
            Pair(null, message) // Return null result and the error message
        }
    }
}