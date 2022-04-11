package ro.dragossusi.navigation

class NavBackstackList(
    override val destination: NavDestinationList,
    override val parent: BackstackEntry? = null
) : BackstackEntry {

    private val entries: MutableList<BackstackEntry> = mutableListOf(
        destination.createStartEntry(this)
    )

    override fun navigateUp(): BackstackEntry? {
        if (entries.size <= 1) {
            clear()
            return parent?.navigateUp()
        }
        entries.removeLast().clear()
        return entries.last()
    }

    override fun clear() {
        entries.forEach {
            it.clear()
        }
        entries.clear()
    }

    override val current: NavBackstackEntry
        get() = (entries.lastOrNull() ?: destination.createStartEntry(this)
            .also {
                println("This shouldn't happen, fix me later")
                entries += it
            })
            .current

    override fun contains(route: String): Boolean {
        return entries.any { it.route == route }
    }

    fun hasRoute(route: String): Boolean {
        return destination[route] != null
    }

    override fun navigateUpTo(route: String): BackstackEntry? {
        if (!contains(route)) return null
        //find possible backstack
        val index = entries.indexOfLast {
            it.route == route
        }
        if (index == -1) return null
        repeat(entries.lastIndex - index) {
            entries.removeLast().clear()
        }
        return entries.last()
    }

    fun navigate(
        route: String,
        arguments: NavArguments = emptyArguments(),
        navOptions: NavOptions = emptyNavOptions()
    ): BackstackEntry {
        if (route == this.route) {
            return this
        }
        //create only one instance if needed
        var entry = if (navOptions.launchSingleTop) {
            getAndClearExisting(route)
        } else null

        //setup popup
        if (navOptions.popUpTo != null) navigateUpTo(route)
        if (entry == null || !navOptions.restoreState) entry = createNewEntry(route, arguments)
        entries += entry
        return entry
    }

    private fun createNewEntry(route: String, arguments: NavArguments): BackstackEntry {
        return when (val destination = requireDestination(route)) {
            is NavDestinationEntry -> {
                NavBackstackEntry(
                    destination = destination,
                    parent = this,
                    arguments = arguments
                )
            }
            is NavDestinationList -> {
                NavBackstackList(
                    destination = destination,
                    parent = this,
                )
            }
        }
    }

    private fun getAndClearExisting(route: String): BackstackEntry? {
        val existing = entries.filter { it.route == route }.toMutableList()
        //none found
        if (existing.isEmpty()) return null
        //take last
        val last = existing.removeLast()

        //remove others
        if (existing.isNotEmpty()) {
            existing.forEach {
                it.clear()
            }
            entries.removeAll(existing)
        }
        return last
    }

    internal fun requireDestination(route: String): Destination {
        return destination.requireDestination(route)
    }

    override fun toString(): String {
        return "NavBackstackList(route=$route,entries=$entries)"
    }


}