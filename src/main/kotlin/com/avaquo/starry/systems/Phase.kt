package com.avaquo.starry.systems

import com.avaquo.starry.entities.Entity
import com.avaquo.starry.ids.ID

class Phase(
    val name: String,
    val systems: List<ID>,
    var isActive: Boolean
)