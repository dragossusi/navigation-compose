package ro.dragossusi.item

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ro.dragossusi.MviViewModel

data class ItemUiState(
    val isLoading: Boolean = true
)

class ItemViewModel : MviViewModel<ItemUiState>(ItemUiState()) {

    init {
        viewModelScope.launch {
            updateState { it.copy(isLoading = true) }
            delay(2000L)
            updateState { it.copy(isLoading = false) }
        }
    }

    override fun onCleared() {
        super.onCleared()
        println("Cleared ItemViewModel")
    }

}