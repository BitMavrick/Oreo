package com.bitmavrick.oreo.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bitmavrick.oreo.data.repository.NumberRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: NumberRepository
) : ViewModel() {

    var uiState by mutableStateOf(MainUiState())
        private set

    fun onEvent(event: MainUiEvent) {
        when (event) {
            is MainUiEvent.Refresh -> {
                refreshNumber()
            }
        }
    }

    private fun refreshNumber() {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, errorMessage = null)
            val result = repository.fetchRandomNumber()
            uiState = when {
                result.isSuccess -> {
                    val num = result.getOrNull()
                    MainUiState(number = num, isLoading = false)
                }

                else -> {
                    MainUiState(
                        isLoading = false,
                        errorMessage = result.exceptionOrNull()?.message ?: "Unknown error"
                    )
                }
            }
        }
    }
}