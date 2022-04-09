package ro.dragossusi.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

class NavBackstackEntry(
    private val destination: NavDestination,
    val arguments: NavArguments
) : ViewModelStore() {

    val route: String = destination.route

    @Composable
    internal fun compose() {
        CompositionLocalProvider(LocalViewModelStore provides this) {
            destination.block(arguments)
        }
    }

}