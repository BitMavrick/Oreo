package com.bitmavrick.oreo.ui.main

data class MainUiState(
    val isLoading: Boolean = false,
    val number: Int? = null,
    val errorMessage: String? = null
)