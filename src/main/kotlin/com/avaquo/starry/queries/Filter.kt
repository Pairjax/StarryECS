package com.avaquo.starry.queries

import com.avaquo.starry.ids.IDs
import com.avaquo.starry.ids.Table
import com.avaquo.starry.world.Store

open class Filter(
    private val store: Store,
    private val terms: MutableList<Term>
) {
    fun search(): IDs {
        val foundEntities: IDs = mutableListOf()

        store.tables.map { table ->
            if (store.getEntity(table[0]).type.containsAll(terms.map { it.idType })) {
                foundEntities += table

                terms.map { term ->
                    if (term.parameter != null
                        && term.parameter::class.simpleName == store.getElement(term.idType)) {
                        foundEntities -= trimEntities(term, table)
                    }
                }
            }
        }

        return foundEntities
    }

    private fun trimEntities(term: Term, table: Table): IDs {
        val trimEntities: IDs = mutableListOf()

        table.map { e ->
            if (isInvalidParameter(store.getEntity(e).ids, term.parameter!!)) trimEntities += e
        }

        return trimEntities
    }

    private fun isInvalidParameter(elements: IDs, parameter: Any): Boolean {
        elements.map {
            if (store.getElement(it) == parameter) return false
        }

        return true
    }
}