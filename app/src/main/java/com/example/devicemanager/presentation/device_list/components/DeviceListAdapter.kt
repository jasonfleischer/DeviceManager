package com.example.devicemanager.presentation.device_list.components

import com.example.devicemanager.R
import com.example.devicemanager.common.recycler_view_adapter.BaseAdapter
import com.example.devicemanager.databinding.DeviceListItemBinding
import com.example.devicemanager.domain.model.Device

class DeviceListAdapter(
    list: List<Device>,
    private val deviceItemListener: DeviceItemListener
) : BaseAdapter<DeviceListItemBinding, Device>(list) {

    override val layoutId: Int = R.layout.device_list_item

    override fun bind(binding: DeviceListItemBinding, item: Device) {
        binding.apply {
            viewModel = DeviceListItemViewModel(
                item.identifier,
                item.title.trim(),
                item.type.trim(),
                item.imageUrl,
                deviceItemListener
            )
            executePendingBindings()
        }
    }
}

interface DeviceItemListener {
    fun onItemClicked(deviceId: String)
}