package ro.dragossusi.navigation

interface BackstackEntry {
    val parent: BackstackEntry?
    val current: NavBackstackEntry
    val destination: Destination
    fun navigateUp(): BackstackEntry?
    fun contains(route: String): Boolean
    fun navigateUpTo(route: String): BackstackEntry?
    fun clear()
}


val BackstackEntry.route: String
    get() = destination.route

fun BackstackEntry.startsWith(route: String): Boolean {
    if (this.route == route) return true
    return parent?.startsWith(route) ?: false
}
