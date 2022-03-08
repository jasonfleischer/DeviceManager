package com.example.devicemanager.presentation.device_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.devicemanager.common.Constants
import com.example.devicemanager.domain.model.Device
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DeviceDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    interface NavigationProvider {
        fun returnToPreviousFragment()
    }

    var navigationProvider: NavigationProvider? = null

    var state: DeviceDetailsState = DeviceDetailsState()

    init {
        savedStateHandle.get<Device>(Constants.SavedState.CHOSEN_DEVICE)?.let { device ->
            val price: String? =
                if (device.currency.isNotBlank()) device.price.toString()+" "+device.currency else null
            state = DeviceDetailsState(
                device.title,
                device.type,
                device.isFavorite,
                price,
                device.description,
                device.imageUrl
            )
        }
    }
}