package ro.dragossusi.lifecycle

import kotlinx.coroutines.*

abstract class ViewModel {

    protected val viewModelScope = CoroutineScope(
        context = SupervisorJob() + Dispatchers.Main.immediate
    )

    protected open fun onCleared() = Unit

    internal fun clear() {
        viewModelScope.cancel(CancellationException("ViewModel cleared"))
        onCleared()
    }

}