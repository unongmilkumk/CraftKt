package com.unongmilk.crafting.recipe

import org.bukkit.inventory.ItemStack
import java.util.*

/**
 * 레시피의 토대가 되는 클래스
 */
class Recipes(var name : String, var ingredient : ArrayList<ItemStack>, var result : ItemStack, var minlevel : Int, var percentage : Int)

/**
 * 제작법 추가를 위해 간추려놓은 클래스
 */
class RecipesAdd(var name : String, var minlevel : Int, var percentage : Int)

/**
 * UUID 에 선택된 레시피 보내기
 */
val selectedRecipe = HashMap<UUID, Recipes?>()

/**
 * 제작대 모음
 */
val recipes = ArrayList<Recipes>()

/**
 * UUID 가 원하는 제작대 추가 약간만 저장
 */
val addRecipes = HashMap<UUID, RecipesAdd?>()