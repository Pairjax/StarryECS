package com.avaquo.starry.systems

import com.avaquo.starry.entities.Entity
import com.avaquo.starry.ids.ID

class Phase(
    name: String,
    id: ID,
    val systems: List<ID>,
    var isActive: Boolean
): Entity(name, id)