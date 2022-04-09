package ro.dragossusi.navigation

class NavArguments constructor(
    val map: Map<String, Any> = emptyMap()
) {
    inline operator fun <reified T> get(key: String): T? {
        return map[key] as T?
    }
}

private val emptyArguments = NavArguments()

fun emptyArguments() = emptyArguments