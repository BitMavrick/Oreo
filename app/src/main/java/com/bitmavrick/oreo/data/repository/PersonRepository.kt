package com.bitmavrick.oreo.data.repository

import com.bitmavrick.oreo.domain.model.Person

interface PersonRepository {
    suspend fun fetchRandomPerson(): Result<Person?>
}