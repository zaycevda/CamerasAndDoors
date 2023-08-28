package com.example.camerasdoors.domain.model

data class Door(
    val name: String,
    val snapshot: String?,
    val room: String?,
    val id: Long,
    val favorites: Boolean
)