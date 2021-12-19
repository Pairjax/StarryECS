package com.avaquo.starry

import com.avaquo.starry.world.World

var world: World = World("Core", true)

@Suppress("unused")

// This code runs as soon as Minecraft is in a mod-load-ready state.
// However, some things (like resources) may still be uninitialized.
fun init() {
    world.createEntity("TestEntity")
}