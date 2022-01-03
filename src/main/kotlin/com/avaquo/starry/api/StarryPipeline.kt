package com.avaquo.starry.api

import com.avaquo.starry.systems.Phase

class StarryPipeline(
    starryWorld: StarryWorld,

    val name: String,
    phases: MutableList<StarryPhase>
) {
    private val world = starryWorld.world

    init {
        for (phase in phases) {
            starryWorld.world.addPipelinePhase(Phase(phase.name, mutableListOf(), phase.isActive))
        }
    }
}