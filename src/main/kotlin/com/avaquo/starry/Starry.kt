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

}