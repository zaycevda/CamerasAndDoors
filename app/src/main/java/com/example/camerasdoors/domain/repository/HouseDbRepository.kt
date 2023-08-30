package com.example.camerasdoors.domain.repository

import com.example.camerasdoors.domain.model.Camera
import com.example.camerasdoors.domain.model.Door

interface HouseDbRepository {
    suspend fun addCameras(cameras: List<Camera>)
    suspend fun addDoors(doors: List<Door>)
    suspend fun deleteAll()
    suspend fun getCameras(): List<Camera>
    suspend fun getDoors(): List<Door>
    suspend fun updateDoor(door: Door)
}