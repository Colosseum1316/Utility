package colosseum.utility

import kotlin.math.max
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

object UtilTime {
    val CENTRAL_ZONE: ZoneId = ZoneId.of("UTC")
    const val DATE_FORMAT_NOW: String = "MM-dd-yyyy HH:mm:ss"
    const val DATE_FORMAT_DAY: String = "MM-dd-yyyy"

    @JvmStatic
    fun now(): String {
        val cal = Calendar.getInstance()
        val sdf = SimpleDateFormat(DATE_FORMAT_NOW)
        return sdf.format(cal.time)
    }

    @JvmStatic
    fun `when`(time: Long): String {
        val sdf = SimpleDateFormat(DATE_FORMAT_NOW)
        return sdf.format(time)
    }

    @JvmStatic
    fun date(): String {
        val cal = Calendar.getInstance()
        val sdf = SimpleDateFormat(DATE_FORMAT_DAY)
        return sdf.format(cal.time)
    }

    @JvmStatic
    fun date(date: Long): String {
        val sdf = SimpleDateFormat(DATE_FORMAT_DAY)
        return sdf.format(date)
    }

    @JvmStatic
    fun getDayOfMonthSuffix(n: Int): String {
        if (n in 11..13) {
            return "th"
        }
        return when (n % 10) {
            1 -> "st"
            2 -> "nd"
            3 -> "rd"
            else -> "th"
        }
    }

    /**
     * Converts a [Timestamp] to a [LocalDateTime].
     * This method will only work for timestamp's stored using [CENTRAL_ZONE]
     *
     * @param timestamp the timestamp to convert
     * @return the time
     *
     * @see [fromTimestamp]
     */
    @JvmStatic
    fun fromTimestamp(timestamp: Timestamp): LocalDateTime {
        return fromTimestamp(timestamp, CENTRAL_ZONE)
    }

    /**
     * Converts a [Timestamp] to a [LocalDateTime].
     * The zone supplied should be that of which the timezone was stored using.
     *
     * @param timestamp the timestamp to convert
     * @param zoneId    the zone of the timestamp
     * @return the time
     *
     * @see [fromTimestamp]
     */
    @JvmStatic
    fun fromTimestamp(timestamp: Timestamp, zoneId: ZoneId): LocalDateTime {
        return LocalDateTime.ofInstant(timestamp.toInstant(), zoneId)
    }

    /**
     * Converts a [LocalDateTime] to a [Timestamp].
     * Please note that this will convert using the [CENTRAL_ZONE] timezone.
     *
     * @param localDateTime the time to convert
     * @return the timestamp
     *
     * @see [toTimestamp]
     */
    @JvmStatic
    fun toTimestamp(localDateTime: LocalDateTime): Timestamp {
        return toTimestamp(localDateTime, CENTRAL_ZONE)
    }

    /**
     * Converts a [LocalDateTime] to a [Timestamp].
     *
     * @param localDateTime the time to convert
     * @param zoneId        the zone to use when converting to a timestamp
     * @return the timestamp
     *
     * @see [toTimestamp]
     */
    @JvmStatic
    fun toTimestamp(localDateTime: LocalDateTime, zoneId: ZoneId): Timestamp {
        return Timestamp(localDateTime.atZone(zoneId).toInstant().toEpochMilli())
    }

    /**
     * Convert from one TimeUnit to a different one
     */
    @JvmStatic
    fun convert(time: Long, from: TimeUnit, to: TimeUnit): Long {
        val milleseconds = time * from.milliseconds
        return milleseconds / to.milliseconds
    }

    @JvmStatic
    fun since(epoch: Long): String {
        return "Took ${convertString(System.currentTimeMillis() - epoch, 1, TimeUnit.FIT)}."
    }

    private fun convert0(time: Long, timeUnit: TimeUnit): TimeUnit {
        var type = timeUnit
        if (type == TimeUnit.FIT) {
            type = if (time < 60000) {
                TimeUnit.SECONDS
            } else if (time < 3600000) {
                TimeUnit.MINUTES
            } else if (time < 86400000) {
                TimeUnit.HOURS
            } else {
                TimeUnit.DAYS
            }
        }
        return type
    }

