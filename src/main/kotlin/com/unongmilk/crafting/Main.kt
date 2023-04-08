package com.unongmilk.crafting

import com.unongmilk.crafting.command.Command
import com.unongmilk.crafting.gui.GUI
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.plugin.java.JavaPlugin


class Main : JavaPlugin() {
    companion object {
        var config : FileConfiguration? = null
    }
    override fun onEnable() {
        getCommand("제작")!!.executor = Command
        logger.info("서버가 열렸습니다")

        server.pluginManager.registerEvents(GUI, this)
    }

    override fun onDisable() {
        logger.info("서버가 닫혔습니다")
    }
}