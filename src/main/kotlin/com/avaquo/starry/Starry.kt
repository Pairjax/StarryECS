package com.avaquo.starry

import com.avaquo.starry.example.ExampleComponent
import com.avaquo.starry.queries.Filter
import com.avaquo.starry.queries.Query
import com.avaquo.starry.queries.Term
import com.avaquo.starry.world.World
import com.avaquo.starry.systems.System

var world: World = World("Core", true)

@Suppress("unused")

// This code runs as soon as Minecraft is in a mod-load-ready state.
// However, some things (like resources) may still be uninitialized.
fun init() {
    val entity = world.createEntity("TestEntity")
    world.addElementToEntity(entity.id, ExampleComponent(0, 1))

    val term = Term(world.storage.getType(ExampleComponent::class.simpleName as String))
    val query = Query(world.storage, Filter(world.storage, mutableListOf(term)))
    val system = System("Name", 0u, query) {
        print("Hi!")

        fun test() {
            query.cache.toString()
        }
    }
}