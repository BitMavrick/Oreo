package com.bitmavrick.oreo.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bitmavrick.oreo.data.repository.PersonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: PersonRepository
) : ViewModel() {
    var uiState  by mutableStateOf(MainUiState())
        private set


    fun onEvent(event: MainUiEvent){
        when(event){
            is MainUiEvent.Refresh -> fetchPerson()
            is MainUiEvent.SnackbarShown -> clearMessage()
        }
    }

    private fun fetchPerson(){
        viewModelScope.launch {
            uiState = uiState.copy(
                isLoading = true,
                errorMessage = null,
                uiMessage = null
            )

            val result = repository.fetchRandomPerson()

            uiState = if(result.isSuccess){
                val person = result.getOrNull()
                MainUiState(
                    person = person,
                    isLoading = false,
                    uiMessage = "Fetched: ${person?.name}, ${person?.age}, ${person?.gender}"
                )
            } else {
                MainUiState(
                    isLoading = false,
                    uiMessage = "Network error: ${result.exceptionOrNull()?.message}",
                    errorMessage = result.exceptionOrNull()?.message,
                )
            }
        }
    }

    private fun clearMessage(){
        uiState = uiState.copy(uiMessage = null)
    }

    init {
        fetchPerson()
    }
}

