package com.example.camerasdoors.app.di.module

import com.example.camerasdoors.data.realm.repository.HouseDbRepositoryImpl
import com.example.camerasdoors.data.remote.repository.HouseRepositoryImpl
import com.example.camerasdoors.domain.repository.HouseDbRepository
import com.example.camerasdoors.domain.repository.HouseRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindHouseDbRepository(
        houseDbRepositoryImpl: HouseDbRepositoryImpl
    ): HouseDbRepository

    @Binds
    @Singleton
    abstract fun bindHouseRepository(
        houseRepositoryImpl: HouseRepositoryImpl
    ): HouseRepository
}