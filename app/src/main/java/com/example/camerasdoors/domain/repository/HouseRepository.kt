package com.example.camerasdoors.domain.repository

import com.example.camerasdoors.domain.model.Camera
import com.example.camerasdoors.domain.model.Door

interface HouseRepository {
    suspend fun getCameras(): List<Camera>
    suspend fun getDoors(): List<Door>
}