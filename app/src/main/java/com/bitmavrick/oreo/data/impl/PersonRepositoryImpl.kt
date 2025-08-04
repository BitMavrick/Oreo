package com.bitmavrick.oreo.data.impl

import com.bitmavrick.oreo.data.repository.PersonRepository
import com.bitmavrick.oreo.domain.model.Gender
import com.bitmavrick.oreo.domain.model.Person
import kotlinx.coroutines.delay
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor() : PersonRepository {
    private val names = listOf(
        "Alice", "Bob", "Charlie", "David", "Eva", "Frank", "Grace",
        "Hannah", "Ivan", "Jack", "Kira", "Leo", "Maya", "Nina", "Oscar",
        "Paul", "Queen", "Rita", "Sam", "Tina"
    )

    override suspend fun fetchRandomPerson(): Result<Person?> {

        delay((2000..3000).random().toLong())

        return if((1..4).random() == 1){
            Result.failure(Exception("Network error"))
        }else{
            val name = names.random()
            val age = (1 .. 80).random()
            val gender = Gender.values().random()

            Result.success(Person(name, age, gender))
        }
    }
}