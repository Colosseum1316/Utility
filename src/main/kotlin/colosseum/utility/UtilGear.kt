package colosseum.utility

import org.bukkit.Material
import org.bukkit.inventory.ItemStack

object UtilGear {
    private val scytheSet = HashSet<Material>()
    private val axeSet = HashSet<Material>()
    private val swordSet = HashSet<Material>()
    private val maulSet = HashSet<Material>()
    private val pickSet = HashSet<Material>()
    private val diamondSet = HashSet<Material>()
    private val goldSet = HashSet<Material>()
    private val armorSet = HashSet<Material>()

    @JvmStatic
    fun isArmor(item: ItemStack?): Boolean {
        if (item == null) {
            return false
        }

        if (armorSet.isEmpty()) {
            armorSet.add(Material.LEATHER_HELMET)
            armorSet.add(Material.LEATHER_CHESTPLATE)
            armorSet.add(Material.LEATHER_LEGGINGS)
            armorSet.add(Material.LEATHER_BOOTS)

            armorSet.add(Material.GOLD_HELMET)
            armorSet.add(Material.GOLD_CHESTPLATE)
            armorSet.add(Material.GOLD_LEGGINGS)
            armorSet.add(Material.GOLD_BOOTS)

            armorSet.add(Material.CHAINMAIL_HELMET)
            armorSet.add(Material.CHAINMAIL_CHESTPLATE)
            armorSet.add(Material.CHAINMAIL_LEGGINGS)
            armorSet.add(Material.CHAINMAIL_BOOTS)

            armorSet.add(Material.IRON_HELMET)
            armorSet.add(Material.IRON_CHESTPLATE)
            armorSet.add(Material.IRON_LEGGINGS)
            armorSet.add(Material.IRON_BOOTS)

            armorSet.add(Material.DIAMOND_HELMET)
            armorSet.add(Material.DIAMOND_CHESTPLATE)
            armorSet.add(Material.DIAMOND_LEGGINGS)
            armorSet.add(Material.DIAMOND_BOOTS)
        }

        return armorSet.contains(item.type)
    }

    @JvmStatic
    fun isAxe(item: ItemStack?): Boolean {
        if (item == null) {
            return false
        }

        if (axeSet.isEmpty()) {
            axeSet.add(Material.WOOD_AXE)
            axeSet.add(Material.STONE_AXE)
            axeSet.add(Material.IRON_AXE)
            axeSet.add(Material.GOLD_AXE)
            axeSet.add(Material.DIAMOND_AXE)
        }

        return axeSet.contains(item.type)
    }

    @JvmStatic
    fun isSword(item: ItemStack?): Boolean {
        if (item == null) {
            return false
        }

        if (swordSet.isEmpty()) {
            swordSet.add(Material.WOOD_SWORD)
            swordSet.add(Material.STONE_SWORD)
            swordSet.add(Material.IRON_SWORD)
            swordSet.add(Material.GOLD_SWORD)
            swordSet.add(Material.DIAMOND_SWORD)
        }

        return swordSet.contains(item.type)
    }

    @JvmStatic
    fun isShovel(item: ItemStack?): Boolean {
        if (item == null) {
            return false
        }

        if (maulSet.isEmpty()) {
            maulSet.add(Material.WOOD_SPADE)
            maulSet.add(Material.STONE_SPADE)
            maulSet.add(Material.IRON_SPADE)
            maulSet.add(Material.GOLD_SPADE)
            maulSet.add(Material.DIAMOND_SPADE)
        }

        return maulSet.contains(item.type)
    }

    @JvmStatic
    fun isHoe(item: ItemStack?): Boolean {
        if (item == null) {
            return false
        }

        if (scytheSet.isEmpty()) {
            scytheSet.add(Material.WOOD_HOE)
            scytheSet.add(Material.STONE_HOE)
            scytheSet.add(Material.IRON_HOE)
            scytheSet.add(Material.GOLD_HOE)
            scytheSet.add(Material.DIAMOND_HOE)
        }

        return scytheSet.contains(item.type)
    }

    @JvmStatic
    fun isPickaxe(item: ItemStack?): Boolean {
        if (item == null) {
            return false
        }

        if (pickSet.isEmpty()) {
            pickSet.add(Material.WOOD_PICKAXE)
            pickSet.add(Material.STONE_PICKAXE)
            pickSet.add(Material.IRON_PICKAXE)
            pickSet.add(Material.GOLD_PICKAXE)
            pickSet.add(Material.DIAMOND_PICKAXE)
        }

        return pickSet.contains(item.type)
    }

    @JvmStatic
    fun isDiamond(item: ItemStack?): Boolean {
        if (item == null) {
            return false
        }

        if (diamondSet.isEmpty()) {
            diamondSet.add(Material.DIAMOND_SWORD)
            diamondSet.add(Material.DIAMOND_AXE)
            diamondSet.add(Material.DIAMOND_SPADE)
            diamondSet.add(Material.DIAMOND_HOE)
            diamondSet.add(Material.DIAMOND_PICKAXE)
            diamondSet.add(Material.DIAMOND_HELMET)
            diamondSet.add(Material.DIAMOND_CHESTPLATE)
            diamondSet.add(Material.DIAMOND_LEGGINGS)
            diamondSet.add(Material.DIAMOND_BOOTS)
        }

        return diamondSet.contains(item.type)
    }

    @JvmStatic
    fun isGold(item: ItemStack?): Boolean {
        if (item == null) {
            return false
        }

        if (goldSet.isEmpty()) {
            goldSet.add(Material.GOLD_SWORD)
            goldSet.add(Material.GOLD_AXE)
            goldSet.add(Material.GOLD_SPADE)
            goldSet.add(Material.GOLD_HOE)
            goldSet.add(Material.GOLD_PICKAXE)
            goldSet.add(Material.GOLD_HELMET)
            goldSet.add(Material.GOLD_CHESTPLATE)
            goldSet.add(Material.GOLD_LEGGINGS)
            goldSet.add(Material.GOLD_BOOTS)
        }

        return goldSet.contains(item.type)
    }

    @JvmStatic
    fun isBow(item: ItemStack?): Boolean {
        if (item == null) {
            return false
        }
        return item.type == Material.BOW
    }

    @JvmStatic
    fun isWeapon(item: ItemStack?): Boolean {
        return isAxe(item) || isSword(item)
    }

    @JvmStatic
    fun isMaterial(item: ItemStack?, mat: Material): Boolean {
        if (item == null) {
            return false
        }
        return item.type == mat
    }

    @JvmStatic
    fun isMatAndData(item: ItemStack?, mat: Material, data: Byte): Boolean {
        if (item == null) {
            return false
        }
        return item.type == mat && item.data.data == data
    }

    @JvmStatic
    fun isRepairable(item: ItemStack): Boolean {
        return (item.type.maxDurability > 0)
    }
}
