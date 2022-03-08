package com.example.devicemanager

import com.example.devicemanager.domain.model.Device
import com.example.devicemanager.domain.use_case.SortDevicesUseCase
import junit.framework.Assert.assertEquals
import org.junit.Test

class SortDevicesUseCaseTest {

    private val sortDevicesUseCase = SortDevicesUseCase()

    private var devices = listOf(
        Device(
            "1",
            "A",
            "1",
            "A",
            90,
            "CAD",
            true,
            ""
        ),
        Device(
            "2",
            "C",
            "3",
            "C",
            90,
            "CAD",
            true,
            ""
        ),
        Device(
            "3",
            "B",
            "Phone",
            "B",
            90,
            "CAD",
            true,
            ""
        )
    )

    @Test
    fun testSortDevicesUseCase() {

        devices = sortDevicesUseCase(devices, SortDevicesUseCase.SortType.ASCENDING_BY_TITLE)
        assertEquals(devices[0].title, "A")
        assertEquals(devices[1].title, "B")
        assertEquals(devices[2].title, "C")

        devices = sortDevicesUseCase(devices, SortDevicesUseCase.SortType.DESCENDING_BY_TITLE)
        assertEquals(devices[0].title, "C")
        assertEquals(devices[1].title, "B")
        assertEquals(devices[2].title, "A")
    }
}