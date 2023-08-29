package com.example.camerasdoors.app.di.module

import com.example.camerasdoors.data.remote.repository.HouseRepositoryImpl
import com.example.camerasdoors.domain.repository.HouseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideHouseRepository(): HouseRepository =
        HouseRepositoryImpl(
            client = HttpClient(Android) {
                install(JsonFeature) {
                    serializer = KotlinxSerializer()
                }
            }
        )
}