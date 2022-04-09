package ro.dragossusi

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ro.dragossusi.lifecycle.ViewModel

abstract class MviViewModel<S>(
    initialState: S
) : ViewModel() {

    private val _uiState = MutableStateFlow(initialState)

    // UI state exposed to the UI
    val uiState = _uiState.asStateFlow()

    /**
     * Update the state with suspend functiosn so we don't block main thread,
     * safe to change context to IO inside of it
     *
     * It is launched using viewModelScope, so it will be canceled when the
     * viewModel is cleared
     */
    protected fun updateState(block: (S) -> S) {
        _uiState.update(block)
    }

    protected fun setState(state: S) {
        _uiState.value = state
    }

}
