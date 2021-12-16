package com.pairjax.starry.world

/**
 * The World is the box that holds all ECS operations; you can have multiple worlds,
 * but they will not interact unless you design specific Systems to interface between
 * them.
 */
class World(name: String) {
    val debug = true;
    val storage: Store = Store(this);

    init {
        if (debug) print("[DEBUG] Loading World $name")
    }

    fun createEntity() {

    }

    fun destroyEntity() {

    }

    /** Remove all IDs from an entity */
    fun clearEntity() {

    }

    fun addElementToEntity() {

    }

    fun removeElementFromEntity() {

    }
}