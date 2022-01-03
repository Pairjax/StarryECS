package com.avaquo.starry.systems

import com.avaquo.starry.entities.Entity
import com.avaquo.starry.ids.ID
import com.avaquo.starry.queries.Query

open class System(
    name: String,
    id: ID,
    val queries: MutableList<Query>,
    var callback: () -> Unit
): Entity(name, id)