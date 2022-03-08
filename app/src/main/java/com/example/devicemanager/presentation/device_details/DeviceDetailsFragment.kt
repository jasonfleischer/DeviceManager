package com.example.devicemanager.presentation.device_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.devicemanager.databinding.FragmentDeviceDetailsBinding

class DeviceDetailsFragment : Fragment(), DeviceDetailsViewModel.NavigationProvider {

    private var _binding: FragmentDeviceDetailsBinding? = null
    private val viewModel: DeviceDetailsViewModel by viewModels()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.navigationProvider = this
        _binding = FragmentDeviceDetailsBinding.inflate(inflater, container, false).apply {
            viewModel = this@DeviceDetailsFragment.viewModel
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun returnToPreviousFragment() {
        requireActivity().onBackPressed()
    }
}