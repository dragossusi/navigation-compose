package ro.dragossusi.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

class NavBackstackEntry constructor(
    override val destination: NavDestinationEntry,
    override val parent: BackstackEntry,
    val arguments: NavArguments,
) : ViewModelStore(), BackstackEntry {

    override val current: NavBackstackEntry get() = this

    override fun navigateUp(): NavBackstackEntry? {
        return null
    }


    @Composable
    internal fun compose() {
        CompositionLocalProvider(
            LocalViewModelStore provides this,
            LocalNavArguments provides arguments
        ) {
            destination.block(arguments)
        }
    }

    override fun contains(route: String): Boolean {
        return route == route
    }

    override fun navigateUpTo(route: String): BackstackEntry? {
        return if (!contains(route)) null
        else this
    }

    override fun toString(): String {
        return "NavBackstackEntry(route=${route},arguments=$arguments)"
    }

}