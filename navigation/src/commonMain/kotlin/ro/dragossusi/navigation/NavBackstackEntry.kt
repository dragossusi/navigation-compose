package ro.dragossusi.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

class NavBackstackEntry(
    val destination: NavDestination,
    val arguments: NavArguments
) : ViewModelStore() {

    @Composable
    fun compose() {
        CompositionLocalProvider(LocalViewModelStore provides this) {
            destination.block(arguments)
        }
    }

}