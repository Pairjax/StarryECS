package com.avaquo.starry.api

import com.avaquo.starry.entities.Entity
import com.avaquo.starry.ids.Element
import com.avaquo.starry.ids.ID
import com.avaquo.starry.world.World

/**
 * The world within which all ECS operations are held.
 * @param debug output helpful debug info through the console
 */
class StarryWorld(name: String, debug: Boolean) {
    //region INTERNAL_SYSTEMS
    /** This is the internal world beneath the API wrapper. Don't use this! */
    val world: World = World(name, debug)

    /**
     * This is an internal function used in entity creation. Don't call this!
     */
    fun createInternalEntity(entity: StarryEntity): Entity {
        val e = world.createEntity(entity.name)

        if (entity.elements == null) return e

        for (element in entity.elements) world.addElementToEntity(e.id, element)

        return e
    }
    //endregion

    //region HELPER_FUNCTIONS

    //endregion
}