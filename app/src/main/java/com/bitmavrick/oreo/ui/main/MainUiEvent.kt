package com.bitmavrick.oreo.ui.main

sealed class MainUiEvent {
    object Refresh : MainUiEvent()
    object SnackbarShown : MainUiEvent()
}