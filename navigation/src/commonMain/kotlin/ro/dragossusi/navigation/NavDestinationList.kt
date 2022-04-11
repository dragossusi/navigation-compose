package ro.dragossusi.navigation

import androidx.compose.runtime.Composable

class NavDestinationList(
    override val route: String,
    val startRoute: String,
    val destinations: Map<String, Destination>
) : Destination {
    override val block: @Composable (NavArguments) -> Unit
        get() = destinations[startRoute]!!.block

    operator fun get(route: String) = destinations[route]

    internal fun requireDestination(route: String): Destination {
        return this[route] ?: throw IllegalArgumentException("$route not declared in the builder")
    }

    fun createStartEntry(
        parent: BackstackEntry
    ): BackstackEntry {
        return when (val startDestination = requireDestination(startRoute)) {
            is NavDestinationEntry -> NavBackstackEntry(startDestination, parent, emptyArguments())
            is NavDestinationList -> NavBackstackList(startDestination, parent)
        }
    }
}