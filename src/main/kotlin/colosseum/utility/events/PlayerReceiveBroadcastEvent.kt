package colosseum.utility.events

import org.bukkit.entity.Player
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class PlayerReceiveBroadcastEvent(val player: Player, val message: String) : Event(), Cancellable {
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

    override fun getHandlers(): HandlerList {
        return handlerList
    }
}