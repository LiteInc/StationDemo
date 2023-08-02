package com.inc.lite.stationdemo.repository

import android.util.Log
import com.inc.lite.stationdemo.model.AdsItem
import com.inc.lite.stationdemo.model.AdsType
import com.inc.lite.stationdemo.model.ConfirmVerificationResponse
import com.inc.lite.stationdemo.model.ProgramItem
import com.inc.lite.stationdemo.model.StartVerificationResponse
import com.inc.lite.stationdemo.model.UserUpdate
import com.inc.lite.stationdemo.model.UserUpdateResponse
import javax.inject.Inject

class MainRepoImpl @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val authRepository: AuthRepository
): MainRepository {

    private val TAG = "MainRepositoryImpl"




    override suspend fun getAds(result: (List<AdsItem>, String?) -> Unit) {
        val list = mutableListOf<AdsItem>()
        val response =  remoteRepository.getAdds()
        Log.d(TAG, "Response : $response")
        if (response.first != null){

            response.first!!.data.forEach {
                list
                    .add(
                        AdsItem(
                            url = it.url1,
                            type = if(it.fileType == "2") AdsType.Video else AdsType.Image,
                            playTime = (it.playTime * 1000).toLong(),
                            order = it.order
                        )
                    )
            }
            result(list, null)
            Log.d(TAG,"response ads : ${response.second} ")
        } else{
            result(emptyList(),response.second)
            Log.e(TAG,"response  ads error : ${response.second} ")
        }
    }
    override suspend fun getPrograms(result: (List<ProgramItem>, String?) -> Unit){
        val response =  remoteRepository.getPrograms()
        Log.d(TAG, "Response : $response")
        if (response.first != null){
            result(response.first!!.data,null)
            Log.d(TAG,"response ads : ${response.second} ")
        } else{
            result(emptyList(),response.second)
            Log.e(TAG,"response  ads error : ${response.second} ")
        }

    }

    override suspend fun startVerification(
        phoneNumber: String,
        result: (StartVerificationResponse?, String?) -> Unit
    ) {
        val response =  authRepository.startVerification(phoneNumber)
        Log.d(TAG, "Response : $response")
        if (response.first != null){
            result(response.first!!,null)
            Log.d(TAG,"response startVerification : ${response.second} ")
        } else{
            result(null,response.second)
            Log.e(TAG,"response startVerification error : ${response.second} ")
        }
    }
    override suspend fun confirmVerification(
        phoneNumber: String,
        verificationCode: String,
        result: (ConfirmVerificationResponse?, String?) -> Unit
    ) {
        val response =  authRepository.confirmVerification(phoneNumber, verificationCode)
        Log.d(TAG, "Response : $response")
        if (response.first != null){
            result(response.first!!,null)
            Log.d(TAG,"response startVerification : ${response.second} ")
        } else{
            result(null,response.second)
            Log.e(TAG,"response startVerification error : ${response.second} ")
        }
    }

    override suspend fun confirmPassword(
        password: String,
        userId: String,
        result: (ConfirmVerificationResponse?, String?) -> Unit
    ) {
        val response =  authRepository.confirmPassword(password, userId)
        Log.d(TAG, "Response : $response")
        if (response.first != null){
            result(response.first!!,null)
            Log.d(TAG,"response confirmPassword : ${response.second} ")
        } else{
            result(null,response.second)
            Log.e(TAG,"response confirmPassword error: ${response.second} ")
        }
    }

    override suspend fun updateUser(
        token: String,
        userId: String,
        userInformation: UserUpdate,
        result: (UserUpdateResponse?, String?) -> Unit
    ) {
        val response =  authRepository.updateUser(token, userId, userInformation)
        Log.d(TAG, "Response : $response")
        if (response.first != null){
            result(response.first!!,null)
            Log.d(TAG,"response updateUser : ${response.second} ")
        } else{
            result(null,response.second)
            Log.e(TAG,"response updateUser error : ${response.second} ")
        }
    }

}