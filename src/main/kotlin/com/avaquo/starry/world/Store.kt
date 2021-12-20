package com.avaquo.starry.world

import com.avaquo.starry.entities.Entity
import com.avaquo.starry.ids.Element
import com.avaquo.starry.ids.ID
import com.avaquo.starry.ids.Table

/**
 * Stores all the data in a World. Contains:
 * - most recent valid ID
 * - tables for all Entities
 * - map of all elements in the world
 * - list of all valid types of elements
 */
class Store(world: World) {

    private var heapID: ID = 0u // The bottom available ID.
    // private var stackID: ID = ULong.MAX_VALUE // The top available ID.

    // Every table in the World
    var tables: MutableList<Table> = mutableListOf()
        private set

    // Every element in the World
    var elementMap: MutableMap<ID, Element> = mutableMapOf()
        private set

    // Every ID that identifies a type element
    var typeMap: MutableMap<String, ID> = mutableMapOf()

    init {
        if (world.debug) print("[DEBUG] Loading Storage Defaults...")
    }

    fun getNextID(): ULong { return ++heapID }

    /**
     * Takes a new element and registers it into the ECS World.
     * If the element's typeID is new, it will also be added.
     * @param element The element added into the world.
     */
    fun addElement(element: Element): ID {
        val typeElemID = element::class.simpleName.toString()

        if (!typeMap.containsKey(typeElemID)) {
            val typeId = getNextID()

            typeMap += Pair(typeElemID, typeId)
            elementMap += Pair(typeId, typeElemID)
        }

        val id = getNextID()
        elementMap += Pair(id, element)

        return id
    }

    /**
     * Takes a new element and registers it into the ECS World.
     * If the element's typeID is new, it will also be added.
     * @param element The element added into the world.
     */
    fun removeElement(id: ID): Boolean {
        if (elementMap.containsKey(id)) {
            elementMap.remove(id)
            return true
        }

        return false
    }

    fun getElement(id: ID): Element { return elementMap[id] as Element }

    fun getEntity(id: ID): Entity { return elementMap[id] as Entity }

    /**
     * Adds an Entity to its associated Table based on its Type.
     * If the element's Type has no Table yet, it is created.
     * @param entity The entity added to a Table.
     */
    fun addToTable(entity: Entity) {
        var hasType = false

        tables.map {
            val e = elementMap[it[0]] as Entity

            if (entity.type == e.type) {
                if (!it.contains(entity.id)) it += entity.id

                hasType = true
                return
            }
        }

        if (!hasType) {
            val newTable: Table = mutableListOf(entity.id)
            tables += newTable
        }
    }

    /**
     * Removes an Entity from its associated table.
     */
    fun removeFromTable(entity: Entity) {
        tables.map {
            val e = elementMap[it[0]] as Entity

            if (entity.type == e.type) {
                if (!it.contains(entity.id)) it -= entity.id
                if (it.isEmpty()) tables.remove(it)

                return
            }
        }
    }
}