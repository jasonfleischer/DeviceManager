package com.example.devicemanager.repo

import com.example.devicemanager.domain.model.Device
import com.example.devicemanager.domain.repository.DeviceRepository

class TestDeviceRepositoryImpl : DeviceRepository {
    override suspend fun getDevices(): List<Device> {
        return listOf(
            Device(
                "1",
                "",
                " ",
                "",
                0,
                "",
                false,
                ""
            ),
            Device(
                "2",
                "     ",
                "  ",
                "    ",
                0,
                "   ",
                false,
                "  "
            ),
            Device(
                "3",
                "",
                "",
                "",
                0,
                "",
                false,
                ""
            )
        )
    }
}