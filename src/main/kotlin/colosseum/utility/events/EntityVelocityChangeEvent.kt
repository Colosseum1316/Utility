package colosseum.utility.events

import org.bukkit.entity.Entity
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList
import org.bukkit.util.Vector

/**
 * Call this event before actually changing an entity's velocity.
 */
class EntityVelocityChangeEvent(val entity: Entity, var velocity: Vector) : Event(), Cancellable {
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