package com.avaquo.starry.world

import com.avaquo.starry.entities.Entity
import com.avaquo.starry.ids.ID
import com.avaquo.starry.ids.Table

/**
 * Storage for tables & other essential data.
 */
class Store(world: World) {
    var lastID: ID = 0u; // The last ID used globally.

    var tables: MutableList<Table> = mutableListOf()

    init {
        if (world.debug) print("[DEBUG] Loading Storage...")
    }

    fun getNewID(): ULong {
        return ++lastID;
    }

    fun updateTables(entity: Entity) {
        var hasType = false

        tables.map {
            if (entity.type == it[0].type) {
                if (!it.contains(entity)) it += entity
                hasType = true
                return;
            }
        }

        print("Found entity? $hasType")

        if (!hasType) {
            val newTable: Table = mutableListOf(entity)
            tables += newTable
        }
    }
}