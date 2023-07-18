package com.inc.lite.stationdemo.modules.repository

import javax.inject.Inject

class MainRepoImpl @Inject constructor(
    private val remoteRepository: RemoteRepository
): MainRepository{

}