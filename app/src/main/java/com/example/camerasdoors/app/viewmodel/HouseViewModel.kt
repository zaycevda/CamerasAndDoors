package com.example.camerasdoors.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.camerasdoors.domain.model.Camera
import com.example.camerasdoors.domain.model.Door
import com.example.camerasdoors.domain.repository.HouseDbRepository
import com.example.camerasdoors.domain.repository.HouseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HouseViewModel @Inject constructor(
    private val houseRepository: HouseRepository,
    private val houseDbRepository: HouseDbRepository
) : ViewModel() {
    private val _cameras = MutableLiveData<List<Camera>>()
    val cameras: LiveData<List<Camera>> get() = _cameras

    private val _doors = MutableLiveData<List<Door>>()
    val doors: LiveData<List<Door>> get() = _doors

    private val _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing: LiveData<Boolean> get() = _isRefreshing

    fun getCameras() {
        viewModelScope.launch {
            val cameras = houseDbRepository.getCameras().ifEmpty {
                val cameras = houseRepository.getCameras()
                houseDbRepository.addCameras(cameras = cameras)
                cameras
            }
            _cameras.value = cameras
        }
    }

    fun getDoors() {
        viewModelScope.launch {
            val doors = houseDbRepository.getDoors().ifEmpty {
                val doors = houseRepository.getDoors()
                houseDbRepository.addDoors(doors = doors)
                doors
            }
            _doors.value = doors
        }
    }

    fun refresh() {
        viewModelScope.launch {
            val cameras = houseRepository.getCameras()
            val doors = houseRepository.getDoors()

            if (doors.isNotEmpty()) {
                houseDbRepository.deleteAll()

                houseDbRepository.addCameras(cameras = cameras)
                houseDbRepository.addDoors(doors = doors)

                _cameras.value = cameras
                _doors.value = doors
            }
        }
        _isRefreshing.value = false
    }

    fun updateDoor(door: Door) {
        viewModelScope.launch {
            houseDbRepository.updateDoor(door = door)
            val doors = houseDbRepository.getDoors()
            _doors.value = doors
        }
    }
}