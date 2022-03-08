package com.example.devicemanager.presentation.device_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.devicemanager.R
import com.example.devicemanager.common.Constants
import com.example.devicemanager.databinding.FragmentDeviceListBinding
import com.example.devicemanager.domain.use_case.SortDevicesUseCase
import com.example.devicemanager.presentation.device_list.components.DeviceListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeviceListFragment : Fragment(), DeviceListViewModel.NavigationProvider {

    private val viewModel: DeviceListViewModel by viewModels()
    private var _binding: FragmentDeviceListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.navigationProvider = this
        _binding = FragmentDeviceListBinding.inflate(inflater, container, false).apply {
            viewModel = this@DeviceListFragment.viewModel
            lifecycleOwner = this@DeviceListFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView(view.findViewById(R.id.recycler_view))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapterDevice: DeviceListAdapter? = null
    private fun setupRecyclerView(recyclerView: RecyclerView) {
        layoutManager = LinearLayoutManager(this.requireContext())
        recyclerView.layoutManager = layoutManager
        adapterDevice = DeviceListAdapter(listOf(), viewModel)
        recyclerView.adapter = adapterDevice
    }

    override fun showDeviceDetailsFragment(deviceId: String) {
        viewModel.state.value.devices.firstOrNull { device -> device.identifier == deviceId }
            ?.let { device ->
                val bundle = bundleOf(Constants.SavedState.CHOSEN_DEVICE to device)
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
            }
    }

    override fun showMenu() {
        val v: View = binding.menuButton
        val popup = PopupMenu(v.context, v)
        popup.menuInflater.inflate(R.menu.popup, popup.menu)
        popup.setOnMenuItemClickListener { item ->
            viewModel.sortDevices(getSortTypeFromId(item.itemId))
            return@setOnMenuItemClickListener true
        }
        popup.show()
    }

    private fun getSortTypeFromId(title: Int): SortDevicesUseCase.SortType {
        return when (title) {
            R.id.option_ascending_by_title -> SortDevicesUseCase.SortType.ASCENDING_BY_TITLE
            R.id.option_descending_by_title -> SortDevicesUseCase.SortType.DESCENDING_BY_TITLE
            R.id.option_ascending_by_type -> SortDevicesUseCase.SortType.ASCENDING_BY_TYPE
            R.id.option_descending_by_type -> SortDevicesUseCase.SortType.DESCENDING_BY_TYPE
            else -> SortDevicesUseCase.SortType.ASCENDING_BY_TITLE
        }
    }
}