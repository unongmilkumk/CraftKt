package com.unongmilk.crafting.gui

import com.unongmilk.crafting.recipe.Recipes
import com.unongmilk.crafting.recipe.addRecipes
import com.unongmilk.crafting.recipe.recipes
import com.unongmilk.crafting.recipe.selectedRecipe
import com.unongmilk.crafting.util.*
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack
import kotlin.collections.set

/**
 * 플레이어의 제작대 여는 함수
 * @param page 여는 제작대의 페이지
 */
fun Player.openCraftingInventory(page : Int) {
    val p = this
    val inventory = makeInventory("제작대 ${page}페이지")
    for (i in (0    until recipes.size)) {
        val a = recipes[i]
        val c = mutableListOf<String>()
        c.add("${ChatColor.GOLD}확률 : ${a.percentage}")
        c.add("${ChatColor.GOLD}최소레벨 : ${a.minlevel}")
        a.ingredient.forEach {
            if (it.getName() != "AIR") c.add("${ChatColor.GOLD}${it.getName()}")
        }
        var b = a.result.setName(a.name).setDescription(c)
        inventory.setItem(i, b)
    }
    p.openInventory(inventory)
}

/**
 * 플레이어의 제작법 선택 창을 여는 함수
 */
fun Player.openSelectInventory() {
    val p = this
    val inventory = makeInventory("이 조합법으로 선택하시겠습니까?").setCheck(30).setRedCheck(32)
    p.openInventory(inventory)
}

/**
 * 플레이어의 제작법 추가 창을 여는 함수
 */
fun Player.openAddInventory() {
    val p = this
    val inventory = makeInventory("제작법 추가").setAir(1, 2, 3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 15, 16, 22).setCheck(31)
    p.openInventory(inventory)
}


object GUI : Listener {
    @EventHandler
    fun onClick(event : InventoryClickEvent) {
        val player = event.whoClicked as Player
        if (event.clickedInventory == null || event.clickedInventory.title == null) {
            player.sendMessage("1")
            return
        }
        val inv = event.clickedInventory
        when (inv.title) {
            "이 조합법으로 선택하시겠습니까?" -> {
                event.isCancelled = true
                if (event.slot == 30) {
                    /**
                     * 조건에 충족하지 못하면 끝내기
                     * a = 제작 레시피
                     * b = 아이템이 없다면 false
                     * 조건 리스트
                     * a가 존재
                     * b가 true
                     * 플레이어 레벨이 최소레벨보다 높음
                     * 확률에 도달
                     */
                    val a = selectedRecipe[player.uniqueId] ?: return
                    var b = true
                    a.ingredient.forEach {
                        if (b) b = player.inventory.contains(it)
                    }
                    if (!b) return
                    if (player.getLevel() < a.minlevel) return
                    if (!isSucceed(a.percentage)) return
                    /**
                     * 재료를 회수하기
                     */
                    a.ingredient.forEach {
                        player.inventory.removeItem(it)
                    }
                    /**
                     * 결과 아이템을 주기
                     */
                    player.inventory.addItem(a.result)
                    /**
                     * 제작하는 레시피를 다시 null 값으로 만들기
                     */
                    selectedRecipe[player.uniqueId] = null
                } else if (event.slot == 32) {
                    player.closeInventory()
                }
            }
            "제작법 추가" -> {
                val b = arrayListOf(1, 2, 3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 15, 16)
                if (event.slot == 31) {
                    event.isCancelled = true
                    /**
                     * 만약에 a 값이 null 이라면 처리하기
                     * a = 제작 레시피
                     */
                    val a = addRecipes[player.uniqueId] ?: return

                    /**
                     * 제작 레시피 추가하기
                     * c = 재료
                     * d = 결괏값
                     * recipe = 추가할 레시피
                     */
                    val c = arrayListOf<ItemStack>()
                    b.forEach {
                        c.add(event.clickedInventory.getItem(it) ?: ItemStack(Material.AIR))
                    }
                    val d = event.clickedInventory.getItem(22) ?: ItemStack(Material.AIR)
                    val recipe = Recipes(a.name, c, d, a.minlevel, a.percentage)
                    recipes.add(recipe)
                    /**
                     * 창 닫기
                     */
                    player.closeInventory()
                } else if (!b.contains(event.slot) && event.slot != 22) {
                    /**
                     * 빈칸은 못 움직이게 하기
                     */
                    event.isCancelled = true
                }
            }
        }
        if (inv.title.startsWith("제작대 ")) {
            event.isCancelled = true
            /**
             * 맞는 제작법을 선택해서 선택창으로 보내기
             * page = 인벤토리에 페이지 확인하기
             * a = 페이지를 가지고 정확한 제작법의 주소 찾기
             * b = a를 가지고 제작법 찾기
             */
            val page = inv.title.split("제작대 ")[0].toIntOrNull() ?: return
            val a = ((page - 1) * 54) + event.slot
            val b = recipes[a]
            selectedRecipe[player.uniqueId] = b
            player.openSelectInventory()
        }
    }
}
