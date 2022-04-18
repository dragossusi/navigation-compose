package ro.dragossusi

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import ro.dragossusi.home.HomeScreen
import ro.dragossusi.item.ItemScreen
import ro.dragossusi.navigation.NavHost
import ro.dragossusi.navigation.NavOptions
import ro.dragossusi.navigation.rememberNavController
import ro.dragossusi.navigation.startsWith

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    val navController = rememberNavController()
    val entry by navController.navBackstackEntry
    Scaffold(
        bottomBar = {
            NavigationBar {
                BotNavItem.values().forEach {
                    NavigationBarItem(
                        selected = entry?.startsWith(it.route) ?: false,
                        onClick = {
                            navController.navigate(
                                it.route,
                                navOptions = NavOptions(
                                    launchSingleTop = true,
                                    restoreState = true
                                )
                            )
                        },
                        icon = {
                            Icon(Icons.Default.Home, contentDescription = null)
                        },
                        label = {
                            Text(it.route)
                        }
                    )
                }
            }
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
                .padding(it)
        )
        NavHost(
            navController,
            "home"
        ) {
            composable("home") {
                HomeScreen(
                    onGoToItem = {
                        navController.navigate("items")
                    },
                    onGoToHelp = {
                        navController.navigate("items")
                        navController.navigate("help")
                    }
                )
            }
            navigation("items", startRoute = "item") {
                composable("item") {
                    ItemScreen { navController.navigate("help") }
                }
                composable("help") {
                    HelpScreen(
                        onClick = navController::navigateUp
                    )
                }
            }
        }
    }
}