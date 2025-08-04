package com.bitmavrick.oreo.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.bitmavrick.oreo.ui.theme.OreoTheme
@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState

    MainScreenCompose(
        uiState = uiState,
        onEvent = viewModel::onEvent
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenCompose(
    uiState: MainUiState,
    onEvent: (MainUiEvent) -> Unit
){
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.uiMessage) {
        uiState.uiMessage?.let { message ->
            snackbarHostState.showSnackbar(message)
            onEvent(MainUiEvent.ClearUiMessages)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Oreo") },
                actions = {
                    IconButton(
                        enabled = !uiState.isLoading,
                        onClick = { onEvent(MainUiEvent.Refresh) }
                    ) {
                        Icon(Icons.Default.Refresh, contentDescription = "Manual Refresh")
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        PullToRefreshBox(
            modifier = Modifier.padding(padding),
            isRefreshing = uiState.isLoading,
            onRefresh = { onEvent(MainUiEvent.Refresh) }
        ) {
            LazyColumn(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    if (uiState.person != null) {
                        Text(
                            text = "Name: ${uiState.person.name}",
                            style = MaterialTheme.typography.headlineSmall
                        )
                    } else {
                        Text(
                            text = "Pull down to fetch a person",
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview(){
    OreoTheme {
        MainScreenCompose(
            uiState = MainUiState(),
            onEvent = {}
        )
    }
}