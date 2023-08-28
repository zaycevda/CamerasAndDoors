package com.example.camerasdoors.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CamerasResponse(
    val success: Boolean,
    val data: Data
) {
    companion object {
        fun empty() =
            CamerasResponse(
                success = false,
                data = Data.empty()
            )
    }
}

@Serializable
data class Data(
    val room: List<String>,
    @SerialName("cameras")
    val camerasModels: List<CameraModel>
) {
    companion object {
        fun empty() =
            Data(
                room = emptyList(),
                camerasModels = emptyList()
            )
    }
}

@Serializable
data class CameraModel(
    val name: String,
    val snapshot: String,
    val room: String?,
    val id: Long,
    val favorites: Boolean,
    val rec: Boolean
)