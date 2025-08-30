package colosseum.utility.unittest;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import colosseum.utility.UtilPlayerBase;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TestUtilPlayerBase {
    private static ServerMock server;

    @BeforeAll
    static void setup() {
        server = MockBukkit.mock();
    }

    @AfterAll
    static void tearDown() {
        MockBukkit.unload();
    }

    @Test
    void testSearchOnline() {
        PlayerMock player1 = new PlayerMock("test1");
        PlayerMock player2 = new PlayerMock("test2");
        PlayerMock player3 = new PlayerMock("test3");
        PlayerMock player4 = new PlayerMock("test4");
        server.addPlayer(player1);
        server.addPlayer(player2);
        server.addPlayer(player3);
        Assertions.assertNull(UtilPlayerBase.searchOnline(player1, "a", true));
        Assertions.assertNull(UtilPlayerBase.searchOnline(player1, "t", true));
        Assertions.assertNull(UtilPlayerBase.searchOnline(player1, "test", true));
        Assertions.assertNotNull(UtilPlayerBase.searchOnline(player1, "test1", true));
        Assertions.assertNotNull(UtilPlayerBase.searchOnline(player1, "test2", true));
        Assertions.assertNotNull(UtilPlayerBase.searchOnline(player1, "test3", true));
        Assertions.assertNull(UtilPlayerBase.searchOnline(player1, "test4", true));
        server.addPlayer(player4);
        Assertions.assertNotNull(UtilPlayerBase.searchOnline(player1, "test4", true));
    }
}
