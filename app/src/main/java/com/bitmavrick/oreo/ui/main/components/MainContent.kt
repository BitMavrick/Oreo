package com.bitmavrick.oreo.ui.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bitmavrick.oreo.ui.main.MainUiState
import com.bitmavrick.oreo.ui.theme.OreoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(
    paddingValues: PaddingValues,
    uiState: MainUiState,
    onRefresh: () -> Unit
) {
    PullToRefreshBox(
        modifier = Modifier.padding(paddingValues),
        isRefreshing = uiState.isLoading,
        onRefresh = { onRefresh() }
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

@Preview(showBackground = true)
@Composable
private fun MainContentPreview(){
    OreoTheme {
        MainContent(
            paddingValues = PaddingValues(),
            uiState = MainUiState(),
            onRefresh = {}
        )
    }
}