package ro.dragossusi.navigation

import androidx.compose.runtime.compositionLocalOf
import ro.dragossusi.lifecycle.ViewModel

val LocalViewModelFactory = compositionLocalOf<ViewModel.Factory?> {
    null
}