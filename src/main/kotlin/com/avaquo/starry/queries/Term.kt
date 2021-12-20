package com.avaquo.starry.queries

import com.avaquo.starry.ids.ID
import com.avaquo.starry.ids.IDs
import com.avaquo.starry.ids.Table
import com.avaquo.starry.world.Store

class Term(
    private val store: Store,
    private val idType: ID,
    private val parameter: Any?
) {

    fun search(): IDs {
        val foundElements: IDs = mutableListOf()

        store.tables.map {
            if (store.getEntity(it[0]).type.contains(idType)) {
                foundElements += if (parameter != null) parseParameters(getElements(it))
                                 else getElements(it)
            }
        }

        return foundElements
    }

    private fun getElements(entities: Table): IDs {
        val typeElements: IDs = mutableListOf()

        entities.map { entityID ->
            val e = store.getEntity(entityID)

            e.ids.map { elementID ->
                if (store.getElement(elementID)::class.simpleName == store.getElement(idType)) {
                    typeElements += elementID
                }
            }
        }

        return typeElements
    }

    private fun parseParameters(elements: IDs): IDs {
        val validElements: IDs = mutableListOf()

        elements.map { if (store.getElement(it) == parameter) validElements += it }

        return validElements
    }
}