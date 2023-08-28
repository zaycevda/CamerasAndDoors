package com.example.camerasdoors.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.camerasdoors.app.ui.utils.ScreenState
import com.example.camerasdoors.app.ui.utils.ScreenState.ErrorScreenState
import com.example.camerasdoors.app.ui.utils.ScreenState.LoadingScreenState
import com.example.camerasdoors.app.ui.utils.ScreenState.SuccessScreenState
import com.example.camerasdoors.domain.model.Camera
import com.example.camerasdoors.domain.model.Door
import com.example.camerasdoors.domain.repository.HouseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HouseViewModel @Inject constructor(
    private val repository: HouseRepository
) : ViewModel() {

    private val _cameras = MutableLiveData<ScreenState<List<Camera>>>()
    val cameras: LiveData<ScreenState<List<Camera>>> = _cameras

    private val _doors = MutableLiveData<ScreenState<List<Door>>>()
    val doors: LiveData<ScreenState<List<Door>>> = _doors

    fun getCameras() {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            _cameras.value = ErrorScreenState(throwable = throwable)
        }
        viewModelScope.launch(exceptionHandler) {
            _cameras.value = LoadingScreenState()
            val cameras = repository.getCameras()
            _cameras.value = SuccessScreenState(data = cameras)
        }
    }

    fun getDoors() {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            _doors.value = ErrorScreenState(throwable = throwable)
        }
        viewModelScope.launch(exceptionHandler) {
            _doors.value = LoadingScreenState()
            val doors = repository.getDoors()
            _doors.value = SuccessScreenState(data = doors)
        }
    }
}