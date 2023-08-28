package com.example.camerasdoors.data.remote.repository

import android.util.Log
import com.example.camerasdoors.data.remote.dto.CamerasResponse
import com.example.camerasdoors.data.remote.dto.DoorsResponse
import com.example.camerasdoors.data.remote.utils.HttpRoutes
import com.example.camerasdoors.data.remote.utils.toCamera
import com.example.camerasdoors.data.remote.utils.toDoor
import com.example.camerasdoors.domain.model.Camera
import com.example.camerasdoors.domain.model.Door
import com.example.camerasdoors.domain.repository.HouseRepository
import io.ktor.client.HttpClient
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.request.get
import io.ktor.client.request.url

class HouseRepositoryImpl(
    private val client: HttpClient
) : HouseRepository {

    override suspend fun getCameras(): List<Camera> {
        val camerasResponse =
            try {
                client.get { url(HttpRoutes.CAMERAS) }
            } catch (e: RedirectResponseException) {
                // 3xx
                Log.e(LOG, "getCameras Error ${e.response.status}: ${e.response.status.description}")
                CamerasResponse.empty()
            } catch (e: RedirectResponseException) {
                // 4xx
                Log.e(LOG, "getCameras Error ${e.response.status}: ${e.response.status.description}")
                CamerasResponse.empty()
            } catch (e: RedirectResponseException) {
                // 5xx
                Log.e(LOG, "getCameras Error ${e.response.status}: ${e.response.status.description}")
                CamerasResponse.empty()
            } catch (e: Exception) {
                Log.e(LOG, "getCameras Error: ${e.message}")
                CamerasResponse.empty()
            }

        val cameraModels = camerasResponse.data.camerasModels

        return cameraModels.map { cameraModel -> cameraModel.toCamera() }
    }

    override suspend fun getDoors(): List<Door> {
        val doorsResponse =
            try {
                client.get { url(HttpRoutes.DOORS) }
            } catch (e: RedirectResponseException) {
                // 3xx
                Log.e(LOG, "getDoors Error ${e.response.status}: ${e.response.status.description}")
                DoorsResponse.empty()
            } catch (e: RedirectResponseException) {
                // 4xx
                Log.e(LOG, "getDoors Error ${e.response.status}: ${e.response.status.description}")
                DoorsResponse.empty()
            } catch (e: RedirectResponseException) {
                // 5xx
                Log.e(LOG, "getDoors Error ${e.response.status}: ${e.response.status.description}")
                DoorsResponse.empty()
            } catch (e: Exception) {
                Log.e(LOG, "getDoors Error: ${e.message}")
                DoorsResponse.empty()
            }

        val doorModels = doorsResponse.doorModels

        return doorModels.map { doorModel -> doorModel.toDoor() }
    }

    private companion object {
        private const val LOG = "HouseRepositoryImplLog"
    }
}