    @JvmStatic
    fun convert(time: Long, trim: Int, timeUnit: TimeUnit): Double {
        val type = convert0(time, timeUnit)
        return when (type) {
            TimeUnit.DAYS -> UtilMath.trim(trim, (time) / 86400000.0)
            TimeUnit.HOURS -> UtilMath.trim(trim, (time) / 3600000.0)
            TimeUnit.MINUTES -> UtilMath.trim(trim, (time) / 60000.0)
            TimeUnit.SECONDS -> UtilMath.trim(trim, (time) / 1000.0)
            else -> UtilMath.trim(trim, time.toDouble())
        }
    }

    @JvmStatic
    fun makeStr(time: Long): String {
        return convertString(time, 1, TimeUnit.FIT)
    }

    @JvmStatic
    fun makeStr(time: Long, trim: Int): String {
        return convertString(max(0.0, time.toDouble()).toLong(), trim, TimeUnit.FIT)
    }

    @JvmStatic
    fun convertColonString(time: Long): String {
        return convertColonString(time, TimeUnit.HOURS, TimeUnit.SECONDS)
    }

    /**
     * Converts a time into a colon separated string, displaying max to min units.
     *
     * @param time Time in milliseconds
     * @param max  The max [TimeUnit] to display, inclusive
     * @param min  The min [TimeUnit] to display, inclusive
     * @return A colon separated string to represent the time
     */
    @JvmStatic
    fun convertColonString(time: Long, max: TimeUnit, min: TimeUnit): String {
        if (time <= -1L) {
            return "Permanent"
        } else if (time == 0L) {
            return "0"
        }

        val sb = StringBuilder()
        var curr = time
        for (unit in TimeUnit.decreasingOrder()) {
            if (unit.milliseconds >= min.milliseconds && unit.milliseconds <= max.milliseconds) {
                val amt: Long = curr / unit.milliseconds
                if (amt < 10 && unit.milliseconds != max.milliseconds) {
                    sb.append('0') // prefix single digit numbers with a 0
                }
                sb.append(amt)
                if (unit.milliseconds > min.milliseconds) {
                    sb.append(':')
                }
                curr -= amt * unit.milliseconds
            }
        }
        return sb.toString()
    }

    @JvmStatic
    fun convertString(time: Long, trim: Int, timeUnit: TimeUnit): String {
        if (time <= -1L) {
            return "Permanent"
        }

        val type = convert0(time, timeUnit)
        var text: String
        var num: Double
        if (trim == 0) {
            text = when (type) {
                TimeUnit.DAYS -> "${UtilMath.trim(trim, time / 86400000.0).also { num = it }} day"
                TimeUnit.HOURS -> "${UtilMath.trim(trim, time / 3600000.0).also { num = it }} hour"
                TimeUnit.MINUTES -> "${UtilMath.trim(trim, time / 60000.0).toInt().also { num = it.toDouble() }.toInt()} minute"
                TimeUnit.SECONDS -> "${UtilMath.trim(trim, time / 1000.0).toInt().also { num = it.toDouble() }.toInt()} second"
                else -> "${UtilMath.trim(trim, time.toDouble()).toInt().also { num = it.toDouble() }.toInt()} millisecond"
            }
        } else {
            text = when (type) {
                TimeUnit.DAYS -> "${UtilMath.trim(trim, time / 86400000.0).also { num = it }} day"
                TimeUnit.HOURS -> "${UtilMath.trim(trim, time / 3600000.0).also { num = it }} hour"
                TimeUnit.MINUTES -> "${UtilMath.trim(trim, time / 60000.0).also { num = it }} minute"
                TimeUnit.SECONDS -> "${UtilMath.trim(trim, time / 1000.0).also { num = it }} second"
                else -> "${UtilMath.trim(0, time.toDouble()).toInt().also { num = it.toDouble() }.toInt()} millisecond"
            }
        }

        if (num != 1.0) {
            text += "s"
        }
        return text
    }

    @JvmStatic
    fun elapsed(from: Long, required: Long): Boolean {
        return System.currentTimeMillis() - from > required
    }

    enum class TimeUnit(val milliseconds: Long) {
        FIT(1),
        DAYS(86400000),
        HOURS(3600000),
        MINUTES(60000),
        SECONDS(1000),
        MILLISECONDS(1);

        companion object {
            fun decreasingOrder(): Array<TimeUnit> {
                return arrayOf(DAYS, HOURS, MINUTES, SECONDS, MILLISECONDS)
            }
        }
    }
}
