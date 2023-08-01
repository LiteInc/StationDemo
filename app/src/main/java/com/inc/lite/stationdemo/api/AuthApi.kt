package com.inc.lite.stationdemo.api

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthApi {

    @POST("/api/v1/start_verification")
    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    fun startVerificationAsync(
        @Field("phone_number") phoneNumber: String,
    ): Response<Unit>

//    @POST("/api/v1/confirm_verification")
//    @FormUrlEncoded
//    @Headers("Content-Type: application/x-www-form-urlencoded")
//    fun confirmVerificationAsync(
//        @Field("phone_number") phoneNumber: String,
//        @Field("verification_code") verificationCode: String
//    ): Deferred<Response<ConfirmVerificationOutput>>
//
//    @PATCH("/api/v1/users/{id}")
//    @Headers("Content-Type: application/json")
//    fun provideAuthDataAsync(
//        @Header("token") token: String,
//        @Path("id") id: Long,
//        @Body input: AuthDataInput
//    ): Deferred<Response<ProvideAuthDataOutput>>

}