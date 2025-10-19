package colosseum.utility.unittest;

import colosseum.utility.UtilTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.util.concurrent.TimeUnit;

class TestUtilTime {
    @Test
    void testNowAndDate() {
        String now = UtilTime.now();
        Assertions.assertTrue(now.matches("\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}:\\d{2}"));
        String today = UtilTime.date();
        Assertions.assertTrue(today.matches("\\d{2}-\\d{2}-\\d{4}"));
    }

    @Test
    void testWhen() {
        Assumptions.assumeTrue(ZoneId.systemDefault().equals(ZoneId.of("UTC")));
        Assertions.assertEquals("01-01-1970 23:59:59", UtilTime.when(TimeUnit.SECONDS.toMillis(86399L)));
        Assertions.assertEquals("11-14-2023 22:13:20", UtilTime.when(TimeUnit.SECONDS.toMillis(1700000000L)));
    }

    @Test
    void testDate() {
        Assumptions.assumeTrue(ZoneId.systemDefault().equals(ZoneId.of("UTC")));
        Assertions.assertEquals("01-01-1970", UtilTime.date(TimeUnit.SECONDS.toMillis(86399L)));
        Assertions.assertEquals("11-14-2023", UtilTime.date(TimeUnit.SECONDS.toMillis(1700000000L)));
    }

    @Test
    void testGetDayOfMonthSuffix() {
        Assertions.assertEquals("st", UtilTime.getDayOfMonthSuffix(1));
        Assertions.assertEquals("nd", UtilTime.getDayOfMonthSuffix(2));
        Assertions.assertEquals("rd", UtilTime.getDayOfMonthSuffix(3));
        Assertions.assertEquals("th", UtilTime.getDayOfMonthSuffix(4));
        Assertions.assertEquals("th", UtilTime.getDayOfMonthSuffix(5));
        Assertions.assertEquals("th", UtilTime.getDayOfMonthSuffix(6));
        Assertions.assertEquals("th", UtilTime.getDayOfMonthSuffix(7));
        Assertions.assertEquals("th", UtilTime.getDayOfMonthSuffix(8));
        Assertions.assertEquals("th", UtilTime.getDayOfMonthSuffix(9));
        Assertions.assertEquals("th", UtilTime.getDayOfMonthSuffix(10));
        Assertions.assertEquals("th", UtilTime.getDayOfMonthSuffix(11));
        Assertions.assertEquals("th", UtilTime.getDayOfMonthSuffix(12));
        Assertions.assertEquals("th", UtilTime.getDayOfMonthSuffix(13));
        Assertions.assertEquals("th", UtilTime.getDayOfMonthSuffix(14));
        Assertions.assertEquals("th", UtilTime.getDayOfMonthSuffix(15));
        Assertions.assertEquals("th", UtilTime.getDayOfMonthSuffix(16));
        Assertions.assertEquals("th", UtilTime.getDayOfMonthSuffix(17));
        Assertions.assertEquals("th", UtilTime.getDayOfMonthSuffix(18));
        Assertions.assertEquals("th", UtilTime.getDayOfMonthSuffix(19));
        Assertions.assertEquals("th", UtilTime.getDayOfMonthSuffix(20));
        Assertions.assertEquals("st", UtilTime.getDayOfMonthSuffix(21));
        Assertions.assertEquals("nd", UtilTime.getDayOfMonthSuffix(22));
        Assertions.assertEquals("rd", UtilTime.getDayOfMonthSuffix(23));
        Assertions.assertEquals("th", UtilTime.getDayOfMonthSuffix(24));
        Assertions.assertEquals("th", UtilTime.getDayOfMonthSuffix(25));
        Assertions.assertEquals("th", UtilTime.getDayOfMonthSuffix(26));
        Assertions.assertEquals("th", UtilTime.getDayOfMonthSuffix(27));
        Assertions.assertEquals("th", UtilTime.getDayOfMonthSuffix(28));
        Assertions.assertEquals("th", UtilTime.getDayOfMonthSuffix(29));
        Assertions.assertEquals("th", UtilTime.getDayOfMonthSuffix(30));
        Assertions.assertEquals("st", UtilTime.getDayOfMonthSuffix(31));
    }

