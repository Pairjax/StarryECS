package com.avaquo.starry.api

import com.avaquo.starry.systems.Phase

class StarryPipeline(
    starryWorld: StarryWorld,

    val name: String,
    val phases: MutableList<StarryPhase>
) {
    init {
        for (phase in phases) {
            starryWorld.world.addPipelinePhase(buildPhase(phase))
        }
    }

    fun buildPhase(phase: StarryPhase): Phase {


        return Phase(phase.name, mutableListOf(), phase.isActive)
    }
}