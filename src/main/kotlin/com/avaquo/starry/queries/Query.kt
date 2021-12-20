package com.avaquo.starry.queries

import com.avaquo.starry.ids.ID
import com.avaquo.starry.ids.IDs
import com.avaquo.starry.world.Store

class Query(
    store: Store,
    terms: MutableList<Term>,
    var cache: HashMap<ID, Any>
): Filter(store, terms) {

}