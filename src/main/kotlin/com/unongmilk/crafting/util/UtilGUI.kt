package com.unongmilk.crafting.util

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

fun makeInventory(title: String): Inventory {
    val inv = Bukkit.createInventory(null, 54, title)
    for (i in 0..53) {
        inv.setItem(i, ItemStack(Material.THIN_GLASS))
    }
    return inv
}

fun Inventory.set(slot: Int, itemStack: ItemStack) : Inventory {
    this.setItem(slot ,itemStack)
    return this
}

fun Inventory.set(slot: Int, material: Material) : Inventory {
    this.setItem(slot, ItemStack(material))
    return this
}

@Suppress("DEPRECATION")
fun Inventory.setCheck(slot: Int) : Inventory {
    this.setItem(slot, ItemStack(Material.WOOL, 1, 0,5))
    return this
}

@Suppress("DEPRECATION")
fun Inventory.setRedCheck(slot: Int) : Inventory {
    this.setItem(slot, ItemStack(Material.WOOL, 1, 0, 14))
    return this
}

fun Inventory.setAir(vararg slot: Int) : Inventory {
    slot.forEach {
        this.setItem(it, ItemStack(Material.AIR))
    }
    return this
}