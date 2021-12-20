package com.avaquo.starry.systems

import com.avaquo.starry.ids.ID
import com.avaquo.starry.world.Store
import com.avaquo.starry.systems.Phase

class Pipeline(
    private val storage: Store,
    var phases: MutableList<Phase>
) {
    fun runPhases() {
        phases.map { phase ->
            runPhase(phase)
        }
    }

    private fun runPhase(phase: Phase) {
        phase.systems.map { systemId ->
            if (!phase.isActive) return

            val system = storage.getElement(systemId) as System
            system.callback.invoke()
        }
    }
}