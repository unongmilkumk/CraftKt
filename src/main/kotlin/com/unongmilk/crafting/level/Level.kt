package com.unongmilk.crafting.level

import org.bukkit.entity.Player
import java.util.*

val levelh = HashMap<UUID, Int>()
val experienceh = HashMap<UUID, Int>()

@Suppress("EXTENSION_SHADOWED_BY_MEMBER")
fun Player.getExp() : Int {
    val p = this
    return experienceh[p.uniqueId] ?: 0
}

@Suppress("EXTENSION_SHADOWED_BY_MEMBER")
fun Player.setExp(exp : Int) {
    val p = this
    experienceh[p.uniqueId] = exp
}

fun Player.addExp(exp : Int) {
    val p = this
    experienceh[p.uniqueId] = p.getLevel() + exp
    if ((p.getLevel() * 2) == p.getExp().toInt()) {
        p.setExp(0)
        p.addLevel(1)
    }
}

@Suppress("EXTENSION_SHADOWED_BY_MEMBER")
fun Player.getLevel() : Int {
    val p = this
    return levelh[p.uniqueId] ?: 0
}

@Suppress("EXTENSION_SHADOWED_BY_MEMBER")
fun Player.setLevel(level : Int) {
    val p = this
    levelh[p.uniqueId] = level
}

fun Player.addLevel(level : Int) {
    val p = this
    levelh[p.uniqueId] = p.getLevel() + level
}
