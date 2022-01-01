package com.avaquo.starry.api

import com.avaquo.starry.entities.Entity
import com.avaquo.starry.ids.Element
import com.avaquo.starry.ids.ID

class StarryEntity(
    starryWorld: StarryWorld,

    val name: String,
    elements: List<Element> = listOf()
) {
    private val world = starryWorld.world

    val internalEntity: Entity = world.createEntity(name)

    init { addElements(elements) }

    fun getElement(elementName: String): Element {
        for (id in internalEntity.ids) {
            val element = world.storage.getElement(id)
            if (element::class.simpleName == elementName) return element
        }

        return false
    }

    fun addElement(element: Element) {
        // Checking if it is a primitive type (which is not allowed)
        if (element::class.javaPrimitiveType != null) throw Exception()

        for (id in internalEntity.ids) {
            if (world.storage.getElement(id)::class == element::class) throw Exception()
        }

        world.addElementToEntity(internalEntity.id, element)
    }

    fun addElements(newElements: List<Element>) {
        for (element in newElements) addElement(element)
    }

    fun removeElement(elementName: String) {
        world.removeElementFromEntity(internalEntity.id, getElementID(elementName))
    }

    fun removeElements(elementsName: List<String>) {
        for (element in elementsName) removeElement(element)
    }

    fun destroy() { world.destroyEntity(internalEntity.id) }

    private fun getElementID(name: String): ID {
        for (id in internalEntity.ids) {
            if (world.storage.getElement(id)::class.simpleName == name) return id
        }

        throw Exception()
    }
}