package colosseum.utility.unittest;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static colosseum.utility.UtilGear.isArmor;
import static colosseum.utility.UtilGear.isAxe;
import static colosseum.utility.UtilGear.isBow;
import static colosseum.utility.UtilGear.isDiamond;
import static colosseum.utility.UtilGear.isGold;
import static colosseum.utility.UtilGear.isHoe;
import static colosseum.utility.UtilGear.isPickaxe;
import static colosseum.utility.UtilGear.isShovel;
import static colosseum.utility.UtilGear.isSword;
import static colosseum.utility.UtilGear.isWeapon;

class TestUtilGear {
    @Test
    void testIsArmor() {
        Assertions.assertFalse(isArmor(null));

        Assertions.assertTrue(isArmor(new ItemStack(Material.LEATHER_HELMET)));
        Assertions.assertTrue(isArmor(new ItemStack(Material.LEATHER_CHESTPLATE)));
        Assertions.assertTrue(isArmor(new ItemStack(Material.LEATHER_LEGGINGS)));
        Assertions.assertTrue(isArmor(new ItemStack(Material.LEATHER_BOOTS)));
        Assertions.assertTrue(isArmor(new ItemStack(Material.IRON_HELMET)));
        Assertions.assertTrue(isArmor(new ItemStack(Material.IRON_CHESTPLATE)));
        Assertions.assertTrue(isArmor(new ItemStack(Material.IRON_LEGGINGS)));
        Assertions.assertTrue(isArmor(new ItemStack(Material.IRON_BOOTS)));
        Assertions.assertTrue(isArmor(new ItemStack(Material.CHAINMAIL_HELMET)));
        Assertions.assertTrue(isArmor(new ItemStack(Material.CHAINMAIL_CHESTPLATE)));
        Assertions.assertTrue(isArmor(new ItemStack(Material.CHAINMAIL_LEGGINGS)));
        Assertions.assertTrue(isArmor(new ItemStack(Material.CHAINMAIL_BOOTS)));
        Assertions.assertTrue(isArmor(new ItemStack(Material.GOLD_HELMET)));
        Assertions.assertTrue(isArmor(new ItemStack(Material.GOLD_CHESTPLATE)));
        Assertions.assertTrue(isArmor(new ItemStack(Material.GOLD_LEGGINGS)));
        Assertions.assertTrue(isArmor(new ItemStack(Material.GOLD_BOOTS)));
        Assertions.assertTrue(isArmor(new ItemStack(Material.DIAMOND_HELMET)));
        Assertions.assertTrue(isArmor(new ItemStack(Material.DIAMOND_CHESTPLATE)));
        Assertions.assertTrue(isArmor(new ItemStack(Material.DIAMOND_LEGGINGS)));
        Assertions.assertTrue(isArmor(new ItemStack(Material.DIAMOND_BOOTS)));
    }

    @Test
    void testIsAxe() {
        Assertions.assertFalse(isAxe(null));

        Assertions.assertTrue(isAxe(new ItemStack(Material.WOOD_AXE)));
        Assertions.assertTrue(isAxe(new ItemStack(Material.STONE_AXE)));
        Assertions.assertTrue(isAxe(new ItemStack(Material.IRON_AXE)));
        Assertions.assertTrue(isAxe(new ItemStack(Material.GOLD_AXE)));
        Assertions.assertTrue(isAxe(new ItemStack(Material.DIAMOND_AXE)));
    }

    @Test
    void testIsSword() {
        Assertions.assertFalse(isSword(null));

        Assertions.assertTrue(isSword(new ItemStack(Material.WOOD_SWORD)));
        Assertions.assertTrue(isSword(new ItemStack(Material.STONE_SWORD)));
        Assertions.assertTrue(isSword(new ItemStack(Material.IRON_SWORD)));
        Assertions.assertTrue(isSword(new ItemStack(Material.GOLD_SWORD)));
        Assertions.assertTrue(isSword(new ItemStack(Material.DIAMOND_SWORD)));
    }

    @Test
    void testIsShovel() {
        Assertions.assertFalse(isShovel(null));

        Assertions.assertTrue(isShovel(new ItemStack(Material.WOOD_SPADE)));
        Assertions.assertTrue(isShovel(new ItemStack(Material.STONE_SPADE)));
        Assertions.assertTrue(isShovel(new ItemStack(Material.IRON_SPADE)));
        Assertions.assertTrue(isShovel(new ItemStack(Material.GOLD_SPADE)));
        Assertions.assertTrue(isShovel(new ItemStack(Material.DIAMOND_SPADE)));
    }

