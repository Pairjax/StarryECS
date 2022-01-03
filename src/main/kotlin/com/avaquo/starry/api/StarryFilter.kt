package com.avaquo.starry.api

import com.avaquo.starry.queries.Filter
import com.avaquo.starry.queries.Query
import com.avaquo.starry.queries.Term

class StarryFilter(private val starryWorld: StarryWorld) {
    private val storage = starryWorld.world.storage

    val filter = Filter(storage, mutableListOf())

    fun addTerm(elementName: String, contents: Any?) {
        val id = storage.typeMap[elementName] ?: throw Exception()

        filter.terms += Term(id, contents)
    }

    fun addTerms(terms: List<Pair<String, Any?>>) {
        for (term in terms) addTerm(term.first, term.second)
    }

    fun removeTerm(elementName: String, contents: Any?) {
        val id = storage.typeMap[elementName] ?: throw Exception()

        filter.terms -= Term(id, contents)
    }

    fun removeTerms(terms: List<Pair<String, Any?>>) {
        for (term in terms) removeTerm(term.first, term.second)
    }

    fun search(): List<StarryEntity> {
        val cache: MutableList<StarryEntity> = mutableListOf()

        for (entity in filter.search()) {
            val e = storage.getEntity(entity)
            cache += StarryEntity(starryWorld, e.name, listOf(), e)
        }

        return cache
    }
}