package com.example.camerasdoors.domain.model

data class Camera(
    val name: String,
    val snapshot: String,
    val room: String?,
    val id: Long,
    val favorites: Boolean,
    val rec: Boolean
)