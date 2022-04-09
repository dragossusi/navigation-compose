package ro.dragossusi.navigation

import androidx.compose.runtime.*

class NavGraph internal constructor(
    private val start: String,
    private val startArguments: NavArguments,
    private val destinations: Map<String, NavDestination>
) {

    private val backstack: MutableList<NavBackstackEntry>
    private val _navBackstackEntry: MutableState<NavBackstackEntry>
    val navBackstackEntry: State<NavBackstackEntry>
        get() = _navBackstackEntry

    init {
        val startDestination = requireDestination(start)
        val startBackstackEntry = NavBackstackEntry(startDestination, startArguments)
        backstack = mutableListOf(startBackstackEntry)
        _navBackstackEntry = mutableStateOf(startBackstackEntry)
    }

    fun navigate(
        route: String,
        arguments: NavArguments = emptyArguments(),
        popUpTo: String? = null
    ) {
        if (popUpTo != null) {
            val index = backstack.indexOfLast {
                it.destination.route == popUpTo
            }
            if (index != -1) {
                repeat(backstack.lastIndex - index) {
                    backstack.removeLast().clear()
                }
            }
        }
        navigateInternal(
            NavBackstackEntry(requireDestination(route), arguments)
        )
    }

    fun navigateFresh(
        route: String,
        arguments: NavArguments = emptyArguments(),
    ) {
        repeat(backstack.size) {
            backstack.removeLast().clear()
        }
        navigateInternal(
            NavBackstackEntry(requireDestination(route), arguments)
        )
    }

    private fun navigateInternal(entry: NavBackstackEntry) {
        backstack += entry
        _navBackstackEntry.value = entry
    }

    private fun requireDestination(route: String): NavDestination {
        return destinations[route] ?: throw IllegalArgumentException("$route not declared in the builder")
    }

    fun navigateUp() {
        if (backstack.size < 2) return
        backstack.removeLast().clear()
        _navBackstackEntry.value = backstack.last()
    }

    fun navigateUpTo(route: String) {
        val index = backstack.indexOfLast {
            it.destination.route == route
        }
        if (index == -1) throw IllegalArgumentException("$route not on backstack")
        repeat(backstack.lastIndex - index) {
            backstack.removeLast().clear()
        }
        _navBackstackEntry.value = backstack.last()
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