package ro.dragossusi

import androidx.compose.ui.Alignment
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
        NavHost(
            navController,
            "home"
        ) {
            composable("home") {
                HomeScreen(
                    onItem = {
                        navController.navigate("item")
                    },
                    onHelp = {
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