    @Test
    void testConvert() {
        Assertions.assertEquals(1, UtilTime.convert(1000, UtilTime.TimeUnit.MILLISECONDS, UtilTime.TimeUnit.SECONDS));
        Assertions.assertEquals(60000, UtilTime.convert(1, UtilTime.TimeUnit.MINUTES, UtilTime.TimeUnit.MILLISECONDS));
    }

    @Test
    void testSince() {
        final long time = System.currentTimeMillis();
        Assertions.assertEquals("Took 11 milliseconds.", UtilTime.since(() -> time, time - 11L));
        Assertions.assertEquals("Took 1.0 second.", UtilTime.since(() -> time, time - 1010L));
        Assertions.assertEquals("Took 1.1 seconds.", UtilTime.since(() -> time, time - 1110L));
        Assertions.assertEquals("Took 1.0 minute.", UtilTime.since(() -> time, time - 60600L));
        Assertions.assertEquals("Took 1.1 minutes.", UtilTime.since(() -> time, time - 66600L));
        Assertions.assertEquals("Took 1.0 hour.", UtilTime.since(() -> time, time - 3600000L));
        Assertions.assertEquals("Took 1.1 hours.", UtilTime.since(() -> time, time - 3996000L));
        Assertions.assertEquals("Took 1.0 day.", UtilTime.since(() -> time, time - 86400000L));
        Assertions.assertEquals("Took 1.1 days.", UtilTime.since(() -> time, time - 96768000L));
    }

