package com.example.devicemanager.data.remote

import com.example.devicemanager.data.remote.data_transfer_object.DeviceDTO
import retrofit2.http.GET

interface DeviceManagerApi {

    @GET("/devices")
    suspend fun getDevices(): List<DeviceDTO>
}