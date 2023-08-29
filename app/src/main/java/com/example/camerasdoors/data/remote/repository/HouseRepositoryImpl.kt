package com.example.camerasdoors.data.remote.repository

import android.util.Log
import com.example.camerasdoors.data.remote.dto.CamerasResponse
import com.example.camerasdoors.data.remote.dto.DoorsResponse
import com.example.camerasdoors.data.remote.util.HttpRoutes
import com.example.camerasdoors.data.remote.util.toCamera
import com.example.camerasdoors.data.remote.util.toDoor
import com.example.camerasdoors.domain.model.Camera
import com.example.camerasdoors.domain.model.Door
import com.example.camerasdoors.domain.repository.HouseRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url

class HouseRepositoryImpl(
    private val client: HttpClient
) : HouseRepository {
    override suspend fun getCameras(): List<Camera> {
        val camerasResponse =
            try {
                client.get { url(urlString = HttpRoutes.CAMERAS) }
            } catch (e: Exception) {
                Log.e(LOG, "getCameras error: ${e.message}")
                CamerasResponse.empty()
            }

        val cameraModels = camerasResponse.data.camerasModels
        return cameraModels.map { cameraModel -> cameraModel.toCamera() }
    }

    override suspend fun getDoors(): List<Door> {
        val doorsResponse =
            try {
                client.get { url(urlString = HttpRoutes.DOORS) }
            } catch (e: Exception) {
                Log.e(LOG, "getDoors error: ${e.message}")
                DoorsResponse.empty()
            }

        val doorModels = doorsResponse.doorModels
        return doorModels.map { doorModel -> doorModel.toDoor() }
    }

    private companion object {
        private const val LOG = "HouseRepositoryImplLog"
    }
}