    @Test
    void testConvertString() {
        Assertions.assertEquals("Permanent", UtilTime.convertString(-2L, 0, UtilTime.TimeUnit.SECONDS));
        Assertions.assertEquals("Permanent", UtilTime.convertString(-1L, 0, UtilTime.TimeUnit.SECONDS));

        Assertions.assertEquals("101 milliseconds", UtilTime.convertString(101L, 0, UtilTime.TimeUnit.MILLISECONDS));
        Assertions.assertEquals("101 milliseconds", UtilTime.convertString(101L, 1, UtilTime.TimeUnit.MILLISECONDS));
        Assertions.assertEquals("101 milliseconds", UtilTime.convertString(101L, 2, UtilTime.TimeUnit.MILLISECONDS));
        Assertions.assertEquals("101 milliseconds", UtilTime.convertString(101L, 3, UtilTime.TimeUnit.MILLISECONDS));

        Assertions.assertEquals("1 millisecond", UtilTime.convertString(1L, 0, UtilTime.TimeUnit.MILLISECONDS));
        Assertions.assertEquals("1 millisecond", UtilTime.convertString(1L, 1, UtilTime.TimeUnit.MILLISECONDS));
        Assertions.assertEquals("1 millisecond", UtilTime.convertString(1L, 2, UtilTime.TimeUnit.MILLISECONDS));
        Assertions.assertEquals("1 millisecond", UtilTime.convertString(1L, 3, UtilTime.TimeUnit.MILLISECONDS));

        Assertions.assertEquals("0 seconds", UtilTime.convertString(0L, 0, UtilTime.TimeUnit.SECONDS));
        Assertions.assertEquals("0.0 seconds", UtilTime.convertString(0L, 1, UtilTime.TimeUnit.SECONDS));
        Assertions.assertEquals("0.0 seconds", UtilTime.convertString(0L, 2, UtilTime.TimeUnit.SECONDS));
        Assertions.assertEquals("0.0 seconds", UtilTime.convertString(0L, 3, UtilTime.TimeUnit.SECONDS));
        Assertions.assertEquals("0.0 seconds", UtilTime.convertString(0L, 4, UtilTime.TimeUnit.SECONDS));

        Assertions.assertEquals("0 minutes", UtilTime.convertString(0L, 0, UtilTime.TimeUnit.MINUTES));
        Assertions.assertEquals("0.0 minutes", UtilTime.convertString(0L, 1, UtilTime.TimeUnit.MINUTES));
        Assertions.assertEquals("0.0 minutes", UtilTime.convertString(0L, 2, UtilTime.TimeUnit.MINUTES));
        Assertions.assertEquals("0.0 minutes", UtilTime.convertString(0L, 3, UtilTime.TimeUnit.MINUTES));
        Assertions.assertEquals("0.0 minutes", UtilTime.convertString(0L, 4, UtilTime.TimeUnit.MINUTES));

        Assertions.assertEquals("0 seconds", UtilTime.convertString(100L, 0, UtilTime.TimeUnit.SECONDS));
        Assertions.assertEquals("0.1 seconds", UtilTime.convertString(100L, 1, UtilTime.TimeUnit.SECONDS));
        Assertions.assertEquals("0.1 seconds", UtilTime.convertString(100L, 2, UtilTime.TimeUnit.SECONDS));
        Assertions.assertEquals("0.1 seconds", UtilTime.convertString(100L, 3, UtilTime.TimeUnit.SECONDS));

        Assertions.assertEquals("0 seconds", UtilTime.convertString(111L, 0, UtilTime.TimeUnit.SECONDS));
        Assertions.assertEquals("0.1 seconds", UtilTime.convertString(111L, 1, UtilTime.TimeUnit.SECONDS));
        Assertions.assertEquals("0.11 seconds", UtilTime.convertString(111L, 2, UtilTime.TimeUnit.SECONDS));
        Assertions.assertEquals("0.111 seconds", UtilTime.convertString(111L, 3, UtilTime.TimeUnit.SECONDS));
        Assertions.assertEquals("0.111 seconds", UtilTime.convertString(111L, 4, UtilTime.TimeUnit.SECONDS));
        Assertions.assertEquals("0.111 seconds", UtilTime.convertString(111L, 5, UtilTime.TimeUnit.SECONDS));

        Assertions.assertEquals("1 second", UtilTime.convertString(1000L, 0, UtilTime.TimeUnit.SECONDS));
        Assertions.assertEquals("1.0 second", UtilTime.convertString(1000L, 1, UtilTime.TimeUnit.SECONDS));
        Assertions.assertEquals("1.0 second", UtilTime.convertString(1000L, 2, UtilTime.TimeUnit.SECONDS));
        Assertions.assertEquals("1.0 second", UtilTime.convertString(1000L, 3, UtilTime.TimeUnit.SECONDS));

        Assertions.assertEquals("1 second", UtilTime.convertString(1001L, 0, UtilTime.TimeUnit.SECONDS));
        Assertions.assertEquals("1.0 second", UtilTime.convertString(1001L, 1, UtilTime.TimeUnit.SECONDS));
        Assertions.assertEquals("1.0 second", UtilTime.convertString(1001L, 2, UtilTime.TimeUnit.SECONDS));
        Assertions.assertEquals("1.001 seconds", UtilTime.convertString(1001L, 3, UtilTime.TimeUnit.SECONDS));
        Assertions.assertEquals("1.001 seconds", UtilTime.convertString(1001L, 4, UtilTime.TimeUnit.SECONDS));

        Assertions.assertEquals("2 seconds", UtilTime.convertString(2010L, 0, UtilTime.TimeUnit.SECONDS));
        Assertions.assertEquals("2.0 seconds", UtilTime.convertString(2010L, 1, UtilTime.TimeUnit.SECONDS));
        Assertions.assertEquals("2.01 seconds", UtilTime.convertString(2010L, 2, UtilTime.TimeUnit.SECONDS));
        Assertions.assertEquals("2.01 seconds", UtilTime.convertString(2010L, 3, UtilTime.TimeUnit.SECONDS));

        Assertions.assertEquals("2 seconds", UtilTime.convertString(2001L, 0, UtilTime.TimeUnit.SECONDS));
        Assertions.assertEquals("2.0 seconds", UtilTime.convertString(2001L, 1, UtilTime.TimeUnit.SECONDS));
        Assertions.assertEquals("2.0 seconds", UtilTime.convertString(2001L, 2, UtilTime.TimeUnit.SECONDS));
        Assertions.assertEquals("2.001 seconds", UtilTime.convertString(2001L, 3, UtilTime.TimeUnit.SECONDS));
        Assertions.assertEquals("2.001 seconds", UtilTime.convertString(2001L, 4, UtilTime.TimeUnit.SECONDS));
        Assertions.assertEquals("2.001 seconds", UtilTime.convertString(2001L, 5, UtilTime.TimeUnit.SECONDS));

        Assertions.assertEquals("1 minute", UtilTime.convertString(60000L, 0, UtilTime.TimeUnit.MINUTES));
        Assertions.assertEquals("1.0 minute", UtilTime.convertString(60000L, 1, UtilTime.TimeUnit.MINUTES));
        Assertions.assertEquals("1.0 minute", UtilTime.convertString(60000L, 2, UtilTime.TimeUnit.MINUTES));
        Assertions.assertEquals("1.0 minute", UtilTime.convertString(60000L, 3, UtilTime.TimeUnit.MINUTES));

        Assertions.assertEquals("1 minute", UtilTime.convertString(66000L, 0, UtilTime.TimeUnit.MINUTES));
        Assertions.assertEquals("1.1 minutes", UtilTime.convertString(66000L, 1, UtilTime.TimeUnit.MINUTES));
        Assertions.assertEquals("1.1 minutes", UtilTime.convertString(66000L, 2, UtilTime.TimeUnit.MINUTES));
        Assertions.assertEquals("1.1 minutes", UtilTime.convertString(66000L, 3, UtilTime.TimeUnit.MINUTES));

        Assertions.assertEquals("1 minute", UtilTime.convertString(66060L, 0, UtilTime.TimeUnit.MINUTES));
        Assertions.assertEquals("1.1 minutes", UtilTime.convertString(66060L, 1, UtilTime.TimeUnit.MINUTES));
        Assertions.assertEquals("1.1 minutes", UtilTime.convertString(66060L, 2, UtilTime.TimeUnit.MINUTES));
        Assertions.assertEquals("1.101 minutes", UtilTime.convertString(66060L, 3, UtilTime.TimeUnit.MINUTES));
        Assertions.assertEquals("1.101 minutes", UtilTime.convertString(66060L, 4, UtilTime.TimeUnit.MINUTES));

        Assertions.assertEquals("1.0 hour", UtilTime.convertString(3600000L, 0, UtilTime.TimeUnit.HOURS));
        Assertions.assertEquals("1.0 hour", UtilTime.convertString(3600000L, 1, UtilTime.TimeUnit.HOURS));
        Assertions.assertEquals("1.0 hour", UtilTime.convertString(3600000L, 2, UtilTime.TimeUnit.HOURS));
        Assertions.assertEquals("1.0 hour", UtilTime.convertString(3600000L, 3, UtilTime.TimeUnit.HOURS));

        Assertions.assertEquals("1.0 hour", UtilTime.convertString(3636000L, 0, UtilTime.TimeUnit.HOURS));
        Assertions.assertEquals("1.0 hour", UtilTime.convertString(3636000L, 1, UtilTime.TimeUnit.HOURS));
        Assertions.assertEquals("1.01 hours", UtilTime.convertString(3636000L, 2, UtilTime.TimeUnit.HOURS));
        Assertions.assertEquals("1.01 hours", UtilTime.convertString(3636000L, 3, UtilTime.TimeUnit.HOURS));

        Assertions.assertEquals("1.1 hours", UtilTime.convertString(3996000L, 0, UtilTime.TimeUnit.HOURS));
        Assertions.assertEquals("1.1 hours", UtilTime.convertString(3996000L, 1, UtilTime.TimeUnit.HOURS));
        Assertions.assertEquals("1.11 hours", UtilTime.convertString(3996000L, 2, UtilTime.TimeUnit.HOURS));
        Assertions.assertEquals("1.11 hours", UtilTime.convertString(3996000L, 3, UtilTime.TimeUnit.HOURS));

        Assertions.assertEquals("1.0 day", UtilTime.convertString(86400000L, 0, UtilTime.TimeUnit.DAYS));
        Assertions.assertEquals("1.0 day", UtilTime.convertString(86400000L, 1, UtilTime.TimeUnit.DAYS));
        Assertions.assertEquals("1.0 day", UtilTime.convertString(86400000L, 2, UtilTime.TimeUnit.DAYS));
        Assertions.assertEquals("1.0 day", UtilTime.convertString(86400000L, 3, UtilTime.TimeUnit.DAYS));

        Assertions.assertEquals("1.0 day", UtilTime.convertString(87264000L, 0, UtilTime.TimeUnit.DAYS));
        Assertions.assertEquals("1.0 day", UtilTime.convertString(87264000L, 1, UtilTime.TimeUnit.DAYS));
        Assertions.assertEquals("1.01 days", UtilTime.convertString(87264000L, 2, UtilTime.TimeUnit.DAYS));
        Assertions.assertEquals("1.01 days", UtilTime.convertString(87264000L, 3, UtilTime.TimeUnit.DAYS));

        Assertions.assertEquals("1.1 days", UtilTime.convertString(96768000L, 0, UtilTime.TimeUnit.DAYS));
        Assertions.assertEquals("1.1 days", UtilTime.convertString(96768000L, 1, UtilTime.TimeUnit.DAYS));
        Assertions.assertEquals("1.12 days", UtilTime.convertString(96768000L, 2, UtilTime.TimeUnit.DAYS));
        Assertions.assertEquals("1.12 days", UtilTime.convertString(96768000L, 3, UtilTime.TimeUnit.DAYS));
    }

