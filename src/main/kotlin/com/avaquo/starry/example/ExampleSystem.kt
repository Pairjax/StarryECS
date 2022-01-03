package com.avaquo.starry.example

import com.avaquo.starry.api.StarryQuery
import com.avaquo.starry.entities.Entity
import com.avaquo.starry.ids.ID
import com.avaquo.starry.queries.Query
import com.avaquo.starry.systems.System

/**
 * An example of a system within an ECS world.
 * DO NOT add parameters into these systems except
 * for queries.
 */
class ExampleSystem(
    private val entitiesWithPosition: StarryQuery
) {
    val callback = {
        println("Hello from inside a System!")

        val positionCache = entitiesWithPosition.getCache()

        positionCache.map { e ->
            val pos = e.getElement("Position")
        }
    }
}