package com.avaquo.starry.queries

import com.avaquo.starry.ids.ID
import com.avaquo.starry.ids.IDs
import com.avaquo.starry.ids.Table
import com.avaquo.starry.world.Store

/**
 * A search for a single type of Element.
 * @param idType The type of Element being searched for
 * @param parameter (Optional) Specifies the data inside elements.
 */
data class Term(
    val idType: ID,
    val parameter: Any? = null
)