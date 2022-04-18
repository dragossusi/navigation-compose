package ro.dragossusi.item

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.kodein.di.compose.localDI
import ro.dragossusi.lifecycle.rememberDiViewModel
import ro.dragossusi.lifecycle.rememberViewModel

@Composable
fun ItemScreen(onClick: () -> Unit) {
    val viewModel: ItemViewModel = localDI().rememberDiViewModel()
    val uiState by viewModel.uiState.collectAsState()
    if (uiState.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(modifier = Modifier.size(32.dp))
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(uiState.items) {
                Text(it)
            }
            item {
                Button(onClick = onClick) {
                    Text("Help")
                }
            }
        }
    }
}