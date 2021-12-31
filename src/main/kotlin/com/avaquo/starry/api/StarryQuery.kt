package com.avaquo.starry.api

import com.avaquo.starry.queries.Filter
import com.avaquo.starry.queries.Query
import com.avaquo.starry.queries.Term

class StarryQuery(starryWorld: StarryWorld) {
    private val storage = starryWorld.world.storage

    val query = Query(storage, Filter(storage, mutableListOf()))

    fun addTerm(elementName: String, contents: Any?) {
        val id = storage.typeMap[elementName] ?: throw Exception()

        query.filter.terms += Term(id, contents)
    }

    fun addTerms(terms: List<Pair<String, Any?>>) {
        for (term in terms) addTerm(term.first, term.second)
    }

    fun removeTerm(elementName: String, contents: Any?) {
        val id = storage.typeMap[elementName] ?: throw Exception()

        query.filter.terms -= Term(id, contents)
    }

    fun removeTerms(terms: List<Pair<String, Any?>>) {
        for (term in terms) removeTerm(term.first, term.second)
    }

}
