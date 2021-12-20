package com.avaquo.starry.example

import com.avaquo.starry.entities.Entity
import com.avaquo.starry.ids.ID
import com.avaquo.starry.queries.Query
import com.avaquo.starry.systems.System

class ExampleSystem(
    name: String,
    id: ID,
    query: Query,
    callback: () -> Unit = {
        query.cache.toString()
    }
): System(name, id, query, callback)