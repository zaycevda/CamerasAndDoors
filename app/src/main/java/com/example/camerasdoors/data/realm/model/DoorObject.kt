package com.example.camerasdoors.data.realm.model

import io.realm.kotlin.types.RealmObject

class DoorObject() : RealmObject {
    var name: String = ""

    var snapshot: String? = null

    var room: String? = null

    var id: Long = 0

    var favorites: Boolean = false

    constructor(
        name: String,
        snapshot: String?,
        room: String?,
        id: Long,
        favorites: Boolean
    ) : this() {
        this.name = name
        this.snapshot = snapshot
        this.room = room
        this.id = id
        this.favorites = favorites
    }
}