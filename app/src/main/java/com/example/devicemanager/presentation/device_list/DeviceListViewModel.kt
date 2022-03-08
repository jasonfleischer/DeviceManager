package com.example.devicemanager.presentation.device_list

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.devicemanager.common.Resource
import com.example.devicemanager.domain.model.Device
import com.example.devicemanager.domain.use_case.GetDevicesUseCase
import com.example.devicemanager.domain.use_case.SearchDevicesUseCase
import com.example.devicemanager.domain.use_case.SortDevicesUseCase
import com.example.devicemanager.presentation.device_list.components.DeviceItemListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeviceListViewModel @Inject constructor(
    private val getDevicesUseCase: GetDevicesUseCase,
    private val sortDevicesUseCase: SortDevicesUseCase,
    private val searchDevicesUseCase: SearchDevicesUseCase,
) : ViewModel(), DeviceItemListener, DefaultLifecycleObserver {

    private val _state = MutableStateFlow(DeviceListState(value = DeviceListStateEnum.LOADING))
    val state: StateFlow<DeviceListState> = _state

    val mutableSearchText = MutableStateFlow("")
    private val searchText: StateFlow<String> = mutableSearchText

    private var allDevices: List<Device> = emptyList()

    private var sortType = SortDevicesUseCase.SortType.ASCENDING_BY_TITLE

    interface NavigationProvider {
        fun showDeviceDetailsFragment(deviceId: String)
        fun showMenu()
    }

    var navigationProvider: NavigationProvider? = null

    init {
        getDevices()
        viewModelScope.launch {
            onDisplayedSearchTextChange()
        }
    }

    private suspend fun onDisplayedSearchTextChange() {
        searchText.collectLatest { searchString: String ->
            delay(500)
            print(searchString)
            onTextChanged(searchString)
        }
    }

    fun onMenu() {
        navigationProvider?.showMenu()
    }

    private fun onTextChanged(s: CharSequence) {

        searchDevicesUseCase(allDevices, s.toString()).onEach { result ->
            _state.value = when (result) {
                is Resource.Success -> {
                    val devices = sortDevicesUseCase(result.data ?: emptyList(), sortType)
                    DeviceListState(
                        value = DeviceListStateEnum.LOADED,
                        devices = devices,
                        error = state.value.error
                    )
                }
                is Resource.Error -> {
                    DeviceListState(
                        value = DeviceListStateEnum.ERROR,
                        error = result.message ?: "an unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    DeviceListState(
                        value = DeviceListStateEnum.SEARCHING,
                        error = state.value.error
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getDevices() {
        getDevicesUseCase().onEach { result ->
            _state.value = when (result) {
                is Resource.Success -> {
                    val devices = sortDevicesUseCase(result.data ?: emptyList(), sortType)
                    allDevices = devices
                    DeviceListState(
                        value = DeviceListStateEnum.LOADED, devices = devices,
                        error = state.value.error
                    )
                }
                is Resource.Error -> {
                    DeviceListState(
                        value = DeviceListStateEnum.ERROR,
                        error = result.message ?: "an unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    DeviceListState(
                        value = DeviceListStateEnum.LOADING,
                        error = state.value.error
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun retryConnection() {
        _state.value = DeviceListState(
            value = DeviceListStateEnum.LOADING,
        )
        getDevices()
    }

    fun sortDevices(sortType: SortDevicesUseCase.SortType) {
        this.sortType = sortType
        val devices = sortDevicesUseCase(state.value.devices, sortType)
        _state.value = DeviceListState(
            value = DeviceListStateEnum.LOADED,
            devices = devices,
            error = state.value.error
        )
    }

    override fun onItemClicked(deviceId: String) {
        navigationProvider?.showDeviceDetailsFragment(deviceId)
    }
}