package com.pairjax.starry.entities

import com.pairjax.starry.ids.ID
import com.pairjax.starry.ids.Type

/**
 * Starry Entities are capable of storing any ID, but also have their own ID to identify them
 * in the world.
 */
class Entity(public val entityId: ID) {
    var type: Type = Type()
    var ids: MutableList<ID> = mutableListOf()

    public fun addIDs(addingIDs: List<ID>) {
        for (id in addingIDs) addID(id)
    }

    fun addID(id: ID) {
        ids += id
    }

    public fun removeIDs(addingIDs: List<ID>) {
        for (id in addingIDs) removeID(id)
    }

    fun removeID(id: ID) {
        if (ids.contains(id)) ids -= id
    }
}