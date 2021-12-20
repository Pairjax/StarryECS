package com.avaquo.starry.queries

import com.avaquo.starry.entities.Entity
import com.avaquo.starry.ids.ID
import com.avaquo.starry.ids.IDs
import com.avaquo.starry.world.Store

class Query(
    val store: Store,
    val filter: Filter,
    var cache: HashMap<ID, Entity>
) {
    fun searchAndCache() {
        val ids = filter.search()

        for (id in ids) cache[id] = store.getEntity(id)
    }
}