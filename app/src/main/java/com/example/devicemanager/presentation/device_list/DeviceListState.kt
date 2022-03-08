package com.example.devicemanager.presentation.device_list

import com.example.devicemanager.domain.model.Device

enum class DeviceListStateEnum {
    LOADING, SEARCHING, ERROR, LOADED
}

data class DeviceListState(

    val value: DeviceListStateEnum = DeviceListStateEnum.LOADING,
    var devices: List<Device> = emptyList(),
    val error: String = ""
)
