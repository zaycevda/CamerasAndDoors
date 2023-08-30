package com.example.camerasdoors.data.realm.util

import com.example.camerasdoors.data.realm.model.CameraObject
import com.example.camerasdoors.data.realm.model.DoorObject
import com.example.camerasdoors.domain.model.Camera
import com.example.camerasdoors.domain.model.Door

fun Camera.toCameraObject() =
    CameraObject(
        name = name,
        snapshot = snapshot,
        room = room,
        id = id,
        favorites = favorites,
        rec = rec
    )

fun CameraObject.toCamera() =
    Camera(
        name = name,
        snapshot = snapshot,
        room = room,
        id = id,
        favorites = favorites,
        rec = rec
    )

fun Door.toDoorObject() =
    DoorObject(
        name = name,
        snapshot = snapshot,
        room = room,
        id = id,
        favorites = favorites
    )

fun DoorObject.toDoor() =
    Door(
        name = name,
        snapshot = snapshot,
        room = room,
        id = id,
        favorites = favorites
    )