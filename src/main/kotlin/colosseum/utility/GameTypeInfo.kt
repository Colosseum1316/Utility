package colosseum.utility

import colosseum.utility.arcade.GameType

data class GameTypeInfo(val gameType: GameType, val info: MutableList<String>) {
    fun addInfo(info: String) {
        this.info.add(info)
    }

    fun removeInfo(index: Int) {
        info.removeAt(index)
    }
}
