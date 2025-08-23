package colosseum.utility

import colosseum.utility.events.PlayerMessageEvent
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import java.util.*

object UtilPlayerBase {
    @JvmStatic
    fun sendMessage(client: Player, messageList: List<String>) {
        for (curMessage in messageList) {
            sendMessage(client, curMessage)
        }
    }

    @JvmStatic
    fun sendMessage(client: Entity, messageList: List<String>) {
        for (curMessage in messageList) {
            sendMessage(client, curMessage)
        }
    }

    @JvmStatic
    fun sendMessage(recipient: CommandSender, messageList: List<String>) {
        for (curMessage in messageList) {
            sendMessage(recipient, curMessage)
        }
    }

    @JvmStatic
    fun sendMessage(client: Player, message: String) {
        val event = PlayerMessageEvent(client, message)
        Bukkit.getPluginManager().callEvent(event)
        if (event.isCancelled) {
            return
        }
        sendMessage(client as CommandSender, event.message)
    }

    @JvmStatic
    fun sendMessage(client: Entity, message: String) {
        sendMessage(client as CommandSender, message)
    }

    @JvmStatic
    fun sendMessage(recipient: CommandSender, message: String) {
        recipient.sendMessage(ChatColor.translateAlternateColorCodes('&', message))
    }

    @JvmStatic
    fun searchOnline(caller: Player, target: String, inform: Boolean): Player? {
        val matchList = ArrayList<Player>()

        for (cur in Bukkit.getOnlinePlayers()) {
            if (cur.name.equals(target, ignoreCase = true)) {
                return cur
            }

            if (cur.name.lowercase(Locale.getDefault()).contains(target.lowercase(Locale.getDefault()))) {
                matchList.add(cur)
            }
        }

        // No / Non-Unique
        if (matchList.size != 1) {
            if (!inform) {
                return null
            }

            // Inform
            sendMessage(caller, String.format("&e%s&r possible matches for [&e%s&r]:\n&e%s&r", matchList.size, target, matchList.map { it.name }.toList().joinToString("&r, &e")))
            return null
        }

        return matchList[0]
    }
}
