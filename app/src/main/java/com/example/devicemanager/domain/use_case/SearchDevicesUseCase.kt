package com.example.devicemanager.domain.use_case

import com.example.devicemanager.common.Resource
import com.example.devicemanager.domain.model.Device
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchDevicesUseCase @Inject constructor() {

    operator fun invoke(
        devices: List<Device>,
        searchText: String = ""
    ): Flow<Resource<List<Device>>> = flow {
        emit(Resource.Loading())
        val result = if (searchText.isEmpty()) {
            devices
        } else {
            devices.filter { device ->
                device.title.contains(searchText, true) ||
                        device.type.contains(searchText, true) ||
                        device.description.contains(searchText, true)
            }
        }
        emit(Resource.Success(result))
    }
}