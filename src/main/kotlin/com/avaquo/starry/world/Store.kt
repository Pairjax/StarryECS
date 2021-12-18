package com.avaquo.starry.world

import com.avaquo.starry.entities.Entity
import com.avaquo.starry.example.ExampleComponent
import com.avaquo.starry.ids.ID
import com.avaquo.starry.ids.Table

/**
 * Storage for tables & other essential data.
 */
class Store(world: World) {
    var heapID: ID = 0u; // The bottom available ID.
    var stackID: ID = ULong.MAX_VALUE; // The top available ID.

    var tables: MutableList<Table> = mutableListOf()

    var elementMap: MutableMap<ID, Any> = mutableMapOf()
    var typeMap: MutableMap<String, ID> = mutableMapOf()


    init {
        if (world.debug) print("[DEBUG] Loading Storage Defaults...")
    }

    fun addElement(element: Any) {
        val elemType = element::class.simpleName.toString()

        if (!typeMap.containsKey(elemType))
        {
            val id = getNextID()

            typeMap += Pair(elemType, id)
            elementMap += Pair(id, elemType)
        }

        elementMap += Pair(getNextID(), element)
    }

    fun getNextID(): ULong {
        return ++heapID;
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