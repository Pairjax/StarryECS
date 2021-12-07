package com.pairjax.starry.world

import com.pairjax.starry.ids.ID
import com.pairjax.starry.ids.Table

/**
 * Storage for tables & other essential data.
 */
class Store(world: World) {
    var lastID: ID = 0u; // The last ID used globally.

    var tables: List<Table> = listOf()

    init {
        if (world.debug) print("[DEBUG] Loading Storage...")
    }

}