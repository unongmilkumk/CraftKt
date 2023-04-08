package com.unongmilk.crafting.util

import org.bukkit.Material
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

fun makeItem(material : Material, name : String, vararg description : String) : ItemStack {
    return ItemStack(material).setName(name).setDescription(*description).noDamage()
}

fun ItemStack.getDescription(): MutableList<String> {
    return this.itemMeta.lore
}

fun ItemStack.noDamage(): ItemStack {
    val i = this
    val im = i.itemMeta
    im.isUnbreakable = true
    im.addItemFlags(ItemFlag.HIDE_UNBREAKABLE)
    i.itemMeta = im
    return i
}

fun ItemStack.setName(name: String): ItemStack {
    val i = this
    val im = this.itemMeta
    im.displayName = name
    i.itemMeta = im
    return i
}

fun ItemStack.setDescription(vararg description : String) : ItemStack {
    val i = this
    val im = i.itemMeta
    im.lore = description.toMutableList()
    i.itemMeta = im
    return i
}

fun ItemStack.addDescription(vararg description : String) : ItemStack {
    val i = this
    val im = i.itemMeta
    if (im.lore == null) im.lore = description.toMutableList()
    else im.lore.addAll(description)
    i.itemMeta = im
    return i
}

fun ItemStack.addDescription(description : MutableList<String>) : ItemStack {
    val i = this
    val im = i.itemMeta
    if (im.lore == null) im.lore = description
    else im.lore.addAll(description)
    i.itemMeta = im
    return i
}

fun ItemStack.setDescription(description : MutableList<String>) : ItemStack {
    val i = this
    val im = i.itemMeta
    im.lore = description
    i.itemMeta = im
    return i
}

fun ItemStack.setDescription(place: Int, string: String) : ItemStack {
    val i = this
    val l = i.getDescription()
    while (l.size > place) {
        l.add(" ")
    }
    l[place] = string
    return i.setDescription(l)
}

fun getAir() : ItemStack {
    return ItemStack(Material.IRON_AXE, 1, 64.toShort()).noDamage()
}

fun ItemStack.setDamage(damage : Int) : ItemStack {
    this.durability = damage.toShort()
    return this
}

fun ItemStack.getName() : String {
    return if ( this.hasItemMeta() && this.itemMeta.displayName != null) this.itemMeta.displayName else this.type.name
}