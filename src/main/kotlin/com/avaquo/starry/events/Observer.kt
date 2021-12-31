package com.avaquo.starry.events

import com.avaquo.starry.ids.ID

class Observer {
    fun isValidEntity(): Boolean {
        return true
    }

    fun conditionMet(condition: ID): Boolean {
        return true
    }
}