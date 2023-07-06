package com.inc.lite.stationdemo.modules.ui.repository

import javax.inject.Inject

class MainRepoImpl @Inject constructor(
    private val remoteRepository: RemoteRepository
): MainRepository{

}