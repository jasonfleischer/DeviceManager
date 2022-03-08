package com.example.devicemanager.domain.repository

import com.example.devicemanager.domain.model.Device

interface DeviceRepository {
    suspend fun getDevices(): List<Device>
}