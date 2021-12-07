package com.pairjax.starry.entities

import com.pairjax.starry.ids.ID
import com.pairjax.starry.ids.Type

/**
 * Starry Entities are capable of storing any ID, but also have their own ID
 * to identify them in the world.
 */
class Entity(public val entityId: ID, public val name: String) {
    var type: Type = Type()
    var ids: MutableList<ID> = mutableListOf()

    public fun addIDs(addIDs: List<ID>) {
        addIDs.map { ids += it }
    }

    public fun removeIDs(subIDs: List<ID>) {
        subIDs.map { ids -= it }
    }
}