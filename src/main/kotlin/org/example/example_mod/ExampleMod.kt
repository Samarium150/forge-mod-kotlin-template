package org.example.example_mod

import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import thedarkcolour.kotlinforforge.forge.FORGE_BUS
import thedarkcolour.kotlinforforge.forge.MOD_BUS

@Mod(ExampleMod.MOD_ID)
object ExampleMod {
    const val MOD_ID = "example_mod"
    private val LOGGER: Logger = LogManager.getLogger()
    init {
        LOGGER.info("example mod initialization")
        MOD_BUS.addListener(::onClientSetup)
        FORGE_BUS.addListener(::onServerAboutToStart)
    }

    private fun onClientSetup(event: FMLClientSetupEvent) {
        LOGGER.info("client setup")
    }

    private fun onServerAboutToStart(event: FMLServerAboutToStartEvent) {
        LOGGER.info("server about to start")
    }

}
