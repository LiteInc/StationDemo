package com.inc.lite.stationdemo.repository

import android.util.Log
import com.inc.lite.stationdemo.MyApplication
import com.inc.lite.stationdemo.api.AdsApi
import com.inc.lite.stationdemo.model.Ads
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    private val RemoteApi: AdsApi,
    private val app: MyApplication
){
    suspend fun getAdds(): Pair<Ads?, String> {
        Log.d("RemoteRepo", "GetAdsRequest")
    try {
        val response = RemoteApi.getAdds(app.getStationImei(), app.getAdsSign())
        if (response.isSuccessful) {
            val result = response.body()
            Log.d("RemoteRepo", "result: $result")
           return Pair(result, "Successful") // Return the result and an empty message
        } else {
            val message = response.errorBody()?.string() ?: "Unknown error occurred"
            Log.d("RemoteRepo", "message: $message")
           return Pair(null, message) // Return null result and the error message
        }
    } catch (e: Exception) {
        val message = "Network error occurred: ${e.message}"
        return Pair(null, message) // Return null result and the error message
        }
    }


}