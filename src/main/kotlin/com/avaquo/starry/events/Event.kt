package com.avaquo.starry.events

import com.avaquo.starry.entities.Entity
import com.avaquo.starry.ids.ID
import com.avaquo.starry.ids.IDs
import com.avaquo.starry.queries.Filter
import com.avaquo.starry.queries.Query
import com.avaquo.starry.systems.System
import com.avaquo.starry.world.Store

class Event(
    name: String,
    id: ID,
    private val observer: Observer,
    val filter: Filter,
    private val callback: () -> Unit
): Entity(name, id) {
    fun runEvent(condition: ID) {
        if (!observer.conditionMet(condition)) return

        callback.invoke()
    }
}