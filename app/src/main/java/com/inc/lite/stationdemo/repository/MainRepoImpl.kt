package com.inc.lite.stationdemo.repository

import android.util.Log
import com.inc.lite.stationdemo.model.AdsItem
import com.inc.lite.stationdemo.model.AdsType
import javax.inject.Inject

class MainRepoImpl @Inject constructor(
    private val remoteRepository: RemoteRepository
): MainRepository {

    private val TAG = "MainRepositoryImpl"

    override suspend fun getAds(): List<AdsItem> {
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
                            playTime = (it.playTime * 1000).toLong()
                        )
                    )
            }
            Log.d(TAG,"response ads : ${response.second} ")
        } else{
            Log.e(TAG,"response  ads error : ${response.second} ")
        }
        return list
    }

}