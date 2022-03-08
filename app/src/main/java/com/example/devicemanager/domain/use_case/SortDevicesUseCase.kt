package com.example.devicemanager.domain.use_case

import com.example.devicemanager.domain.model.Device
import javax.inject.Inject

class SortDevicesUseCase @Inject constructor(){

    enum class SortType {
        ASCENDING_BY_TITLE, DESCENDING_BY_TITLE, ASCENDING_BY_TYPE, DESCENDING_BY_TYPE
    }

    operator fun invoke(devices: List<Device>, sortType: SortType = SortType.ASCENDING_BY_TITLE): List<Device>{
        return when(sortType){
            SortType.ASCENDING_BY_TITLE -> devices.sortedBy { it.title }
            SortType.DESCENDING_BY_TITLE -> devices.sortedByDescending { it.title }
            SortType.ASCENDING_BY_TYPE -> devices.sortedBy { it.type }
            SortType.DESCENDING_BY_TYPE -> devices.sortedByDescending { it.type }
        }
    }
}