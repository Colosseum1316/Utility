package colosseum.utility.events

import org.bukkit.event.Event
import org.bukkit.event.HandlerList
import org.bukkit.plugin.java.JavaPlugin

class ServerShutdownEvent(val plugin: JavaPlugin) : Event() {
    companion object {
        @JvmStatic
        val handlerList: HandlerList = HandlerList()
    }

    override fun getHandlers(): HandlerList {
        return handlerList
    }
}