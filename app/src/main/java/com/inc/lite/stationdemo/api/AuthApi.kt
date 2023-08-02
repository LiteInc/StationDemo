package com.inc.lite.stationdemo.api

import com.inc.lite.stationdemo.model.ConfirmVerificationResponse
import com.inc.lite.stationdemo.model.StartVerificationResponse
import com.inc.lite.stationdemo.model.UserUpdate
import com.inc.lite.stationdemo.model.UserUpdateResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface AuthApi {

    @POST("/station_api/v1/start_verification")
    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    suspend fun startVerificationAsync(
        @Field("phone_number") phoneNumber: String,
    ): Response<StartVerificationResponse>

    @POST("/station_api/v1/confirm_verification")
    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    suspend fun confirmVerificationAsync(
        @Field("phone_number") phoneNumber: String,
        @Field("verification_code") verificationCode: String,
    ): Response<ConfirmVerificationResponse>

    @POST("/station_api/v1/confirm_password")
    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    suspend fun confirmPasswordAsync(
        @Field("password") password: String,
        @Field("id") userId: String,
    ): Response<ConfirmVerificationResponse>

    @PUT("/station_api/v1/users/{userId}")
//    @FormUrlEncoded
    suspend fun updateUser(
        @Header("token") token: String,
        @Path("userId") userId: String,
        @Body userInfo: UserUpdate
    ): Response<UserUpdateResponse>


}