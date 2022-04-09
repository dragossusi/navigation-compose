package ro.dragossusi.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

class NavController {

    internal var navGraph: NavGraph? = null

    fun requireNavGraph() = navGraph ?: throw IllegalStateException("NavGraph not set yet")

    fun navigate(
        route: String,
        arguments: NavArguments = emptyArguments(),
        popUpTo: String? = null
    ) = requireNavGraph().navigate(route, arguments, popUpTo)

    fun navigateUp() = requireNavGraph().navigateUp()

    fun navigateUpTo(route: String) = requireNavGraph().navigateUpTo(route)
}

@Composable
fun rememberNavController() = remember {
    NavController()
}