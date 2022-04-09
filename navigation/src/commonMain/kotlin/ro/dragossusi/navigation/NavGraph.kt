package ro.dragossusi.navigation

import androidx.compose.runtime.*

class NavGraph internal constructor(
    private val start: String,
    private val startArguments: NavArguments,
    private val destinations: Map<String, NavDestination>
) {

    fun createStartEntry() = NavBackstackEntry(
        requireDestination(start),
        startArguments
    )

    internal fun requireDestination(route: String): NavDestination {
        return destinations[route] ?: throw IllegalArgumentException("$route not declared in the builder")
    }

}

@Composable
internal fun rememberNavGraph(
    startRoute: String,
    startArguments: NavArguments = emptyArguments(),
    block: NavDestinationsBuilder.() -> Unit
) = remember {
    val navDestinationsBuilder = NavDestinationsBuilder()
    navDestinationsBuilder.block()
    NavGraph(
        startRoute,
        startArguments,
        navDestinationsBuilder.build()
    )
}