package com.example.camerasdoors.data.realm.storage

import io.realm.kotlin.MutableRealm
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.types.RealmObject
import javax.inject.Inject
import kotlin.reflect.KClass

class RealmDataStorage @Inject constructor(private val realm: Realm) {
    suspend fun deleteAll() {
        realm.write { deleteAll() }
    }

    fun <T : RealmObject> getAll(model: KClass<T>): List<T> =
        realm.query(clazz = model).find()

    suspend fun <T : RealmObject> saveAll(objs: List<T>) {
        realm.write {
            objs.forEach { obj ->
                copyToRealm(
                    instance = obj,
                    updatePolicy = UpdatePolicy.ALL
                )
            }
        }
    }

    suspend fun <T : RealmObject> update(
        model: KClass<T>,
        id: Long,
        block: MutableRealm.(T?) -> Unit
    ) {
        realm.write {
            val obj = query(
                clazz = model,
                query = "$ID_PREDICATE$id"
            ).first().find()
            block(obj)
        }
    }

    private companion object {
        private const val ID_PREDICATE = "id == "
    }
}