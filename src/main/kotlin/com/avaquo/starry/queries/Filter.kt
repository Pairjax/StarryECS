package com.avaquo.starry.queries

import com.avaquo.starry.ids.IDs
import com.avaquo.starry.ids.Table
import com.avaquo.starry.world.Store

/**
 * A search for an entity based on specific terms.
 * @param store Reference to the world's storage
 * @param terms The list of Terms used to compare to a given entity.
 */
open class Filter(
    private val store: Store,
    val terms: MutableList<Term>
) {
    /**
     * A robust search tool for finding specific entities in the world based on its individual elements.
     * @return A list of all entities matching the given terms.
     */
    fun search(): IDs {
        val foundEntities: IDs = mutableListOf()

        store.tables.map { table ->
            if (store.getEntity(table[0]).type.containsAll(terms.map { it.idType })) {
                foundEntities += table

                terms.map { term ->
                    if (term.parameter != null) {
                        foundEntities -= trimEntities(term.parameter, table)
                    }
                }
            }
        }

        return foundEntities
    }

    /**
     * Helper function to find non-matching entities.
     * @param parameter The specific data to be compared with.
     * @param table A list of all entities which are being checked
     * @return A list of all entities which do not match the specified parameter.
     */
    private fun trimEntities(parameter: Any, table: Table): IDs {
        val trimEntities: IDs = mutableListOf()

        table.map { e ->
            if (isInvalidParameter(store.getEntity(e).ids, parameter)) trimEntities += e
        }

        return trimEntities
    }

    /** Checks if a single entity contains the desired parameter */
    private fun isInvalidParameter(elements: IDs, parameter: Any): Boolean {
        elements.map {
            if (store.getElement(it) == parameter) return false
        }

        return true
    }
}