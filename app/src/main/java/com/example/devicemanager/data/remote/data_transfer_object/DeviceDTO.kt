package com.example.devicemanager.data.remote.data_transfer_object

import com.google.gson.annotations.SerializedName

data class DeviceDTO(
    @SerializedName("Currency")
    val currency: String?,
    @SerializedName("Description")
    val description: String?,
    @SerializedName("Id")
    val id: String?,
    @SerializedName("Price")
    val price: Int?,
    @SerializedName("Title")
    val title: String?,
    @SerializedName("Type")
    val type: String?,
    val imageUrl: String?,
    val isFavorite: Boolean?
)