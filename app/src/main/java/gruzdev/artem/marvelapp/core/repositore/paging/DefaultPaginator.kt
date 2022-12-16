package gruzdev.artem.marvelapp.core.repositore.paging

import gruzdev.artem.marvelapp.core.repositore.network.Resource

class DefaultPaginator<Key, Item> (
    private val initKey: Key,
    private inline val onLoadUpdated: (Boolean) -> Unit,
    private inline val onRequest: suspend (nextKey: Key) -> Resource<List<Item>>,
    private inline val getNextKey: suspend  (List<Item>) -> Key,
    private inline val onError: suspend (String) -> Unit,
    private inline val onSuccess: suspend (items: List<Item>, newKey: Key) -> Unit
) : Paginator<Key, Item>{

    private var currentKey: Key = initKey
    private var isMakingRequest: Boolean = false

    override suspend fun loadNextItems() {
        if (isMakingRequest) return
        isMakingRequest = true
        onLoadUpdated(true)
        val result = onRequest(currentKey)
        isMakingRequest = false
        when(result) {
            is Resource.Error -> {
                result.message?.let { onError(it) }
                onLoadUpdated(false)
                return
            }
            is Resource.Success -> {
                currentKey = result.data?.let { getNextKey(it) }!!
                onSuccess(result.data, currentKey)
                onLoadUpdated(false)
            }
            else -> {}
        }
    }

    override suspend fun reset() {
        currentKey =  initKey
    }

}
