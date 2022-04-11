package ro.dragossusi.navigation

data class NavOptions(
    val popUpTo: String? = null,
    val launchSingleTop: Boolean = false,
    val restoreState: Boolean = false
)

private val EmptyNavOptions = NavOptions()
fun emptyNavOptions() = EmptyNavOptions