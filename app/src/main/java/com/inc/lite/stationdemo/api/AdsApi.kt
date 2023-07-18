package com.inc.lite.stationdemo.api

import com.inc.lite.stationdemo.modules.models.Ads
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AdsApi {

    @GET("api/v1/adverts")
    suspend fun getAdds(
        @Query("stationUuid")
        stationID: String,
        @Query("sign")
        sign: String
    ): Response<Ads>
}