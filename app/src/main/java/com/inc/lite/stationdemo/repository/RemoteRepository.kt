package com.inc.lite.stationdemo.repository

import android.util.Log
import com.inc.lite.stationdemo.MyApplication
import com.inc.lite.stationdemo.api.AdsApi
import com.inc.lite.stationdemo.model.AdsRequest
import com.inc.lite.stationdemo.model.Programs
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    private val RemoteApi: AdsApi,
    private val app: MyApplication
){
    suspend fun getAdds(): Pair<AdsRequest?, String> {
        Log.d("RemoteRepo", "GetAdsRequest")
        return try {
            val response = RemoteApi.getAdds(app.getStationImei(), app.getAdsSign())
            if (response.isSuccessful) {
                val result = response.body()
                Log.d("RemoteRepo", "result: $result")
                Pair(result, "Successful") // Return the result and an empty message
            } else {
                val message = response.errorBody()?.string() ?: "Unknown error occurred"
                Log.d("RemoteRepo", "message: $message")
                Pair(null, message) // Return null result and the error message
            }
        } catch (e: Exception) {
            val message = "Network error occurred: ${e.message}"
            Pair(null, message) // Return null result and the error message
        }
    }

    suspend fun getPrograms(): Pair<Programs?, String> {
        Log.d("RemoteRepo", "GetAdsRequest")
        return try {
            val response = RemoteApi.getPrograms(app.getStationImei(), app.getAdsSign())
            if (response.isSuccessful) {
                val result = response.body()
                Log.d("RemoteRepo", "result: $result")
                Pair(result, "Successful") // Return the result and an empty message
            } else {
                val message = response.errorBody()?.string() ?: "Unknown error occurred"
                Log.d("RemoteRepo", "message: $message")
                Pair(null, message) // Return null result and the error message
            }
        } catch (e: Exception) {
            val message = "Network error occurred: ${e.message}"
            Pair(null, message) // Return null result and the error message
        }
    }


}