package com.bitmavrick.oreo.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bitmavrick.oreo.data.repository.NumberRepository
import com.bitmavrick.oreo.data.repository.PersonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
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
        }
    }

    private fun fetchPerson(){
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)

            val result = repository.fetchRandomPerson()
            uiState = if(result.isSuccess){
                MainUiState(
                    person = result.getOrNull(),
                    isLoading = false
                )
            } else {
                MainUiState(
                    isLoading = false,
                    errorMessage = result.exceptionOrNull()?.message
                )
            }
        }
    }

    init {
        viewModelScope.launch {
            fetchPerson()
        }
    }
}


/*
private val refreshRequests = Channel<Unit>(Channel.CONFLATED)

    var uiState by mutableStateOf(MainUiState())
        private set

    init {
        viewModelScope.launch {
            for (refresh in refreshRequests) {
                refreshData()
            }
        }
    }

    val isRefreshing: Boolean
        get() = uiState.isLoading

    fun refresh() {
        refreshRequests.trySend(Unit)
    }

    private suspend fun refreshData() {
        uiState = uiState.copy(isLoading = true, errorMessage = null)
        val result = repository.fetchRandomNumber()
        uiState = when {
            result.isSuccess -> {
                MainUiState(number = result.getOrNull(), isLoading = false)
            }

            else -> {
                MainUiState(
                    isLoading = false,
                    errorMessage = result.exceptionOrNull()?.message ?: "Unknown error"
                )
            }
        }
    }
 */