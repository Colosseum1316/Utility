package colosseum.utility.unittest;

import colosseum.utility.TeamNames;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestTeamNames {
    @Test
    void test() {
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
}
