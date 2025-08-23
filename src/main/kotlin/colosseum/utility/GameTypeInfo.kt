package colosseum.utility

import colosseum.utility.arcade.GameType
import org.bukkit.entity.Player
import java.util.function.*

@JvmRecord
data class GameTypeInfo(val gameType: GameType, val info: MutableList<String>) {
    fun addInfo(info: String) {
        this.info.add(info)
    }

    fun removeInfo(index: Int) {
        info.removeAt(index)
    }

    fun sendInfo(player: Player) {
        UtilPlayerBase.sendMessage(player, String.format("&e%s&r", gameType.getName()))
        info.forEach(Consumer { v: String -> UtilPlayerBase.sendMessage(player, v) })
    }
}
