package com.avaquo.starry.ids

import com.avaquo.starry.entities.Entity
import com.avaquo.starry.entities.Relationship
import kotlin.Pair

/**
 * Aliases allow us to follow the naming conventions of an ECS
 * while not making pointless shells for already-existing datatypes
 */
typealias Element = Any

typealias Type = MutableList<ID>

typealias IDs = MutableList<ID>

typealias ID = ULong

typealias Table = MutableList<ID>

typealias Pair = Pair<Relationship, Entity>
