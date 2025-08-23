package colosseum.utility

import org.bukkit.block.Block
import org.bukkit.block.BlockFace

object UtilBlockBase {
    @JvmStatic
    fun getSurrounding(block: Block, diagonals: Boolean): ArrayList<Block> {
        val blocks = ArrayList<Block>()

        if (diagonals) {
            for (x in -1..1) {
                for (z in -1..1) {
                    for (y in 1 downTo -1) {
                        if (x == 0 && y == 0 && z == 0) {
                            continue
                        }
                        blocks.add(block.getRelative(x, y, z))
                    }
                }
            }
        } else {
            blocks.add(block.getRelative(BlockFace.UP))
            blocks.add(block.getRelative(BlockFace.NORTH))
            blocks.add(block.getRelative(BlockFace.SOUTH))
            blocks.add(block.getRelative(BlockFace.EAST))
            blocks.add(block.getRelative(BlockFace.WEST))
            blocks.add(block.getRelative(BlockFace.DOWN))
        }

        return blocks
    }
}
