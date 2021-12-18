package com.avaquo.starry.entities

import com.avaquo.starry.ids.Element
import com.avaquo.starry.ids.ID
import com.avaquo.starry.ids.Type

/**
 * Starry Entities are capable of storing any ID, but also have their own ID
 * to identify them in the world.
 */
class Entity(public val entityId: ID, public val name: String): Element(entityId) {
    var type: Type = mutableListOf()
    var elements: MutableList<Element> = mutableListOf()
}