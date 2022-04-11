package ro.dragossusi.navigation

import androidx.compose.runtime.*

class NavGraph internal constructor(
    start: String,
    startArguments: NavArguments,
    destinations: NavDestinationList
) {

    val backstack: NavBackstackList = NavBackstackList(destinations)
    private var currentBackStack: NavBackstackList = backstack

    init {
        backstack.navigate(
            start,
            startArguments
        )
    }

    fun navigate(
        route: String,
        arguments: NavArguments = emptyArguments(),
        navOptions: NavOptions = emptyNavOptions()
    ): BackstackEntry {
        val target = when {
            currentBackStack.hasRoute(route) -> currentBackStack
            backstack.hasRoute(route) -> backstack
            else -> throw IllegalArgumentException("Can't navigate to $route from ${currentBackStack.route}")
        }

        val backstackEntry = target.navigate(route, arguments, navOptions)
        if (backstackEntry is NavBackstackList) {
            currentBackStack = backstackEntry
        }
        return backstackEntry
    }

    fun navigateUp(): BackstackEntry? {
        return currentBackStack.navigateUp()
    }

    fun navigateUpTo(route: String): BackstackEntry? {
        return currentBackStack.navigateUpTo(route = route)
    }

}

@Composable
internal fun rememberNavGraph(
    startRoute: String,
    startArguments: NavArguments = emptyArguments(),
    block: NavDestinationsBuilder.() -> Unit
) = remember {
    val navDestinationsBuilder = NavDestinationsBuilder("_", startRoute)
    navDestinationsBuilder.block()
    NavGraph(
        startRoute,
        startArguments,
        navDestinationsBuilder.build()
    )
}