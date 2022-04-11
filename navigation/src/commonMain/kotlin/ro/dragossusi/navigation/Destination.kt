package ro.dragossusi.navigation

import androidx.compose.runtime.Composable

sealed interface Destination {
    val route: String
    val block: @Composable (NavArguments) -> Unit
}