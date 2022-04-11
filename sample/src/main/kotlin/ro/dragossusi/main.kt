package ro.dragossusi

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import ro.dragossusi.home.HomeScreen
import ro.dragossusi.item.ItemScreen
import ro.dragossusi.navigation.*


@OptIn(ExperimentalMaterial3Api::class)
fun main() = application {
    val windowState = rememberWindowState(
        size = DpSize(1280.dp, 720.dp),
        position = WindowPosition.Aligned(Alignment.Center)
    )
    Window(
        title = "Sevens",
        onCloseRequest = ::exitApplication,
        state = windowState
    ) {
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
}