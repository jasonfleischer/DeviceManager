package com.example.devicemanager.dependency_injection

import com.example.devicemanager.common.Constants
import com.example.devicemanager.data.remote.DeviceManagerApi
import com.example.devicemanager.data.repository.DeviceRepositoryImpl
import com.example.devicemanager.domain.repository.DeviceRepository
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
    fun provideDeviceManagerApi(): DeviceManagerApi {
        return Retrofit.Builder()
            .baseUrl(Constants.Server.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DeviceManagerApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDeviceRepository(api: DeviceManagerApi): DeviceRepository {
        return DeviceRepositoryImpl(api)
    }
}