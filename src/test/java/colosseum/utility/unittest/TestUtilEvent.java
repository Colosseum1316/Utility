package colosseum.utility.unittest;

import colosseum.utility.UtilEvent;
import org.bukkit.event.block.Action;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestUtilEvent {
    @Test
    void testIsAction() {
        Assertions.assertTrue(UtilEvent.isAction(Action.LEFT_CLICK_BLOCK, UtilEvent.ActionType.L));
        Assertions.assertFalse(UtilEvent.isAction(Action.LEFT_CLICK_BLOCK, UtilEvent.ActionType.L_AIR));
        Assertions.assertTrue(UtilEvent.isAction(Action.LEFT_CLICK_BLOCK, UtilEvent.ActionType.L_BLOCK));
        Assertions.assertFalse(UtilEvent.isAction(Action.LEFT_CLICK_BLOCK, UtilEvent.ActionType.R));
        Assertions.assertFalse(UtilEvent.isAction(Action.LEFT_CLICK_BLOCK, UtilEvent.ActionType.R_AIR));
        Assertions.assertFalse(UtilEvent.isAction(Action.LEFT_CLICK_BLOCK, UtilEvent.ActionType.R_BLOCK));
        Assertions.assertTrue(UtilEvent.isAction(Action.LEFT_CLICK_BLOCK, UtilEvent.ActionType.ANY));

        Assertions.assertTrue(UtilEvent.isAction(Action.LEFT_CLICK_AIR, UtilEvent.ActionType.L));
        Assertions.assertTrue(UtilEvent.isAction(Action.LEFT_CLICK_AIR, UtilEvent.ActionType.L_AIR));
        Assertions.assertFalse(UtilEvent.isAction(Action.LEFT_CLICK_AIR, UtilEvent.ActionType.L_BLOCK));
        Assertions.assertFalse(UtilEvent.isAction(Action.LEFT_CLICK_AIR, UtilEvent.ActionType.R));
        Assertions.assertFalse(UtilEvent.isAction(Action.LEFT_CLICK_AIR, UtilEvent.ActionType.R_AIR));
        Assertions.assertFalse(UtilEvent.isAction(Action.LEFT_CLICK_AIR, UtilEvent.ActionType.R_BLOCK));
        Assertions.assertTrue(UtilEvent.isAction(Action.LEFT_CLICK_AIR, UtilEvent.ActionType.ANY));

        Assertions.assertFalse(UtilEvent.isAction(Action.RIGHT_CLICK_BLOCK, UtilEvent.ActionType.L));
        Assertions.assertFalse(UtilEvent.isAction(Action.RIGHT_CLICK_BLOCK, UtilEvent.ActionType.L_AIR));
        Assertions.assertFalse(UtilEvent.isAction(Action.RIGHT_CLICK_BLOCK, UtilEvent.ActionType.L_BLOCK));
        Assertions.assertTrue(UtilEvent.isAction(Action.RIGHT_CLICK_BLOCK, UtilEvent.ActionType.R));
        Assertions.assertFalse(UtilEvent.isAction(Action.RIGHT_CLICK_BLOCK, UtilEvent.ActionType.R_AIR));
        Assertions.assertTrue(UtilEvent.isAction(Action.RIGHT_CLICK_BLOCK, UtilEvent.ActionType.R_BLOCK));
        Assertions.assertTrue(UtilEvent.isAction(Action.RIGHT_CLICK_BLOCK, UtilEvent.ActionType.ANY));

        Assertions.assertFalse(UtilEvent.isAction(Action.RIGHT_CLICK_AIR, UtilEvent.ActionType.L));
        Assertions.assertFalse(UtilEvent.isAction(Action.RIGHT_CLICK_AIR, UtilEvent.ActionType.L_AIR));
        Assertions.assertFalse(UtilEvent.isAction(Action.RIGHT_CLICK_AIR, UtilEvent.ActionType.L_BLOCK));
        Assertions.assertTrue(UtilEvent.isAction(Action.RIGHT_CLICK_AIR, UtilEvent.ActionType.R));
        Assertions.assertTrue(UtilEvent.isAction(Action.RIGHT_CLICK_AIR, UtilEvent.ActionType.R_AIR));
        Assertions.assertFalse(UtilEvent.isAction(Action.RIGHT_CLICK_AIR, UtilEvent.ActionType.R_BLOCK));
        Assertions.assertTrue(UtilEvent.isAction(Action.RIGHT_CLICK_AIR, UtilEvent.ActionType.ANY));

        Assertions.assertFalse(UtilEvent.isAction(Action.PHYSICAL, UtilEvent.ActionType.L));
        Assertions.assertFalse(UtilEvent.isAction(Action.PHYSICAL, UtilEvent.ActionType.L_AIR));
        Assertions.assertFalse(UtilEvent.isAction(Action.PHYSICAL, UtilEvent.ActionType.L_BLOCK));
        Assertions.assertFalse(UtilEvent.isAction(Action.PHYSICAL, UtilEvent.ActionType.R));
        Assertions.assertFalse(UtilEvent.isAction(Action.PHYSICAL, UtilEvent.ActionType.R_AIR));
        Assertions.assertFalse(UtilEvent.isAction(Action.PHYSICAL, UtilEvent.ActionType.R_BLOCK));
        Assertions.assertFalse(UtilEvent.isAction(Action.PHYSICAL, UtilEvent.ActionType.ANY));
    }
}
