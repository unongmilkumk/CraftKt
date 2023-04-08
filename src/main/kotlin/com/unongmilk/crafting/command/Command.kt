package com.unongmilk.crafting.command

import com.unongmilk.crafting.gui.openAddInventory
import com.unongmilk.crafting.gui.openCraftingInventory
import com.unongmilk.crafting.recipe.RecipesAdd
import com.unongmilk.crafting.recipe.addRecipes
import com.unongmilk.crafting.recipe.recipes
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object Command : CommandExecutor{
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>) : Boolean {
        val p = sender as Player
        if (args.isEmpty()) {
            p.openCraftingInventory(1)
        } else when (args[0]) {
            "추가" -> {
                /**
                 * 조건이 안되면 탈락
                 * 조건 리스트
                 * - 플레이어가 OP
                 * - 커맨드의 꼬랑지가 4개 이상
                 * - 이 이름이 처음 쓰는 이름
                 * - 꼬랑지 3과 4가 숫자
                 */
                if (!p.isOp) return false
                if (args.size <= 3) return false
                var d = false
                recipes.forEach {
                    if (it.name == args[1]) d = true
                }
                if (d) return false
                val a = args[2].toIntOrNull() ?: return false
                val b = args[3].toIntOrNull() ?: return false
                /**
                 * addRecipes 에 약간 모자른 것들 추가해주기
                 */
                val c = RecipesAdd(args[1], a, b)
                addRecipes[p.uniqueId] = c
                p.openAddInventory()
            }
            "설정" -> {
                /**
                 * 조건이 안되면 탈락
                 * 조건 리스트
                 * - 플레이어가 OP
                 * - 꼬랑지가 4개 이상
                 * - 꼬랑지 4가 숫자
                 */
                if (!p.isOp) return false
                if (args.size <= 3) return false
                val b = args[3].toIntOrNull() ?: return false
                when (args[1]) {
                    "최소레벨" -> {
                        recipes.forEach {
                            if (it.name == args[2]) {
                                it.minlevel = b
                            }
                        }
                    }
                    "확률" -> {
                        recipes.forEach {
                            if (it.name == args[2]) {
                                it.percentage = b
                            }
                        }
                    }
                }
            }
        }
        return true
    }
}