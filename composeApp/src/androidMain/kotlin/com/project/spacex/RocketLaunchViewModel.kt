package com.project.spacex

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.spacex.entity.RocketLaunch
import kotlinx.coroutines.launch

class RocketLaunchViewModel(private val sdk: SpaceXSDK) : ViewModel() {
    private val _state = mutableStateOf(RocketLaunchScreenState())
    val state: State<RocketLaunchScreenState> = _state

    init {
        loadLaunches()
    }

    fun loadLaunches() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, launches = emptyList())
            try {
                val launches = sdk.getLaunches(forceReload = true)
                _state.value = _state.value.copy(isLoading = false, launches = launches)
            } catch (e: Exception) {
                _state.value = _state.value.copy(isLoading = false, launches = emptyList())
            }
        }
    }
    fun getLaunchById(flightNumber: Int) {
        viewModelScope.launch {
            try {
                val launch = sdk.getLaunchById(flightNumber)
                _state.value = _state.value.copy(
                    selectedLaunch = launch
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    selectedLaunch = null
                )
            }
        }
    }
}

data class RocketLaunchScreenState(
    val isLoading: Boolean = false,
    val launches: List<RocketLaunch> = emptyList(),
    val selectedLaunch: RocketLaunch? = null
)