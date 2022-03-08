package com.example.devicemanager.data.repository

import com.example.devicemanager.data.remote.DeviceManagerApi
import com.example.devicemanager.domain.model.Device
import com.example.devicemanager.domain.repository.DeviceRepository
import java.util.*
import javax.inject.Inject

class DeviceRepositoryImpl @Inject constructor(
    private val api: DeviceManagerApi
) : DeviceRepository {

    override suspend fun getDevices(): List<Device> {
        return api.getDevices().map { dto ->
            Device(
                dto.id ?: UUID.randomUUID().toString(),
                dto.title ?: "",
                dto.type?: "",
                dto.description?: "",
                dto.price?: 0,
                dto.currency?: "",
                dto.isFavorite?: false,
                dto.imageUrl?: ""
            )
        }
    }
}