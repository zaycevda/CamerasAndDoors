package com.example.camerasdoors.data.realm.repository

import com.example.camerasdoors.data.realm.model.CameraObject
import com.example.camerasdoors.data.realm.model.DoorObject
import com.example.camerasdoors.data.realm.storage.RealmDataStorage
import com.example.camerasdoors.data.realm.util.toCamera
import com.example.camerasdoors.data.realm.util.toCameraObject
import com.example.camerasdoors.data.realm.util.toDoor
import com.example.camerasdoors.data.realm.util.toDoorObject
import com.example.camerasdoors.domain.model.Camera
import com.example.camerasdoors.domain.model.Door
import com.example.camerasdoors.domain.repository.HouseDbRepository
import javax.inject.Inject

class HouseDbRepositoryImpl @Inject constructor(
    private val rds: RealmDataStorage
) : HouseDbRepository {
    override suspend fun addCameras(cameras: List<Camera>) {
        val cameraObjects = cameras.map { camera -> camera.toCameraObject() }
        rds.saveAll(objs = cameraObjects)
    }

    override suspend fun addDoors(doors: List<Door>) {
        val doorObjects = doors.map { door -> door.toDoorObject() }
        rds.saveAll(objs = doorObjects)
    }

    override suspend fun deleteAll() {
        rds.deleteAll()
    }

    override suspend fun getCameras(): List<Camera> {
        val cameraObjects = rds.getAll(model = CameraObject::class)
        return cameraObjects.map { cameraObject -> cameraObject.toCamera() }
    }

    override suspend fun getDoors(): List<Door> {
        val doorObjects = rds.getAll(model = DoorObject::class)
        return doorObjects.map { doorObject -> doorObject.toDoor() }
    }

    override suspend fun updateDoor(door: Door) {
        rds.update(model = DoorObject::class, id = door.id) { doorObject ->
            doorObject?.name = door.name
        }
    }
}