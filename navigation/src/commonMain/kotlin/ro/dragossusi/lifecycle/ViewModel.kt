package ro.dragossusi.lifecycle

import kotlinx.coroutines.*
import ro.dragossusi.navigation.NavArguments
import kotlin.reflect.KClass

abstract class ViewModel {

    protected val viewModelScope = CoroutineScope(
        context = SupervisorJob() + Dispatchers.Main.immediate
    )

    protected open fun onCleared() = Unit

    internal fun clear() {
        viewModelScope.cancel(CancellationException("ViewModel cleared"))
        onCleared()
    }

    interface Factory {

        fun <VM : ViewModel> createViewModel(clazz: KClass<VM>, arguments: NavArguments?): VM

    }

}