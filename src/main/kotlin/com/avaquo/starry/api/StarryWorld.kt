package com.avaquo.starry.api

import com.avaquo.starry.entities.Entity
import com.avaquo.starry.world.World

/**
 * The world within which all ECS operations are held.
 * @param debug output helpful debug info through the console
 */
class StarryWorld(name: String, debug: Boolean) {
    //region INTERNAL_SYSTEMS
    /** This is the internal world beneath the API wrapper. Don't use this! */
    val world: World = World(name, debug)
    //endregion
}