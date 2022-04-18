package ro.dragossusi.item

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ro.dragossusi.MviViewModel
import ro.dragossusi.repository.ItemRepository

data class ItemUiState(
    val isLoading: Boolean = true,
    val items: List<String> = emptyList(),
    val error: Throwable? = null
)

class ItemViewModel(
    private val repository: ItemRepository
) : MviViewModel<ItemUiState>(ItemUiState()) {

    init {
        viewModelScope.launch {
            updateState { it.copy(isLoading = true) }
            runCatching {
                repository.getItems()
            }.onSuccess { items ->
                updateState { it.copy(isLoading = false, items = items) }
            }.onFailure { error ->
                updateState {
                    it.copy(
                        isLoading = false,
                        error = error
                    )
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        println("Cleared ItemViewModel")
    }

}