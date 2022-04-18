package ro.dragossusi.lifecycle

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import ro.dragossusi.navigation.*

@Suppress("UNCHECKED_CAST")
@Composable
inline fun <reified VM : ViewModel> rememberViewModel(
    factory: ViewModel.Factory? = null
): VM {
    val factory = factory ?: LocalViewModelFactory.current!!
    val store = LocalViewModelStore.current
        ?: throw IllegalArgumentException("No ViewModelStore")
    val arguments = LocalNavArguments.current ?: emptyArguments()
    return remember(store, arguments) {
        store.getOrPut("nav_view_model") {
            factory.createViewModel(VM::class, arguments)
        } as VM
    }
}