package com.bitmavrick.oreo.domain.model

data class Person(
    val name: String,
    val age: Int,
    val gender: Gender
)

enum class Gender {
    MALE, FEMALE, UNKNOWN
}