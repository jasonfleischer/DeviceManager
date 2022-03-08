package com.example.devicemanager

import com.example.devicemanager.common.Resource
import com.example.devicemanager.domain.use_case.GetDevicesUseCase
import com.example.devicemanager.repo.TestDeviceRepositoryImpl
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetDevicesUseCaseTest {

    private val getDevicesUseCase = GetDevicesUseCase(TestDeviceRepositoryImpl())

    @Test
    fun testGetDevicesUseCase() = runBlocking {

        val resourceList = getDevicesUseCase().toList()
        assert(resourceList.first() is Resource.Loading)
        val successResource = resourceList[1]
        assert(successResource is Resource.Success)
        val results = successResource.data ?: emptyList()
        assertEquals(results.size, 3)
    }
}