package colosseum.utility.unittest;

import be.seeseemelk.mockbukkit.Coordinate;
import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.WorldMock;
import be.seeseemelk.mockbukkit.block.BlockMock;
import colosseum.utility.UtilBlockBase;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TestUtilBlockBase {
    private static ServerMock server;
    private static WorldMock world;

    private static class TestUtilBlockBaseWorld extends WorldMock {
        private final Map<Coordinate, BlockMock> dummyBlocks = new HashMap<>();
        private static final Material[][][] CUBE_MATERIAL_INDEX_3_3 = {
                // x=-1
                {
                        // y=-1
                        {
                            Material.STONE, Material.DIAMOND_BLOCK, Material.GOLD_BLOCK
                        },
                        // y=0
                        {
                            Material.COBBLESTONE, Material.IRON_BLOCK, Material.LAPIS_BLOCK
                        },
                        // y=1
                        {
                            Material.MELON_BLOCK, Material.FURNACE, Material.CHEST
                        },
                },
                // x=0
                {
                        // y=-1
                        {
                            Material.QUARTZ_BLOCK, Material.GOLD_ORE, Material.LAPIS_ORE
                        },
                        // y=0
                        {
                            Material.QUARTZ_ORE, Material.IRON_ORE, Material.DIAMOND_ORE
                        },
                        // y=1
                        {
                            Material.OBSIDIAN, Material.COAL_ORE, Material.REDSTONE_ORE
                        },
                },
                // x=1
                {
                        // y=-1
                        {
                            Material.QUARTZ_STAIRS, Material.COAL_BLOCK, Material.REDSTONE_BLOCK
                        },
                        // y=0
                        {
                            Material.PUMPKIN, Material.EMERALD_BLOCK, Material.EMERALD_ORE
                        },
                        // y=1
                        {
                            Material.ANVIL, Material.HAY_BLOCK, Material.AIR
                        },
                },
        };

        public TestUtilBlockBaseWorld() {
            super(Material.AIR, 0);
        }

        @Override
        public BlockMock createBlock(Coordinate c) {
            // NO-OP
            return new BlockMock(Material.AIR);
        }

        @Override
        public BlockMock getBlockAt(int x, int y, int z) {
            // fall to -1,0,1
            return dummyBlocks.computeIfAbsent(new Coordinate(x, y, z), k -> new BlockMock(CUBE_MATERIAL_INDEX_3_3[x + 1][y + 1][z + 1], new Location(this, x, y, z)));
        }
    }

    @BeforeAll
    static void setup() throws Exception {
        server = MockBukkit.mock();
        Field worldsField = ServerMock.class.getDeclaredField("worlds");
        worldsField.setAccessible(true);
        ArrayList<World> worlds = (ArrayList<World>) worldsField.get(server);
        TestUtilBlockBaseWorld testUtilBlockBaseWorld = new TestUtilBlockBaseWorld();
        testUtilBlockBaseWorld.setName(testUtilBlockBaseWorld.getClass().getSimpleName());
        worlds.add(testUtilBlockBaseWorld);
        world = testUtilBlockBaseWorld;
    }

    @AfterAll
    static void tearDown() {
        MockBukkit.unload();
    }

    @Test
    void testGetSurrounding() {
        Block startPoint = world.getBlockAt(0, 0, 0);
        Assertions.assertEquals(Material.IRON_ORE, startPoint.getType());
        List<Block> foundBlocks = UtilBlockBase.getSurrounding(startPoint, false);
        Assertions.assertEquals(6, foundBlocks.size());
        List<Material> materials = foundBlocks.stream().map(Block::getType).toList();
        Assertions.assertTrue(materials.contains(Material.GOLD_ORE));
        Assertions.assertTrue(materials.contains(Material.COAL_ORE));
        Assertions.assertTrue(materials.contains(Material.EMERALD_BLOCK));
        Assertions.assertTrue(materials.contains(Material.IRON_BLOCK));
        Assertions.assertTrue(materials.contains(Material.DIAMOND_ORE));
        Assertions.assertTrue(materials.contains(Material.QUARTZ_ORE));

        Assertions.assertTrue(foundBlocks.stream().anyMatch(b -> b.getX() == 0 && b.getY() == 1 && b.getZ() == 0 && b.getType().equals(Material.COAL_ORE)));
        Assertions.assertTrue(foundBlocks.stream().anyMatch(b -> b.getX() == 0 && b.getY() == -1 && b.getZ() == 0 && b.getType().equals(Material.GOLD_ORE)));
        Assertions.assertTrue(foundBlocks.stream().anyMatch(b -> b.getX() == 1 && b.getY() == 0 && b.getZ() == 0 && b.getType().equals(Material.EMERALD_BLOCK)));
        Assertions.assertTrue(foundBlocks.stream().anyMatch(b -> b.getX() == -1 && b.getY() == 0 && b.getZ() == 0 && b.getType().equals(Material.IRON_BLOCK)));
        Assertions.assertTrue(foundBlocks.stream().anyMatch(b -> b.getX() == 0 && b.getY() == 0 && b.getZ() == 1 && b.getType().equals(Material.DIAMOND_ORE)));
        Assertions.assertTrue(foundBlocks.stream().anyMatch(b -> b.getX() == 0 && b.getY() == 0 && b.getZ() == -1 && b.getType().equals(Material.QUARTZ_ORE)));
    }

    @Test
    void testGetSurroundingWithDiagonals() {
        Block startPoint = world.getBlockAt(0, 0, 0);
        Assertions.assertEquals(Material.IRON_ORE, startPoint.getType());
        List<Block> foundBlocks = UtilBlockBase.getSurrounding(startPoint, true);
        Assertions.assertEquals(26, foundBlocks.size());
        List<Material> materials = foundBlocks.stream().map(Block::getType).toList();
        Assertions.assertTrue(materials.contains(Material.STONE));
        Assertions.assertTrue(materials.contains(Material.DIAMOND_BLOCK));
        Assertions.assertTrue(materials.contains(Material.GOLD_BLOCK));
        Assertions.assertTrue(materials.contains(Material.COBBLESTONE));
        Assertions.assertTrue(materials.contains(Material.IRON_BLOCK));
        Assertions.assertTrue(materials.contains(Material.LAPIS_BLOCK));
        Assertions.assertTrue(materials.contains(Material.MELON_BLOCK));
        Assertions.assertTrue(materials.contains(Material.FURNACE));
        Assertions.assertTrue(materials.contains(Material.CHEST));
        Assertions.assertTrue(materials.contains(Material.QUARTZ_BLOCK));
        Assertions.assertTrue(materials.contains(Material.GOLD_ORE));
        Assertions.assertTrue(materials.contains(Material.LAPIS_ORE));
        Assertions.assertTrue(materials.contains(Material.QUARTZ_ORE));
        Assertions.assertTrue(materials.contains(Material.DIAMOND_ORE));
        Assertions.assertTrue(materials.contains(Material.OBSIDIAN));
        Assertions.assertTrue(materials.contains(Material.COAL_ORE));
        Assertions.assertTrue(materials.contains(Material.REDSTONE_ORE));
        Assertions.assertTrue(materials.contains(Material.QUARTZ_STAIRS));
        Assertions.assertTrue(materials.contains(Material.COAL_BLOCK));
        Assertions.assertTrue(materials.contains(Material.REDSTONE_BLOCK));
        Assertions.assertTrue(materials.contains(Material.PUMPKIN));
        Assertions.assertTrue(materials.contains(Material.EMERALD_BLOCK));
        Assertions.assertTrue(materials.contains(Material.EMERALD_ORE));
        Assertions.assertTrue(materials.contains(Material.ANVIL));
        Assertions.assertTrue(materials.contains(Material.HAY_BLOCK));
        Assertions.assertTrue(materials.contains(Material.AIR));

        Assertions.assertTrue(foundBlocks.stream().anyMatch(b -> b.getX() == -1 && b.getY() == -1 && b.getZ() == -1 && b.getType().equals(Material.STONE)));
        Assertions.assertTrue(foundBlocks.stream().anyMatch(b -> b.getX() == -1 && b.getY() == -1 && b.getZ() == 0 && b.getType().equals(Material.DIAMOND_BLOCK)));
        Assertions.assertTrue(foundBlocks.stream().anyMatch(b -> b.getX() == -1 && b.getY() == -1 && b.getZ() == 1 && b.getType().equals(Material.GOLD_BLOCK)));
        Assertions.assertTrue(foundBlocks.stream().anyMatch(b -> b.getX() == -1 && b.getY() == 0 && b.getZ() == -1 && b.getType().equals(Material.COBBLESTONE)));
        Assertions.assertTrue(foundBlocks.stream().anyMatch(b -> b.getX() == -1 && b.getY() == 0 && b.getZ() == 0 && b.getType().equals(Material.IRON_BLOCK)));
        Assertions.assertTrue(foundBlocks.stream().anyMatch(b -> b.getX() == -1 && b.getY() == 0 && b.getZ() == 1 && b.getType().equals(Material.LAPIS_BLOCK)));
        Assertions.assertTrue(foundBlocks.stream().anyMatch(b -> b.getX() == -1 && b.getY() == 1 && b.getZ() == -1 && b.getType().equals(Material.MELON_BLOCK)));
        Assertions.assertTrue(foundBlocks.stream().anyMatch(b -> b.getX() == -1 && b.getY() == 1 && b.getZ() == 0 && b.getType().equals(Material.FURNACE)));
        Assertions.assertTrue(foundBlocks.stream().anyMatch(b -> b.getX() == -1 && b.getY() == 1 && b.getZ() == 1 && b.getType().equals(Material.CHEST)));
        Assertions.assertTrue(foundBlocks.stream().anyMatch(b -> b.getX() == 0 && b.getY() == -1 && b.getZ() == -1 && b.getType().equals(Material.QUARTZ_BLOCK)));
        Assertions.assertTrue(foundBlocks.stream().anyMatch(b -> b.getX() == 0 && b.getY() == -1 && b.getZ() == 0 && b.getType().equals(Material.GOLD_ORE)));
        Assertions.assertTrue(foundBlocks.stream().anyMatch(b -> b.getX() == 0 && b.getY() == -1 && b.getZ() == 1 && b.getType().equals(Material.LAPIS_ORE)));
        Assertions.assertTrue(foundBlocks.stream().anyMatch(b -> b.getX() == 0 && b.getY() == 0 && b.getZ() == -1 && b.getType().equals(Material.QUARTZ_ORE)));
        Assertions.assertTrue(foundBlocks.stream().anyMatch(b -> b.getX() == 0 && b.getY() == 0 && b.getZ() == 1 && b.getType().equals(Material.DIAMOND_ORE)));
        Assertions.assertTrue(foundBlocks.stream().anyMatch(b -> b.getX() == 0 && b.getY() == 1 && b.getZ() == -1 && b.getType().equals(Material.OBSIDIAN)));
        Assertions.assertTrue(foundBlocks.stream().anyMatch(b -> b.getX() == 0 && b.getY() == 1 && b.getZ() == 0 && b.getType().equals(Material.COAL_ORE)));
        Assertions.assertTrue(foundBlocks.stream().anyMatch(b -> b.getX() == 0 && b.getY() == 1 && b.getZ() == 1 && b.getType().equals(Material.REDSTONE_ORE)));
        Assertions.assertTrue(foundBlocks.stream().anyMatch(b -> b.getX() == 1 && b.getY() == -1 && b.getZ() == -1 && b.getType().equals(Material.QUARTZ_STAIRS)));
        Assertions.assertTrue(foundBlocks.stream().anyMatch(b -> b.getX() == 1 && b.getY() == -1 && b.getZ() == 0 && b.getType().equals(Material.COAL_BLOCK)));
        Assertions.assertTrue(foundBlocks.stream().anyMatch(b -> b.getX() == 1 && b.getY() == -1 && b.getZ() == 1 && b.getType().equals(Material.REDSTONE_BLOCK)));
        Assertions.assertTrue(foundBlocks.stream().anyMatch(b -> b.getX() == 1 && b.getY() == 0 && b.getZ() == -1 && b.getType().equals(Material.PUMPKIN)));
        Assertions.assertTrue(foundBlocks.stream().anyMatch(b -> b.getX() == 1 && b.getY() == 0 && b.getZ() == 0 && b.getType().equals(Material.EMERALD_BLOCK)));
        Assertions.assertTrue(foundBlocks.stream().anyMatch(b -> b.getX() == 1 && b.getY() == 0 && b.getZ() == 1 && b.getType().equals(Material.EMERALD_ORE)));
        Assertions.assertTrue(foundBlocks.stream().anyMatch(b -> b.getX() == 1 && b.getY() == 1 && b.getZ() == -1 && b.getType().equals(Material.ANVIL)));
        Assertions.assertTrue(foundBlocks.stream().anyMatch(b -> b.getX() == 1 && b.getY() == 1 && b.getZ() == 0 && b.getType().equals(Material.HAY_BLOCK)));
        Assertions.assertTrue(foundBlocks.stream().anyMatch(b -> b.getX() == 1 && b.getY() == 1 && b.getZ() == 1 && b.getType().equals(Material.AIR)));
    }
}
