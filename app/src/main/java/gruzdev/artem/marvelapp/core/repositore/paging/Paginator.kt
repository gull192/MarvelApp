package gruzdev.artem.marvelapp.core.repositore.paging

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    suspend fun reset()
}
