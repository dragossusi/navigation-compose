package ro.dragossusi.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue

@Composable
fun NavHost(
    navController: NavController,
    startRoute: String,
    startArguments: NavArguments = emptyArguments(),
    block: NavDestinationsBuilder.() -> Unit
) {
    val graph = rememberNavGraph(startRoute, startArguments, block)
    navController.navGraph = graph
    val navBackstackEntry by navController.navBackstackEntry
    CompositionLocalProvider(LocalNavController provides navController) {
        navBackstackEntry?.compose()
    }
}