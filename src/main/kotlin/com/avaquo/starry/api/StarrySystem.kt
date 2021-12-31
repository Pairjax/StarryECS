package com.avaquo.starry.api

import com.avaquo.starry.systems.System

class StarrySystem(
    starryWorld: StarryWorld,

    name: String,
    starryQuery: StarryQuery,
    callback: () -> Unit
) {
    val world = starryWorld.world
    val system = world.createSystem(name, starryQuery.query, callback)

}