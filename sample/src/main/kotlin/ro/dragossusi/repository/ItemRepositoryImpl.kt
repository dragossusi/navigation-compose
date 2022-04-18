package ro.dragossusi.repository

import kotlinx.coroutines.delay

object ItemRepositoryImpl : ItemRepository {
    override suspend fun getItems(): List<String> {
        delay(1000L)
        return listOf(
            "A",
            "B",
            "C",
        )
    }
}