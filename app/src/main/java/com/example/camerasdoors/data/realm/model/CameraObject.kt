package com.example.camerasdoors.data.realm.model

import io.realm.kotlin.types.RealmObject

class CameraObject() : RealmObject {
    var name: String = ""

    var snapshot: String = ""

    var room: String? = null

    var id: Long = 0

    var favorites: Boolean = false

    var rec: Boolean = false

    constructor(
        name: String,
        snapshot: String,
        room: String?,
        id: Long,
        favorites: Boolean,
        rec: Boolean
    ) : this() {
        this.name = name
        this.snapshot = snapshot
        this.room = room
        this.id = id
        this.favorites = favorites
        this.rec = rec
    }
}