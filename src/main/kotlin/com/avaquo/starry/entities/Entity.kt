package com.avaquo.starry.entities

import com.avaquo.starry.ids.ID
import com.avaquo.starry.ids.IDs
import com.avaquo.starry.ids.Type

/**
 * Starry Entities are capable of storing any ID, but also have their own ID
 * to identify them in the world.
 */
class Entity(public val name: String) {
    var type: Type = mutableListOf()
    var ids: IDs = mutableListOf()
}