package ro.dragossusi.home

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ro.dragossusi.MviViewModel

data class HomeUiState(
    val isLoading: Boolean = true
)

class HomeViewModel : MviViewModel<HomeUiState>(HomeUiState()) {

    init {
        viewModelScope.launch {
            updateState { it.copy(isLoading = true) }
            delay(1000L)
            updateState { it.copy(isLoading = false) }
        }
    }

    override fun onCleared() {
        super.onCleared()
        println("Cleared HomeViewModel")
    }

}