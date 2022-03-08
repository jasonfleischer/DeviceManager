package com.example.devicemanager.presentation.device_details

data class DeviceDetailsState(
    var title: String? = null,
    var subTitle: String? = null,
    var isFavorite: Boolean = false,
    var price: String? = null,
    var description: String? = null,
    var imageUrlString: String? = null
)