package com.example.devicemanager.domain.use_case

import com.example.devicemanager.common.Resource
import com.example.devicemanager.domain.model.Device
import com.example.devicemanager.domain.repository.DeviceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetDevicesUseCase @Inject constructor(
    private val repository: DeviceRepository
) {
    operator fun invoke(): Flow<Resource<List<Device>>> = flow {
        try {
            emit(Resource.Loading())
            val devices = repository.getDevices()
            emit(Resource.Success(devices))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Couldn't connect to server"))
        }
    }
}