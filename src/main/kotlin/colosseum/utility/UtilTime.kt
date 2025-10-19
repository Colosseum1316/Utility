package colosseum.utility

import colosseum.utility.UtilTime.date
import kotlin.math.max
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.function.*

@Suppress("MemberVisibilityCanBePrivate")
object UtilTime {
    private val DATE_FORMAT_NOW = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss")
    private val DATE_FORMAT_DAY = DateTimeFormatter.ofPattern("MM-dd-yyyy")
    val NOW_MILLIS = Supplier {
        System.currentTimeMillis()
    }

    @JvmStatic
    fun now(): String {
        return LocalDateTime.now().format(DATE_FORMAT_NOW)
    }

    /**
     * @param time Unix timestamp in milli seconds.
     * @return Formatted datetime in local timezone.
     */
    @JvmStatic
    fun `when`(time: Long): String {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault()).format(DATE_FORMAT_NOW)
    }

    @JvmStatic
    fun date(): String {
        return LocalDateTime.now().format(DATE_FORMAT_DAY)
    }

    /**
     * @param date Unix timestamp in milli seconds.
     * @return Formatted date in local timezone.
     */
    @JvmStatic
    fun date(date: Long): String {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(date), ZoneId.systemDefault()).format(DATE_FORMAT_DAY)
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

    @JvmStatic
    fun since(epoch: Long): String {
        return since(NOW_MILLIS, epoch);
    }

    @JvmStatic
    fun since(base: Supplier<Long>, epoch: Long): String {
        return "Took ${convertString(base.get() - epoch, 1, TimeUnit.FIT)}."
    }

    private fun convert0(time: Long, timeUnit: TimeUnit): TimeUnit {
        var type = timeUnit
        if (type == TimeUnit.FIT) {
            type = if (time < 1000) {
                TimeUnit.MILLISECONDS
            } else if (time < 60000) {
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
            TimeUnit.DAYS -> doTrim(trim, time / 86400000.0)
            TimeUnit.HOURS -> doTrim(trim, time / 3600000.0)
            TimeUnit.MINUTES -> doTrim(trim, time / 60000.0)
            TimeUnit.SECONDS -> doTrim(trim, time / 1000.0)
            else -> doTrim(trim, time.toDouble())
        }
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
    fun makeStr(time: Long): String {
        return convertString(time, 1, TimeUnit.FIT)
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
                TimeUnit.DAYS -> "${doTrim(trim, time / 86400000.0).also { num = it }} day"
                TimeUnit.HOURS -> "${doTrim(trim, time / 3600000.0).also { num = it }} hour"
                TimeUnit.MINUTES -> "${doTrim(trim, time / 60000.0).toInt().also { num = it.toDouble() }.toInt()} minute"
                TimeUnit.SECONDS -> "${doTrim(trim, time / 1000.0).toInt().also { num = it.toDouble() }.toInt()} second"
                else -> "${doTrim(0, time.toDouble()).toInt().also { num = it.toDouble() }.toInt()} millisecond"
            }
        } else {
            text = when (type) {
                TimeUnit.DAYS -> "${doTrim(trim, time / 86400000.0).also { num = it }} day"
                TimeUnit.HOURS -> "${doTrim(trim, time / 3600000.0).also { num = it }} hour"
                TimeUnit.MINUTES -> "${doTrim(trim, time / 60000.0).also { num = it }} minute"
                TimeUnit.SECONDS -> "${doTrim(trim, time / 1000.0).also { num = it }} second"
                else -> "${doTrim(0, time.toDouble()).toInt().also { num = it.toDouble() }.toInt()} millisecond"
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
    
    @JvmStatic
    fun doTrim(degree: Int, d: Double): Double {
        val symb = DecimalFormatSymbols()
        val twoDForm = DecimalFormat("#.#${"#".repeat(max(0.0, (degree - 1).toDouble()).toInt())}", symb)
        return twoDForm.format(d).toDouble()
    }

    enum class TimeUnit(val milliseconds: Long) {
        FIT(0),
        DAYS(86400000),
        HOURS(3600000),
        MINUTES(60000),
        SECONDS(1000),
        MILLISECONDS(1);

        companion object {
            @JvmStatic
            fun decreasingOrder(): Array<TimeUnit> {
                return arrayOf(DAYS, HOURS, MINUTES, SECONDS, MILLISECONDS)
            }
        }
    }
}
