package com.example.devicemanager

import com.example.devicemanager.common.Resource
import com.example.devicemanager.domain.model.Device
import com.example.devicemanager.domain.use_case.SearchDevicesUseCase
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Test

class SearchDevicesUseCaseTest {

    private val searchDevicesUseCase = SearchDevicesUseCase()
    private val devices = listOf(
        Device(
            "1",
            "A",
            "1",
            "F",
            0,
            "",
            true,
            ""
        ),
        Device(
            "2",
            "C",
            "3",
            "E F",
            0,
            "",
            true,
            ""
        )
    )

    @Test
    fun testSearchDevicesUseCase() = runBlocking {

        testSearchDevicesUseCaseHelper("", 2)
        testSearchDevicesUseCaseHelper("C", 1)
        testSearchDevicesUseCaseHelper("3", 1)
        testSearchDevicesUseCaseHelper("E", 1)

        testSearchDevicesUseCaseHelper("A", 1)
        testSearchDevicesUseCaseHelper("1", 1)
        testSearchDevicesUseCaseHelper("F", 2)

        testSearchDevicesUseCaseHelper("X", 0)
    }

    private fun testSearchDevicesUseCaseHelper(searchText: String, count: Int) = runBlocking {
        val resourceList = searchDevicesUseCase(devices, searchText).toList()
        assert(resourceList.first() is Resource.Loading)
        val successResource = resourceList[1]
        assert(successResource is Resource.Success)
        val results = successResource.data ?: emptyList()
        assertEquals(results.size, count)
    }
}