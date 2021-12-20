package com.avaquo.starry.ids

import com.avaquo.starry.entities.Entity
import com.avaquo.starry.entities.Relationship
import java.util.*
import kotlin.Pair

/**
 * Aliases allow us to follow the naming conventions of an ECS
 * while not making pointless shells for already-existing datatypes
 */
typealias Element = Any // Any element that can be associated with an ID

typealias ID = ULong // A unique identifier for anything that can be put into a World.

typealias IDs = MutableList<ID> // A generic list of IDs

typealias Type = IDs // A list of IDs which define an entity's components.

typealias Table = MutableList<ID> // A list of Entities with the same Type

typealias Pair = Pair<Relationship, Entity>
