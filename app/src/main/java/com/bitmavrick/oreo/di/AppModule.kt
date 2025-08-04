package com.bitmavrick.oreo.di

import com.bitmavrick.oreo.data.impl.PersonRepositoryImpl
import com.bitmavrick.oreo.data.repository.PersonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun providePersonRepository(): PersonRepository {
        return PersonRepositoryImpl()
    }
}