package ro.dragossusi.navigation

import androidx.compose.runtime.*

class NavController {

    private val _navBackstackEntry = mutableStateOf<NavBackstackEntry?>(null)
    val navBackstackEntry: State<NavBackstackEntry?> = _navBackstackEntry

    private val backstack: MutableList<NavBackstackEntry> = mutableListOf()

    internal var navGraph: NavGraph? = null
        set(value) {
            if (value == null || field == value) return
            if (field != null) throw IllegalArgumentException("Nav controller already has a graph")
            field = value
            val startBackstackEntry = value.createStartEntry()
            backstack.add(startBackstackEntry)
            _navBackstackEntry.value = startBackstackEntry
        }

    fun requireNavGraph() = navGraph ?: throw IllegalStateException("NavGraph not set yet")


    fun navigate(
        route: String,
        arguments: NavArguments = emptyArguments(),
        popUpTo: String? = null
    ) {
        if (popUpTo != null) {
            val index = backstack.indexOfLast {
                it.route == popUpTo
            }
            if (index != -1) {
                repeat(backstack.lastIndex - index) {
                    backstack.removeLast().clear()
                }
            }
        }
        navigateInternal(
            NavBackstackEntry(
                requireNavGraph().requireDestination(route),
                arguments
            )
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
            NavBackstackEntry(
                destination = requireNavGraph().requireDestination(route),
                arguments = arguments
            )
        )
    }

    private fun navigateInternal(entry: NavBackstackEntry) {
        backstack += entry
        _navBackstackEntry.value = entry
    }

    fun navigateUp() {
        if (backstack.size < 2) return
        backstack.removeLast().clear()
        _navBackstackEntry.value = backstack.last()
    }

    fun navigateUpTo(route: String) {
        val index = backstack.indexOfLast {
            it.route == route
        }
        if (index == -1) throw IllegalArgumentException("$route not on backstack")
        repeat(backstack.lastIndex - index) {
            backstack.removeLast().clear()
        }
        _navBackstackEntry.value = backstack.last()
    }
}

@Composable
fun rememberNavController() = remember {
    NavController()
}