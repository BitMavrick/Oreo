package com.bitmavrick.oreo.ui.main.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.bitmavrick.oreo.ui.theme.OreoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(
    enabled: Boolean,
    onRefreshClick: () -> Unit
){
    TopAppBar(
        title = {
            Text(text = "Oreo")
        },
        actions = {
            IconButton(
                enabled = enabled,
                onClick = { onRefreshClick() }
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Manual Refresh"
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun MainTopBarPreview(){
    OreoTheme {
        MainTopBar(
            enabled = true,
            onRefreshClick = {}
        )
    }
}