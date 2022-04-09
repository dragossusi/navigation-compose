package ro.dragossusi.navigation

import androidx.compose.runtime.Composable

class NavDestinationsBuilder {

    private val navDestinations = mutableMapOf<String, NavDestination>()

    fun composable(
        route:String,
        block: @Composable (NavArguments) -> Unit
    ) {
        navDestinations[route] = NavDestination(route, block)
    }

    fun build() = navDestinations.toMap()

}