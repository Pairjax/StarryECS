package com.pairjax.starry

import com.pairjax.starry.world.World

var world: World = World("Core")

@Suppress("unused")

// This code runs as soon as Minecraft is in a mod-load-ready state.
// However, some things (like resources) may still be uninitialized.
fun init() {

}