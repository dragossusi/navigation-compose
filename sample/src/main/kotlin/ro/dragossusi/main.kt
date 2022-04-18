package ro.dragossusi

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.compose.withDI
import org.kodein.di.instance
import ro.dragossusi.arch.NewInstanceFactory
import ro.dragossusi.home.HomeScreen
import ro.dragossusi.item.ItemScreen
import ro.dragossusi.item.ItemViewModel
import ro.dragossusi.navigation.*
import ro.dragossusi.repository.ItemRepository
import ro.dragossusi.repository.ItemRepositoryImpl

val appDi = DI {
    bindProvider<ItemRepository> {
        ItemRepositoryImpl
    }
    bindProvider {
        ItemViewModel(instance())
    }
}

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
        withDI(appDi) {
            CompositionLocalProvider(
                LocalViewModelFactory provides NewInstanceFactory
            ) {
                App()
            }
        }
    }
}