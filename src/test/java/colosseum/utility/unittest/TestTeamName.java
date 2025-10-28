package colosseum.utility.unittest;

import colosseum.utility.TeamName;
import org.bukkit.DyeColor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestTeamName {
    @Test
    void testIndex() {
        TeamName[] teamNames = TeamName.values();
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
        TeamName[] teamNames = TeamName.values();
        DyeColor[] colors = DyeColor.values();
        Assertions.assertEquals(colors.length, teamNames.length);
        for (int i = 0; i < teamNames.length; i++) {
            Assertions.assertEquals(colors[i].getWoolData(), teamNames[i].getWoolData());
        }
    }

    @Test
    void testGetByDyeColor() {
        Assertions.assertEquals(TeamName.White, TeamName.getByDyeColor(DyeColor.WHITE.name()));
        Assertions.assertEquals(TeamName.Orange, TeamName.getByDyeColor(DyeColor.ORANGE.name()));
        Assertions.assertEquals(TeamName.Magenta, TeamName.getByDyeColor(DyeColor.MAGENTA.name()));
        Assertions.assertEquals(TeamName.Sky, TeamName.getByDyeColor(DyeColor.LIGHT_BLUE.name()));
        Assertions.assertEquals(TeamName.Yellow, TeamName.getByDyeColor(DyeColor.YELLOW.name()));
        Assertions.assertEquals(TeamName.Lime, TeamName.getByDyeColor(DyeColor.LIME.name()));
        Assertions.assertEquals(TeamName.Pink, TeamName.getByDyeColor(DyeColor.PINK.name()));
        Assertions.assertEquals(TeamName.Gray, TeamName.getByDyeColor(DyeColor.GRAY.name()));
        Assertions.assertEquals(TeamName.LGray, TeamName.getByDyeColor(DyeColor.SILVER.name()));
        Assertions.assertEquals(TeamName.Cyan, TeamName.getByDyeColor(DyeColor.CYAN.name()));
        Assertions.assertEquals(TeamName.Purple, TeamName.getByDyeColor(DyeColor.PURPLE.name()));
        Assertions.assertEquals(TeamName.Blue, TeamName.getByDyeColor(DyeColor.BLUE.name()));
        Assertions.assertEquals(TeamName.Brown, TeamName.getByDyeColor(DyeColor.BROWN.name()));
        Assertions.assertEquals(TeamName.Green, TeamName.getByDyeColor(DyeColor.GREEN.name()));
        Assertions.assertEquals(TeamName.Red, TeamName.getByDyeColor(DyeColor.RED.name()));
        Assertions.assertEquals(TeamName.Black, TeamName.getByDyeColor(DyeColor.BLACK.name()));
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            TeamName.getByDyeColor("None");
        });
    }
}
