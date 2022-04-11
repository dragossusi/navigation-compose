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
import ro.dragossusi.navigation.NavHost
import ro.dragossusi.navigation.rememberNavController


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
                            selected = entry?.route == it.route,
                            onClick = {
                                navController.navigate(it.route)
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
                            navController.navigate("item")
                        },
                        onGoToHelp = {
                            navController.navigate("help")
                        }
                    )
                }
                composable("item") {
                    ItemScreen(
                        onClick = navController::navigateUp
                    )
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