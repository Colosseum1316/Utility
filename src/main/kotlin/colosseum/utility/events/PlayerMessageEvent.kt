package colosseum.utility.events

import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

/**
 * Call this event before actually invoking [colosseum.utility.UtilPlayerBase.sendMessage]
 *
 * @see [colosseum.utility.UtilPlayerBase.sendMessage]
 */
class PlayerMessageEvent(val player: Player, var message: String) : Event(), Cancellable {
    companion object {
        @JvmStatic
        val handlerList: HandlerList = HandlerList()
    }

    private var cancelled = false

    override fun isCancelled(): Boolean {
        return cancelled
    }

    override fun setCancelled(cancelled: Boolean) {
        this.cancelled = cancelled
    }

    fun getUnformattedMessage(): String {
        return ChatColor.stripColor(message)
    }

    override fun getHandlers(): HandlerList {
        return handlerList
    }
}