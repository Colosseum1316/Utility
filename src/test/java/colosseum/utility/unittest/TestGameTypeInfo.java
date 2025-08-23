package colosseum.utility.unittest;

import colosseum.utility.GameTypeInfo;
import colosseum.utility.arcade.GameType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class TestGameTypeInfo {
    @Test
    void testAddRemove() {
        GameType.getEntries().forEach(v -> {
            List<String> info = new ArrayList<>();
            GameTypeInfo gameTypeInfo = new GameTypeInfo(v, info);
            gameTypeInfo.addInfo("1");
            gameTypeInfo.addInfo("2");
            gameTypeInfo.addInfo("3");
            Assertions.assertLinesMatch(List.of("1", "2", "3"), info);
            gameTypeInfo.removeInfo(0);
            Assertions.assertLinesMatch(List.of("2", "3"), info);
            gameTypeInfo.removeInfo(1);
            Assertions.assertLinesMatch(List.of("2"), info);
        });
    }
}
