package com.inc.lite.stationdemo.api

import com.inc.lite.stationdemo.model.AdsRequest
import com.inc.lite.stationdemo.model.Programs
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AdsApi {

    @GET("station_api/v1/adverts")
    suspend fun getAdds(
        @Query("stationUuid")
        stationID: String,
        @Query("sign")
        sign: String
    ): Response<AdsRequest>
    @GET("station_api/v1/programs")
    suspend fun getPrograms(
        @Query("stationUuid")
        stationID: String,
        @Query("sign")
        sign: String
    ): Response<Programs>
}