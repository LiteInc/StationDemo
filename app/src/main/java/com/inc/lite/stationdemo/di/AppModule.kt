package com.inc.lite.stationdemo.di

import com.inc.lite.stationdemo.MyApplication
import com.inc.lite.stationdemo.api.AdsApi
import com.inc.lite.stationdemo.repository.MainRepoImpl
import com.inc.lite.stationdemo.repository.MainRepository
import com.inc.lite.stationdemo.repository.RemoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRemoteRepository(
        remoteApi: AdsApi,
        app: MyApplication
    ): RemoteRepository {
        return RemoteRepository(remoteApi, app)
    }

    @Provides
    @Singleton
    fun provideMainRepository(
        remoteRepository: RemoteRepository
    ): MainRepository {
        return MainRepoImpl(remoteRepository)
    }

    @Provides
    @Singleton
    fun provideAdsRemoteInstance(
        app: MyApplication
    ): AdsApi{
        val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(app.getAddUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AdsApi::class.java)
        }
        return retrofit
    }

    @Provides
    @Singleton
    fun provideApplication(): MyApplication {
        return MyApplication()
    }
}