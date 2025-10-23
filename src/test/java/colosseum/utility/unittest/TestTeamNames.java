package colosseum.utility.unittest;

import colosseum.utility.TeamNames;
import org.bukkit.DyeColor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestTeamNames {
    @Test
    void testIndex() {
        TeamNames[] teamNames = TeamNames.values();
        Assertions.assertEquals("Orange", teamNames[1].name());
        Assertions.assertEquals("Magenta", teamNames[2].name());
        Assertions.assertEquals("Sky", teamNames[3].name());
        Assertions.assertEquals("Yellow", teamNames[4].name());
        Assertions.assertEquals("Lime", teamNames[5].name());
        Assertions.assertEquals("Pink", teamNames[6].name());
        Assertions.assertEquals("Gray", teamNames[7].name());
        Assertions.assertEquals("LGray", teamNames[8].name());
        Assertions.assertEquals("Cyan", teamNames[9].name());
        Assertions.assertEquals("Purple", teamNames[10].name());
        Assertions.assertEquals("Blue", teamNames[11].name());
        Assertions.assertEquals("Brown", teamNames[12].name());
        Assertions.assertEquals("Green", teamNames[13].name());
        Assertions.assertEquals("Red", teamNames[14].name());
        Assertions.assertEquals("Black", teamNames[15].name());
    }

    @Test
    void testData() {
        TeamNames[] teamNames = TeamNames.values();
        DyeColor[] colors = DyeColor.values();
        Assertions.assertEquals(colors.length, teamNames.length);
        for (int i = 0; i < teamNames.length; i++) {
            Assertions.assertEquals(colors[i].getWoolData(), teamNames[i].getWoolData());
        }
    }

    @Test
    void testGetByDyeColor() {
        Assertions.assertEquals(TeamNames.White, TeamNames.getByDyeColor(DyeColor.WHITE.name()));
        Assertions.assertEquals(TeamNames.Orange, TeamNames.getByDyeColor(DyeColor.ORANGE.name()));
        Assertions.assertEquals(TeamNames.Magenta, TeamNames.getByDyeColor(DyeColor.MAGENTA.name()));
        Assertions.assertEquals(TeamNames.Sky, TeamNames.getByDyeColor(DyeColor.LIGHT_BLUE.name()));
        Assertions.assertEquals(TeamNames.Yellow, TeamNames.getByDyeColor(DyeColor.YELLOW.name()));
        Assertions.assertEquals(TeamNames.Lime, TeamNames.getByDyeColor(DyeColor.LIME.name()));
        Assertions.assertEquals(TeamNames.Pink, TeamNames.getByDyeColor(DyeColor.PINK.name()));
        Assertions.assertEquals(TeamNames.Gray, TeamNames.getByDyeColor(DyeColor.GRAY.name()));
        Assertions.assertEquals(TeamNames.LGray, TeamNames.getByDyeColor(DyeColor.SILVER.name()));
        Assertions.assertEquals(TeamNames.Cyan, TeamNames.getByDyeColor(DyeColor.CYAN.name()));
        Assertions.assertEquals(TeamNames.Purple, TeamNames.getByDyeColor(DyeColor.PURPLE.name()));
        Assertions.assertEquals(TeamNames.Blue, TeamNames.getByDyeColor(DyeColor.BLUE.name()));
        Assertions.assertEquals(TeamNames.Brown, TeamNames.getByDyeColor(DyeColor.BROWN.name()));
        Assertions.assertEquals(TeamNames.Green, TeamNames.getByDyeColor(DyeColor.GREEN.name()));
        Assertions.assertEquals(TeamNames.Red, TeamNames.getByDyeColor(DyeColor.RED.name()));
        Assertions.assertEquals(TeamNames.Black, TeamNames.getByDyeColor(DyeColor.BLACK.name()));
    }
}