    @Test
    void testConvertColonString() {
        Assertions.assertEquals("Permanent", UtilTime.convertColonString(-1L));
        Assertions.assertEquals("Permanent", UtilTime.convertColonString(-2L));
        Assertions.assertEquals("0", UtilTime.convertColonString(0L));

        Assertions.assertEquals("1", UtilTime.convertColonString(1000L, UtilTime.TimeUnit.SECONDS, UtilTime.TimeUnit.SECONDS));
        Assertions.assertEquals("9", UtilTime.convertColonString(9000L, UtilTime.TimeUnit.SECONDS, UtilTime.TimeUnit.SECONDS));
        Assertions.assertEquals("10", UtilTime.convertColonString(10000L, UtilTime.TimeUnit.SECONDS, UtilTime.TimeUnit.SECONDS));
        Assertions.assertEquals("61", UtilTime.convertColonString(61000L, UtilTime.TimeUnit.SECONDS, UtilTime.TimeUnit.SECONDS));

        Assertions.assertEquals("0:01", UtilTime.convertColonString(1000L, UtilTime.TimeUnit.MINUTES, UtilTime.TimeUnit.SECONDS));
        Assertions.assertEquals("0:10", UtilTime.convertColonString(10000L, UtilTime.TimeUnit.MINUTES, UtilTime.TimeUnit.SECONDS));
        Assertions.assertEquals("0:59", UtilTime.convertColonString(59999L, UtilTime.TimeUnit.MINUTES, UtilTime.TimeUnit.SECONDS));
        Assertions.assertEquals("1:00", UtilTime.convertColonString(60000L, UtilTime.TimeUnit.MINUTES, UtilTime.TimeUnit.SECONDS));
        Assertions.assertEquals("1:01", UtilTime.convertColonString(61000L, UtilTime.TimeUnit.MINUTES, UtilTime.TimeUnit.SECONDS));
        Assertions.assertEquals("59:59", UtilTime.convertColonString(3599999L, UtilTime.TimeUnit.MINUTES, UtilTime.TimeUnit.SECONDS));
        Assertions.assertEquals("60:01", UtilTime.convertColonString(3601000L, UtilTime.TimeUnit.MINUTES, UtilTime.TimeUnit.SECONDS));

        Assertions.assertEquals("0", UtilTime.convertColonString(1000L, UtilTime.TimeUnit.MINUTES, UtilTime.TimeUnit.MINUTES));
        Assertions.assertEquals("0", UtilTime.convertColonString(10000L, UtilTime.TimeUnit.MINUTES, UtilTime.TimeUnit.MINUTES));
        Assertions.assertEquals("0", UtilTime.convertColonString(59999L, UtilTime.TimeUnit.MINUTES, UtilTime.TimeUnit.MINUTES));
        Assertions.assertEquals("1", UtilTime.convertColonString(60000L, UtilTime.TimeUnit.MINUTES, UtilTime.TimeUnit.MINUTES));
        Assertions.assertEquals("1", UtilTime.convertColonString(61000L, UtilTime.TimeUnit.MINUTES, UtilTime.TimeUnit.MINUTES));
        Assertions.assertEquals("59", UtilTime.convertColonString(3599999L, UtilTime.TimeUnit.MINUTES, UtilTime.TimeUnit.MINUTES));
        Assertions.assertEquals("60", UtilTime.convertColonString(3601000L, UtilTime.TimeUnit.MINUTES, UtilTime.TimeUnit.MINUTES));

        Assertions.assertEquals("1:00:01", UtilTime.convertColonString(3601000L, UtilTime.TimeUnit.HOURS, UtilTime.TimeUnit.SECONDS));
        Assertions.assertEquals("23:59:59", UtilTime.convertColonString(86399999L, UtilTime.TimeUnit.HOURS, UtilTime.TimeUnit.SECONDS));
        Assertions.assertEquals("24:00:01", UtilTime.convertColonString(86401000L, UtilTime.TimeUnit.HOURS, UtilTime.TimeUnit.SECONDS));

        Assertions.assertEquals("1:00", UtilTime.convertColonString(3601000L, UtilTime.TimeUnit.HOURS, UtilTime.TimeUnit.MINUTES));
        Assertions.assertEquals("23:59", UtilTime.convertColonString(86399999L, UtilTime.TimeUnit.HOURS, UtilTime.TimeUnit.MINUTES));
        Assertions.assertEquals("24:00", UtilTime.convertColonString(86401000L, UtilTime.TimeUnit.HOURS, UtilTime.TimeUnit.MINUTES));

        Assertions.assertEquals("0", UtilTime.convertColonString(3599999L, UtilTime.TimeUnit.HOURS, UtilTime.TimeUnit.HOURS));
        Assertions.assertEquals("1", UtilTime.convertColonString(3601000L, UtilTime.TimeUnit.HOURS, UtilTime.TimeUnit.HOURS));
        Assertions.assertEquals("23", UtilTime.convertColonString(86399999L, UtilTime.TimeUnit.HOURS, UtilTime.TimeUnit.HOURS));
        Assertions.assertEquals("24", UtilTime.convertColonString(86401000L, UtilTime.TimeUnit.HOURS, UtilTime.TimeUnit.HOURS));

        Assertions.assertEquals("0:01:00:01", UtilTime.convertColonString(3601000L, UtilTime.TimeUnit.DAYS, UtilTime.TimeUnit.SECONDS));
        Assertions.assertEquals("0:23:59:59", UtilTime.convertColonString(86399999L, UtilTime.TimeUnit.DAYS, UtilTime.TimeUnit.SECONDS));
        Assertions.assertEquals("1:00:00:01", UtilTime.convertColonString(86401000L, UtilTime.TimeUnit.DAYS, UtilTime.TimeUnit.SECONDS));

        Assertions.assertEquals("0:01:00", UtilTime.convertColonString(3601000L, UtilTime.TimeUnit.DAYS, UtilTime.TimeUnit.MINUTES));
        Assertions.assertEquals("0:23:59", UtilTime.convertColonString(86399999L, UtilTime.TimeUnit.DAYS, UtilTime.TimeUnit.MINUTES));
        Assertions.assertEquals("1:00:00", UtilTime.convertColonString(86401000L, UtilTime.TimeUnit.DAYS, UtilTime.TimeUnit.MINUTES));

        Assertions.assertEquals("0:01", UtilTime.convertColonString(3601000L, UtilTime.TimeUnit.DAYS, UtilTime.TimeUnit.HOURS));
        Assertions.assertEquals("0:23", UtilTime.convertColonString(86399999L, UtilTime.TimeUnit.DAYS, UtilTime.TimeUnit.HOURS));
        Assertions.assertEquals("1:00", UtilTime.convertColonString(86401000L, UtilTime.TimeUnit.DAYS, UtilTime.TimeUnit.HOURS));

        Assertions.assertEquals("0", UtilTime.convertColonString(3601000L, UtilTime.TimeUnit.DAYS, UtilTime.TimeUnit.DAYS));
        Assertions.assertEquals("0", UtilTime.convertColonString(86399999L, UtilTime.TimeUnit.DAYS, UtilTime.TimeUnit.DAYS));
        Assertions.assertEquals("1", UtilTime.convertColonString(86401000L, UtilTime.TimeUnit.DAYS, UtilTime.TimeUnit.DAYS));
    }

    @Test
    void testElapsed() {
        long past = System.currentTimeMillis() - 5000;
        Assertions.assertTrue(UtilTime.elapsed(past, 1000));
        Assertions.assertFalse(UtilTime.elapsed(System.currentTimeMillis(), 1000));
    }

    @Test
    void testTimeUnitDecreasingOrder() {
        UtilTime.TimeUnit[] units = UtilTime.TimeUnit.decreasingOrder();
        Assertions.assertArrayEquals(new UtilTime.TimeUnit[]{
                UtilTime.TimeUnit.DAYS,
                UtilTime.TimeUnit.HOURS,
                UtilTime.TimeUnit.MINUTES,
                UtilTime.TimeUnit.SECONDS,
                UtilTime.TimeUnit.MILLISECONDS
        }, units);
    }
}
