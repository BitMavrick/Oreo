package com.bitmavrick.oreo.data.repository

import kotlinx.coroutines.delay
import javax.inject.Inject

interface NumberRepository {
    suspend fun fetchRandomNumber(): Result<Int?>
}

class NumberRepositoryImpl @Inject constructor() : NumberRepository {
    override suspend fun fetchRandomNumber(): Result<Int?> {
        delay((2000..3000).random().toLong())
        return if ((1..2).random() == 1) { // 25% chance of error
            Result.failure(Exception("Network error"))
        } else {
            Result.success((1..100).random())
        }
    }
}