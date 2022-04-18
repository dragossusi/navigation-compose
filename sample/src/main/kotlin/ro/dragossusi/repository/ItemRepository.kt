package ro.dragossusi.repository

interface ItemRepository {
    suspend fun getItems(): List<String>
}