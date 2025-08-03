package com.bitmavrick.oreo.di

import com.bitmavrick.oreo.data.repository.NumberRepository
import com.bitmavrick.oreo.data.repository.NumberRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideNumberRepository(): NumberRepository {
        return NumberRepositoryImpl()
    }
}