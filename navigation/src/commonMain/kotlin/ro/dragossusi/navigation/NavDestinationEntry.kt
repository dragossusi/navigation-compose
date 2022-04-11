package ro.dragossusi.navigation

import androidx.compose.runtime.Composable

class NavDestinationEntry(
    override val route: String,
    override val block: @Composable (NavArguments) -> Unit
) : Destination {

}