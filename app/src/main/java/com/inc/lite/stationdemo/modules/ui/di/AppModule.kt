package com.inc.lite.stationdemo.modules.ui.di

import com.inc.lite.stationdemo.modules.ui.repository.MainRepoImpl
import com.inc.lite.stationdemo.modules.ui.repository.MainRepository
import com.inc.lite.stationdemo.modules.ui.repository.RemoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRemoteRepository(): RemoteRepository{
        return RemoteRepository()
    }

    @Provides
    @Singleton
    fun provideMainRepository(
        remoteRepository: RemoteRepository
    ): MainRepository{
        return MainRepoImpl(remoteRepository)
    }

}