package ro.dragossusi.lifecycle

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import ro.dragossusi.navigation.LocalNavArguments
import ro.dragossusi.navigation.LocalViewModelStore
import ro.dragossusi.navigation.NavArguments
import ro.dragossusi.navigation.emptyArguments

@Suppress("UNCHECKED_CAST")
@Composable
fun <VM : ViewModel> rememberViewModel(
    builder: (NavArguments) -> VM
): VM {
    val store = LocalViewModelStore.current
        ?: throw IllegalArgumentException("No ViewModelStore")
    val arguments = LocalNavArguments.current ?: emptyArguments()
    return remember(store, arguments) {
        store.getOrPut("nav_view_model") {
            builder(arguments)
        } as VM
    }
}