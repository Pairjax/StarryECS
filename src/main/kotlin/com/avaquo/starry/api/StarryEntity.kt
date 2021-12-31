package com.avaquo.starry.api

import com.avaquo.starry.entities.Entity
import com.avaquo.starry.ids.Element
import com.avaquo.starry.ids.ID

class StarryEntity(
    starryWorld: StarryWorld,

    val name: String,
    val elements: MutableList<Element>?
) {
    val world = starryWorld.world

    private val entity: Entity = starryWorld.createInternalEntity(this)

    fun addElement(element: Element) {
        world.addElementToEntity(entity.id, element)
    }

    fun addElements(newElements: List<Element>) {
        for (element in newElements) addElement(element)
    }

    fun removeElement(elementName: String) {
        world.removeElementFromEntity(entity.id, getElementID(elementName))
    }

    fun removeElements(elementsName: List<String>) {
        for (element in elementsName) removeElement(element)
    }

    fun destroy() { world.destroyEntity(entity.id) }

    private fun getElementID(name: String): ID {
        for (id in entity.ids) {
            if (world.storage.getElement(id)::class.simpleName == name) return id
        }

        throw Exception()
    }
}