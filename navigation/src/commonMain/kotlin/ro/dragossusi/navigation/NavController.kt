package ro.dragossusi.navigation

import androidx.compose.runtime.*

class NavController {

    private val _navBackstackEntry = mutableStateOf<NavBackstackEntry?>(null)
    val navBackstackEntry: State<NavBackstackEntry?> = _navBackstackEntry

    internal var navGraph: NavGraph? = null
        set(value) {
            if (value == null || field == value) return
            if (field != null) throw IllegalArgumentException("Nav controller already has a graph")
            field = value
            _navBackstackEntry.value = value.backstack.current
        }

    fun requireNavGraph() = navGraph ?: throw IllegalStateException("NavGraph not set yet")

    fun navigate(
        route: String,
        arguments: NavArguments = emptyArguments(),
        navOptions: NavOptions = emptyNavOptions()
    ) {
        navGraph?.navigate(route, arguments, navOptions)
            ?.let {
                _navBackstackEntry.value = it.current
            }
    }

    fun navigateUp() {
        navGraph?.navigateUp()?.let {
            _navBackstackEntry.value = it.current
        }
    }

    fun navigateUpTo(route: String) {
        navGraph?.navigateUpTo(route)?.let {
            _navBackstackEntry.value = it.current
        }
    }
}

@Composable
fun rememberNavController() = remember {
    NavController()
}