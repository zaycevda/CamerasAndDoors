package com.example.camerasdoors.app.di.module

import com.example.camerasdoors.data.realm.model.CameraObject
import com.example.camerasdoors.data.realm.model.DoorObject
import com.example.camerasdoors.data.realm.storage.RealmDataStorage
import dagger.hilt.components.SingletonComponent
import dagger.hilt.InstallIn
import dagger.Module
import dagger.Provides
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.HttpClient

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideHttpClient() =
        HttpClient(Android) {
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
        }

    @Provides
    fun provideRealmConfiguration() =
        RealmConfiguration.Builder(
            setOf(
                CameraObject::class,
                DoorObject::class
            )
        )
            .schemaVersion(schemaVersion = REALM_VERSION)
            .build()

    @Provides
    fun provideRealm(configuration: RealmConfiguration) =
        Realm.open(configuration)

    @Provides
    fun provideRealmDataStorage(realm: Realm) =
        RealmDataStorage(realm = realm)

    private const val REALM_VERSION = 1L
}