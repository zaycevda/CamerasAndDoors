package com.example.camerasdoors.data.remote.repository

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
import javax.inject.Inject

class HouseRepositoryImpl @Inject constructor(
    private val client: HttpClient
) : HouseRepository {
    override suspend fun getCameras(): List<Camera> {
        val camerasResponse =
            try {
                client.get { url(urlString = HttpRoutes.CAMERAS) }
            } catch (e: Exception) {
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
                DoorsResponse.empty()
            }

        val doorModels = doorsResponse.doorModels
        return doorModels.map { doorModel -> doorModel.toDoor() }
    }
}