    @Test
    void testIsHoe() {
        Assertions.assertFalse(isHoe(null));

        Assertions.assertTrue(isHoe(new ItemStack(Material.WOOD_HOE)));
        Assertions.assertTrue(isHoe(new ItemStack(Material.STONE_HOE)));
        Assertions.assertTrue(isHoe(new ItemStack(Material.IRON_HOE)));
        Assertions.assertTrue(isHoe(new ItemStack(Material.GOLD_HOE)));
        Assertions.assertTrue(isHoe(new ItemStack(Material.DIAMOND_HOE)));
    }

    @Test
    void testIsPickaxe() {
        Assertions.assertFalse(isPickaxe(null));

        Assertions.assertTrue(isPickaxe(new ItemStack(Material.WOOD_PICKAXE)));
        Assertions.assertTrue(isPickaxe(new ItemStack(Material.STONE_PICKAXE)));
        Assertions.assertTrue(isPickaxe(new ItemStack(Material.IRON_PICKAXE)));
        Assertions.assertTrue(isPickaxe(new ItemStack(Material.GOLD_PICKAXE)));
        Assertions.assertTrue(isPickaxe(new ItemStack(Material.DIAMOND_PICKAXE)));
    }

    @Test
    void testIsDiamond() {
        Assertions.assertFalse(isDiamond(null));

        Assertions.assertTrue(isDiamond(new ItemStack(Material.DIAMOND_HELMET)));
        Assertions.assertTrue(isDiamond(new ItemStack(Material.DIAMOND_CHESTPLATE)));
        Assertions.assertTrue(isDiamond(new ItemStack(Material.DIAMOND_LEGGINGS)));
        Assertions.assertTrue(isDiamond(new ItemStack(Material.DIAMOND_BOOTS)));
        Assertions.assertTrue(isDiamond(new ItemStack(Material.DIAMOND_SWORD)));
        Assertions.assertTrue(isDiamond(new ItemStack(Material.DIAMOND_PICKAXE)));
        Assertions.assertTrue(isDiamond(new ItemStack(Material.DIAMOND_SPADE)));
        Assertions.assertTrue(isDiamond(new ItemStack(Material.DIAMOND_HOE)));
        Assertions.assertTrue(isDiamond(new ItemStack(Material.DIAMOND_AXE)));
    }

    @Test
    void testIsGold() {
        Assertions.assertFalse(isGold(null));

        Assertions.assertTrue(isGold(new ItemStack(Material.GOLD_HELMET)));
        Assertions.assertTrue(isGold(new ItemStack(Material.GOLD_CHESTPLATE)));
        Assertions.assertTrue(isGold(new ItemStack(Material.GOLD_LEGGINGS)));
        Assertions.assertTrue(isGold(new ItemStack(Material.GOLD_BOOTS)));
        Assertions.assertTrue(isGold(new ItemStack(Material.GOLD_SWORD)));
        Assertions.assertTrue(isGold(new ItemStack(Material.GOLD_PICKAXE)));
        Assertions.assertTrue(isGold(new ItemStack(Material.GOLD_SPADE)));
        Assertions.assertTrue(isGold(new ItemStack(Material.GOLD_HOE)));
        Assertions.assertTrue(isGold(new ItemStack(Material.GOLD_AXE)));
    }

    @Test
    void testIsBow() {
        Assertions.assertFalse(isBow(null));
        Assertions.assertTrue(isBow(new ItemStack(Material.BOW)));
    }

    @Test
    void testIsWeapon() {
        Assertions.assertFalse(isWeapon(null));

        Assertions.assertTrue(isWeapon(new ItemStack(Material.WOOD_SWORD)));
        Assertions.assertTrue(isWeapon(new ItemStack(Material.STONE_SWORD)));
        Assertions.assertTrue(isWeapon(new ItemStack(Material.GOLD_SWORD)));
        Assertions.assertTrue(isWeapon(new ItemStack(Material.IRON_SWORD)));
        Assertions.assertTrue(isWeapon(new ItemStack(Material.DIAMOND_SWORD)));
        Assertions.assertTrue(isWeapon(new ItemStack(Material.WOOD_AXE)));
        Assertions.assertTrue(isWeapon(new ItemStack(Material.STONE_AXE)));
        Assertions.assertTrue(isWeapon(new ItemStack(Material.IRON_AXE)));
        Assertions.assertTrue(isWeapon(new ItemStack(Material.GOLD_AXE)));
        Assertions.assertTrue(isWeapon(new ItemStack(Material.DIAMOND_AXE)));
    }
}
