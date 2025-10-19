package colosseum.utility;

import org.bukkit.Material;
import org.bukkit.block.Block;

@SuppressWarnings("deprecation")
public class BlockData {
    private final Block block;
    private final Material material;
    private final byte data;

    public BlockData(Block block) {
        this.block = block;
        this.material = block.getType();
        this.data = block.getData();
    }

    public void restore() {
        block.setType(material);
        block.setData(data);
    }

    public void restore(boolean applyPhysics) {
        block.setType(material);
        block.setData(data, applyPhysics);
    }

    public Block getBlock() {
        return block;
    }

    public Material getMaterial() {
        return material;
    }

    public byte getData() {
        return data;
    }
}
