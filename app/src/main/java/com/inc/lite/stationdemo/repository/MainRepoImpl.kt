package com.inc.lite.stationdemo.repository

import android.util.Log
import com.inc.lite.stationdemo.model.AdsItem
import com.inc.lite.stationdemo.model.AdsType
import com.inc.lite.stationdemo.model.ProgramItem
import javax.inject.Inject

class MainRepoImpl @Inject constructor(
    private val remoteRepository: RemoteRepository
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

}