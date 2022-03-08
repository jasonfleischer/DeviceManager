package com.example.devicemanager.domain.model
import com.example.devicemanager.common.recycler_view_adapter.ListAdapterItem
import java.io.Serializable

data class Device(

    val identifier: String,
    val title: String,
    val type: String,
    val description: String,
    val price: Int,
    val currency: String,
    val isFavorite: Boolean,
    val imageUrl: String,
    
    override val id: Long = 0
): Serializable, ListAdapterItem
