package com.avaquo.starry.queries

import com.avaquo.starry.entities.Entity
import com.avaquo.starry.ids.ID
import com.avaquo.starry.world.Store

/**
 * A cached search for an entity based on specific terms.
 * @param store Reference to the world's storage
 * @param filter The search engine which seeks for the entity
 * @param cache The local storage for the entity.
 */
class Query(
    private val store: Store,
    val filter: Filter,
) {
    val cache: HashMap<ID, Entity> = hashMapOf()

    fun searchAndCache() {
        val ids = filter.search()

        cache.clear()

        for (id in ids) cache[id] = store.getEntity(id)
    }
}