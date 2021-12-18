package com.avaquo.starry.world

import com.avaquo.starry.entities.Entity
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

    fun createEntity(name: String) {
        val e = Entity(name)
        storage.addElement(e)
        storage.stowEntity(e)
    }

    fun destroyEntity(entityID: ID) {

    }

    /** Remove all IDs from an entity */
    fun clearEntity() {

    }

    fun addElementToEntity() {

    }

    fun removeElementFromEntity() {

    }
}