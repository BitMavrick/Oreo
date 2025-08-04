package com.bitmavrick.oreo.ui.main

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.bitmavrick.oreo.ui.main.components.MainContent
import com.bitmavrick.oreo.ui.main.components.MainTopBar
import com.bitmavrick.oreo.ui.theme.OreoTheme

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState

    MainScreenContent(
        uiState = uiState,
        onEvent = viewModel::onEvent
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenContent(
    uiState: MainUiState,
    onEvent: (MainUiEvent) -> Unit
){
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.uiMessage) {
        uiState.uiMessage?.let { message ->
            snackbarHostState.showSnackbar(message)
            onEvent(MainUiEvent.SnackbarShown)
        }
    }

    Scaffold(
        topBar = {
            MainTopBar(
                enabled = !uiState.isLoading,
                onRefreshClick = { onEvent(MainUiEvent.Refresh) }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        MainContent(
            paddingValues = padding,
            uiState = uiState,
            onRefresh = { onEvent(MainUiEvent.Refresh) }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview(){
    OreoTheme {
        MainScreenContent(
            uiState = MainUiState(),
            onEvent = {}
        )
    }
}