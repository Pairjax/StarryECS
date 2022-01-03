package com.avaquo.starry.api

import com.avaquo.starry.queries.Query
import com.avaquo.starry.systems.System

/**
 * A System is a piece of code which modifies data within the ECS world.
 */
class StarrySystem(
    starryWorld: StarryWorld,

    private val name: String,
    private val starryQueries: MutableList<StarryQuery>,
    private val callback: () -> Unit
) {
    private val world = starryWorld.world
    val system = world.createSystem(name, starryQueries.map { it.query } as MutableList<Query>, callback)
}