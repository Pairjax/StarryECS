package com.avaquo.starry.world

import com.avaquo.starry.entities.Entity
import com.avaquo.starry.ids.Element
import com.avaquo.starry.ids.ID

/**
 * The World is the box that holds all ECS operations; you can have multiple worlds,
 * but they will not interact unless you design specific Systems to interface between
 * them.
 */
class World(name: String, debug: Boolean) {
    public val debug: Boolean = true
    val storage: Store = Store(this)

    init {
        if (debug) print("[DEBUG] Loading World $name")
    }

    //region ENTITIES
    fun createEntity(name: String): Entity {
        val e = Entity(name)

        storage.addElement(e)
        storage.addToTable(e)

        return e
    }

    fun destroyEntity(id: ID) {
        val e = storage.getEntity(id)

        for (elementId in e.ids) storage.removeElement(elementId)

        storage.removeFromTable(e)
    }

    /** Remove all IDs from an entity */
    fun clearEntity(id: ID) {
        val e = storage.getEntity(id)

        for (elementId in e.ids) storage.removeElement(elementId)

        storage.removeFromTable(e)

        e.ids.clear()
        e.type.clear()

        storage.addToTable(e)
    }

    /**
     * Adds a single element to an entity.
     */
    fun addElementToEntity(entityID: ID, element: Element) {
        val e = storage.getEntity(entityID)

        storage.removeFromTable(e)

        val elementID = storage.addElement(element)

        e.ids += elementID

        storage.addToTable(e)
    }

    /**
     * Removes a single element from an entity.
     */
    fun removeElementFromEntity(entityID: ID, elementID: ID) {
        val e = storage.getEntity(entityID)

        storage.removeFromTable(e)

        storage.removeElement(elementID)

        e.ids -= elementID

        storage.addToTable(e)
    }
    //endregion

    //region PIPELINE

    //endregion
}