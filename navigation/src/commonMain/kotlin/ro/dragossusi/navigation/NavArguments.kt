package ro.dragossusi.navigation

data class NavArguments constructor(
    val map: Map<String, Any> = emptyMap()
) {

    constructor(vararg pairs: Pair<String, Any>) : this(mapOf(*pairs))

    inline operator fun <reified T> get(key: String): T? {
        return map[key] as T?
    }

    override fun toString(): String {
        return "NavArguments(" +
                (if (map.isEmpty()) "" else "map=$map") +
                ")"
    }
}

private val emptyArguments = NavArguments()

fun emptyArguments() = emptyArguments