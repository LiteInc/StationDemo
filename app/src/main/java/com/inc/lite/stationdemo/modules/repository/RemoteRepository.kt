package com.inc.lite.stationdemo.modules.repository

import com.inc.lite.stationdemo.MyApplication
import com.inc.lite.stationdemo.api.AdsApi
import com.inc.lite.stationdemo.modules.models.Ads
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    private val RemoteApi: AdsApi,
    private val app: MyApplication
){
    suspend fun getAdds(): Pair<Ads?, String> {
    return try {
        val response = RemoteApi.getAdds(app.getStationImei(), app.getAdsSign())
        if (response.isSuccessful) {
            val result = response.body()
            Pair(result, "Successful") // Return the result and an empty message
        } else {
            val message = response.errorBody()?.string() ?: "Unknown error occurred"
            Pair(null, message) // Return null result and the error message
        }
    } catch (e: Exception) {
        val message = "Network error occurred: ${e.message}"
        Pair(null, message) // Return null result and the error message
        }
    }


}