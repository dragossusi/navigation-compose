package ro.dragossusi.navigation

import ro.dragossusi.lifecycle.ViewModel

open class ViewModelStore {

    private val mMap = HashMap<String, ViewModel>()

    operator fun set(key: String, viewModel: ViewModel) {
        val oldViewModel = mMap.put(key, viewModel)
        oldViewModel?.clear()
    }

    operator fun get(key: String): ViewModel? {
        return mMap[key]
    }

    inline fun getOrPut(key: String, creator: () -> ViewModel): ViewModel {
        return get(key) ?: run {
            val vm = creator()
            set(key, vm)
            vm
        }
    }

    fun keys(): Set<String> {
        return HashSet(mMap.keys)
    }

    /**
     * Clears internal storage and notifies ViewModels that they are no longer used.
     */
    fun clear() {
        mMap.values.forEach { it.clear() }
        mMap.clear()
    }
}