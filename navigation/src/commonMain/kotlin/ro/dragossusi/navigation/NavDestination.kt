package ro.dragossusi.navigation

import androidx.compose.runtime.Composable

class NavDestination(
    val route: String,
    val block: @Composable (NavArguments) -> Unit
) {

}