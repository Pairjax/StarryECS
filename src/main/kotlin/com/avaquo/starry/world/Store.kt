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

    // Every table in the World
    val tables: MutableList<Table> = mutableListOf()

    // Every element in the World
    private val elementMap: MutableMap<ID, Element> = mutableMapOf()

    // Every ID that identifies a type element
    val typeMap: MutableMap<String, ID> = mutableMapOf()

    init {
        if (world.debug) println("[DEBUG] Loading Storage Defaults...")
    }

    fun getNextID(): ULong { return heapID++ }

    /**
     * Takes a new element and registers it into the ECS World.
     * If the element's typeID is new, it will also be added.
     * @param element The element added into the world.
     * @param id the ID of the element.
     */
    fun addElement(element: Element, id: ID) {
        val typeElemID = element::class.simpleName.toString()

        if (!typeMap.containsKey(typeElemID)) {
            val typeId = getNextID()

            typeMap += Pair(typeElemID, typeId)
            elementMap += Pair(typeId, typeElemID)
        }

        elementMap += Pair(id, element)
    }

    /**
     * Takes a new element without an ID and adds it to the table and creates a new ID for it.
     * @param element The element added into the world.
     * @return the element's new ID.
     */
    fun addElement(element: Element): ID {
        val id = getNextID()

        addElement(element, id)

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

    fun getElement(id: ID): Element {
        if (!elementMap.containsKey(id)) return false

        return elementMap[id] as Element
    }

    fun getEntity(id: ID): Entity { return elementMap[id] as Entity }

    fun getType(typeName: String): ID { return typeMap[typeName] as ID }

    /**
     * Adds an Entity to its associated Table based on its Type.
     * If the element's Type has no Table yet, it is created.
     * @param entity The entity added to a Table.
     */
    fun addToTable(entity: Entity) {
        tables.map {
            val e = elementMap[it[0]] as Entity

            if (entity.type == e.type) {
                if (!it.contains(entity.id)) it += entity.id

                return
            }
        }

        val newTable: Table = mutableListOf(entity.id)
        tables += newTable
    }

    /**
     * Removes an Entity from its associated table.
     */
    fun removeFromTable(entity: Entity) {
        tables.map {
            val e = getEntity(it[0])

            if (entity.type == e.type) {
                if (it.contains(entity.id)) {
                    it -= entity.id
                }
                if (it.isEmpty()) {
                    tables.remove(it)
                }
                return
            }
        }
    }

    fun printTables() {
        println("Tables: ")
        tables.map { table ->
            val tableType = getEntity(table[0]).type

            println("Table Type: ")
            tableType.map {
                val element = getElement(it)
                print("$element, ")
            }

            println("\nEntities in table: ")
            table.map { entity ->
                val entityName = getEntity(entity).name
                print("$entityName, ")
            }

            println("\n------------------")
        }
    }
}