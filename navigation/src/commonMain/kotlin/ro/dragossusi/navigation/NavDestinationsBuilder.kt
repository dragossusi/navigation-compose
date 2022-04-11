package ro.dragossusi.navigation

import androidx.compose.runtime.Composable

class NavDestinationsBuilder(
    private val route: String,
    private val startRoute: String
) {

    private val destinations = mutableMapOf<String, Destination>()

    fun composable(
        route: String,
        block: @Composable (NavArguments) -> Unit
    ) {
        destinations[route] = NavDestinationEntry(route, block)
    }

    fun navigation(
        route: String,
        startRoute: String,
        block: NavDestinationsBuilder.() -> Unit
    ) {
        val builder = NavDestinationsBuilder(route, startRoute)
        builder.block()
        destinations[route] = builder.build()
    }

    fun build(): NavDestinationList = NavDestinationList(route, startRoute, destinations)

}