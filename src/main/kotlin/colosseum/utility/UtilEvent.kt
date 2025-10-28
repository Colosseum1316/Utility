package colosseum.utility

import org.bukkit.entity.Arrow
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Projectile
import org.bukkit.event.block.Action
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.player.PlayerInteractEvent

@Suppress("deprecation", "unused")
object UtilEvent {
    @JvmStatic
    fun isAction(event: PlayerInteractEvent, actionType: ActionType): Boolean {
        return isAction(event.action, actionType)
    }
    
    @JvmStatic
    fun isAction(action: Action, actionType: ActionType): Boolean {
        return when (actionType) {
            ActionType.L -> action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK
            ActionType.L_AIR -> action == Action.LEFT_CLICK_AIR
            ActionType.L_BLOCK -> action == Action.LEFT_CLICK_BLOCK
            ActionType.R -> action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK
            ActionType.R_AIR -> action == Action.RIGHT_CLICK_AIR
            ActionType.R_BLOCK -> action == Action.RIGHT_CLICK_BLOCK
            ActionType.ANY -> action != Action.PHYSICAL
        }
    }

    @JvmStatic
    fun isBowDamage(event: EntityDamageEvent): Boolean {
        if (event !is EntityDamageByEntityEvent) {
            return false
        }
        return event.damager is Arrow
    }

    /**
     * Damager: The entity that imposes health damage.
     */
    @JvmStatic
    fun getDamagerEntity(event: EntityDamageEvent, ranged: Boolean): LivingEntity? {
        if (event !is EntityDamageByEntityEvent) {
            return null
        }

        //Get Damager
        if (event.damager is LivingEntity) {
            return event.damager as LivingEntity
        }

        if (!ranged) {
            return null
        }

        if (event.damager !is Projectile) {
            return null
        }

        val projectile = event.damager as Projectile

        if (projectile.shooter == null) {
            return null
        }
        if (projectile.shooter !is LivingEntity) {
            return null
        }
        return projectile.shooter as LivingEntity
    }

    enum class ActionType {
        L,
        L_AIR,
        L_BLOCK,
        R,
        R_AIR,
        R_BLOCK,
        ANY
    }
}
