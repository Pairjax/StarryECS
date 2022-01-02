package com.avaquo.starry.world

import com.avaquo.starry.entities.Entity
import com.avaquo.starry.ids.Element
import com.avaquo.starry.ids.ID
import com.avaquo.starry.queries.Query
import com.avaquo.starry.systems.Phase
import com.avaquo.starry.systems.Pipeline
import com.avaquo.starry.systems.System

/**
 * The World is the box that holds all ECS operations; you can have multiple worlds,
 * but they will not interact unless you design specific Systems to interface between
 * them.
 */
class World(name: String, debug: Boolean) {
    val debug: Boolean = true
    val storage: Store = Store(this)
    private val pipeline: Pipeline = Pipeline(storage, mutableListOf())

    init {
        if (debug) println("[DEBUG] Loading World $name")
    }

    //region ENTITIES
    fun createEntity(name: String): Entity {
        val e = Entity(name, storage.getNextID())

        storage.addElement(e, e.id)
        storage.addToTable(e)

        return e
    }

    fun destroyEntity(id: ID) {
        val e = storage.getEntity(id)

        for (elementId in e.ids) storage.removeElement(elementId)

        storage.removeFromTable(e)
        storage.removeElement(e.id)
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
        e.type += storage.getType(element::class.simpleName!!)

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
    fun createSystem(name: String, query: Query, callback: () -> Unit): System {
        val s = System(name, storage.getNextID(), query, callback)

        storage.addElement(s)
        storage.addToTable(s)

        return s
    }

    fun addPipelinePhase(phase: Phase) {
        pipeline.phases += phase
    }

    fun removePipelinePhase(phase: Phase) {
        pipeline.phases -= phase
    }

    fun runPipeline() {
        pipeline.runPhases()
    }
    //endregion
}