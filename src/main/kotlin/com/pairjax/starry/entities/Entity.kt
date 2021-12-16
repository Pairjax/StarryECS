package com.pairjax.starry.entities

import com.pairjax.starry.ids.Element
import com.pairjax.starry.ids.ID
import com.pairjax.starry.ids.Type

/**
 * Starry Entities are capable of storing any ID, but also have their own ID
 * to identify them in the world.
 */
class Entity(public val entityId: ID, public val name: String): Element(entityId) {
    var type: Type = mutableListOf()
    var elements: MutableList<Element> = mutableListOf()
}