package com.example.camerasdoors.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.camerasdoors.domain.model.Camera
import com.example.camerasdoors.domain.model.Door
import com.example.camerasdoors.domain.repository.HouseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HouseViewModel @Inject constructor(
    private val repository: HouseRepository
) : ViewModel() {

    private val _cameras = MutableLiveData<List<Camera>>()
    val cameras: LiveData<List<Camera>> get() = _cameras

    private val _doors = MutableLiveData<List<Door>>()
    val doors: LiveData<List<Door>> get() = _doors

    private val _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing: LiveData<Boolean> get() = _isRefreshing

    fun getCameras() {
        viewModelScope.launch {
            val cameras = repository.getCameras()
            _cameras.value = cameras
        }
        _isRefreshing.value = false
    }

    fun getDoors() {
        viewModelScope.launch {
            val doors = repository.getDoors()
            _doors.value = doors
        }
        _isRefreshing.value = false
    }
}