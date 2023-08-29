package com.example.camerasdoors.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DoorsResponse(
    val success: Boolean,
    @SerialName("data")
    val doorModels: List<DoorModel>
) {
    companion object {
        fun empty() =
            DoorsResponse(
                success = false,
                doorModels = emptyList()
            )
    }
}

@Serializable
data class DoorModel(
    val name: String,
    val snapshot: String? = null,
    val room: String?,
    val id: Long,
    val favorites: Boolean
)