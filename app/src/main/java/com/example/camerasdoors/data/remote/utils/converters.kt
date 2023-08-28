package com.example.camerasdoors.data.remote.utils

import com.example.camerasdoors.data.remote.dto.CameraModel
import com.example.camerasdoors.data.remote.dto.DoorModel
import com.example.camerasdoors.domain.model.Camera
import com.example.camerasdoors.domain.model.Door

fun CameraModel.toCamera() =
    Camera(
        name = name,
        snapshot = snapshot,
        room = room,
        id = id,
        favorites = favorites,
        rec = rec
    )

fun DoorModel.toDoor() =
    Door(
        name = name,
        snapshot = snapshot,
        room = room,
        id = id,
        favorites = favorites
    )