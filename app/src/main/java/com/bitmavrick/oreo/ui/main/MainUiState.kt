package com.bitmavrick.oreo.ui.main

import com.bitmavrick.oreo.domain.model.Person

data class MainUiState(
    val isLoading: Boolean = false,
    val person: Person? = null,
    val errorMessage: String? = null
)