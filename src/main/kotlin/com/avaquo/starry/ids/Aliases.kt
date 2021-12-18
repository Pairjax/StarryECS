package com.avaquo.starry.ids

import com.google.common.primitives.UnsignedInts
import com.avaquo.starry.entities.Entity
import com.avaquo.starry.entities.Relationship
import kotlin.Pair

/**
 * Aliases allow us to follow the naming conventions of an ECS
 * while not making pointless shells for already-existing datatypes
 */
public typealias Component = Any

public typealias Type = MutableList<ID>

public typealias IDs = MutableList<ID>

public typealias ID = ULong

public typealias Table = MutableList<Entity>

public typealias Pair = Pair<Relationship, Entity>
