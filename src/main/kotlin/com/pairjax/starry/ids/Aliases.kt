package com.pairjax.starry.ids

import com.google.common.primitives.UnsignedInts
import com.pairjax.starry.entities.Entity
import com.pairjax.starry.entities.Relationship
import kotlin.Pair

/**
 * Aliases allow us to follow the naming conventions of an ECS
 * while not making pointless shells for already-existing datatypes
 */
public typealias Component = Any

public typealias Type = MutableList<ID>

public typealias ID = ULong

public typealias Table = MutableList<Entity>

public typealias Pair = Pair<Relationship, Entity>
