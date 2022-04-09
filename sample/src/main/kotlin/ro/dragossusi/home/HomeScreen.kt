package ro.dragossusi.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ro.dragossusi.lifecycle.rememberViewModel

@Composable
fun HomeScreen(
    onItem: () -> Unit,
    onHelp: () -> Unit,
) {
    val viewModel = rememberViewModel {
        HomeViewModel()
    }
    val uiState by viewModel.uiState.collectAsState()
    if (uiState.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(modifier = Modifier.size(32.dp))
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = onItem) {
                Text("Item")
            }
            Button(onClick = onHelp) {
                Text("Help")
            }
        }
    }
}