package com.avaquo.starry.api

data class StarryPhase(
    val name: String,
    val systems: MutableList<StarrySystem>,
    val isActive: Boolean
)