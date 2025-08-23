package colosseum.utility

import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sin
import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.util.Vector
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*
import java.util.concurrent.*

object UtilMath {
    const val TAU: Double = Math.PI * 2.0
    private val random = Random()

    @JvmStatic
    fun trim(degree: Int, d: Double): Double {
        val symb = DecimalFormatSymbols()
        val twoDForm = DecimalFormat("#.#${"#".repeat(max(0.0, (degree - 1).toDouble()).toInt())}", symb)
        return twoDForm.format(d).toDouble()
    }

    @JvmStatic
    fun r(i: Int): Int {
        return random.nextInt(i)
    }

    @JvmStatic
    fun rRange(min: Int, max: Int): Int {
        return min + r(1 + max - min)
    }

    @JvmStatic
    fun offset2d(a: Entity, b: Entity): Double {
        return offset2d(a.location.toVector(), b.location.toVector())
    }

    @JvmStatic
    fun offset2d(a: Location, b: Location): Double {
        return offset2d(a.toVector(), b.toVector())
    }

    @JvmStatic
    fun offset2d(a: Vector, b: Vector): Double {
        a.setY(0)
        b.setY(0)
        return a.subtract(b).length()
    }

    @JvmStatic
    fun offset2dSquared(a: Entity, b: Entity): Double {
        return offset2dSquared(a.location.toVector(), b.location.toVector())
    }

    @JvmStatic
    fun offset2dSquared(a: Location, b: Location): Double {
        return offset2dSquared(a.toVector(), b.toVector())
    }

    @JvmStatic
    fun offset2dSquared(a: Vector, b: Vector): Double {
        a.setY(0)
        b.setY(0)
        return a.subtract(b).lengthSquared()
    }

    @JvmStatic
    fun offset(a: Entity, b: Entity): Double {
        return offset(a.location.toVector(), b.location.toVector())
    }

    @JvmStatic
    fun offset(a: Location, b: Location): Double {
        return offset(a.toVector(), b.toVector())
    }

    @JvmStatic
    fun offset(a: Vector, b: Vector): Double {
        return a.clone().subtract(b).length()
    }

    @JvmStatic
    fun offsetSquared(a: Entity, b: Entity): Double {
        return offsetSquared(a.location, b.location)
    }

    @JvmStatic
    fun offsetSquared(a: Location, b: Location): Double {
        return offsetSquared(a.toVector(), b.toVector())
    }

    @JvmStatic
    fun offsetSquared(a: Vector, b: Vector): Double {
        return a.distanceSquared(b)
    }

    @JvmStatic
    fun rr(d: Double, bidirectional: Boolean): Double {
        if (bidirectional) {
            return Math.random() * (2 * d) - d
        }
        return Math.random() * d
    }

    @JvmStatic
    fun <T> randomElement(array: Array<T>): T? {
        if (array.isEmpty()) {
            return null
        }
        return array[random.nextInt(array.size)]
    }

    @JvmStatic
    fun <T> randomElement(list: List<T>): T? {
        if (list.isEmpty()) {
            return null
        }
        return list[random.nextInt(list.size)]
    }

    @JvmStatic
    fun clamp(num: Double, min: Double, max: Double): Double {
        return if (num < min) min else (min(num, max))
    }

    @JvmStatic
    fun clamp(num: Float, min: Float, max: Float): Float {
        return if (num < min) min else (min(num.toDouble(), max.toDouble()).toFloat())
    }

    @JvmStatic
    fun clamp(num: Long, min: Long, max: Long): Long {
        return if (num < min) min else (min(num.toDouble(), max.toDouble()).toLong())
    }

    @JvmStatic
    fun clamp(num: Int, min: Int, max: Int): Int {
        return if (num < min) min else (min(num.toDouble(), max.toDouble()).toInt())
    }

    @JvmStatic
    fun digits(digit: Int): List<Int> {
        var i = digit
        val digits: MutableList<Int> = ArrayList()
        while (i > 0) {
            digits.add(i % 10)
            i /= 10
        }
        return digits
    }

    @JvmStatic
    fun random(min: Double, max: Double): Double {
        var min0 = min
        min0 = abs(min0)
        var rand = -random.nextInt((min0 * 100).toInt())
        rand += random.nextInt((max * 100).toInt())
        return (rand.toDouble()) / 100.0
    }

    @JvmStatic
    fun <N : Number> closest(values: List<N>, value: N): N {
        var closestIndex = -1

        var index = 0
        for (number in values) {
            if (closestIndex == -1 || (abs(number.toDouble() - value.toDouble()) < abs(values[closestIndex].toDouble() - value.toDouble()))) {
                closestIndex = index
            }
            index++
        }

        return values[closestIndex]
    }

    @JvmStatic
    fun isOdd(size: Int): Boolean {
        return !isEven(size)
    }

    @JvmStatic
    fun isEven(size: Int): Boolean {
        return size % 2 == 0
    }

    @JvmStatic
    fun getBits(value: Int): ByteArray {
        val bits = ByteArray(32)

        val bit = StringBuilder(java.lang.Long.toBinaryString(value.toLong()))

        while (bit.length < 32) {
            bit.insert(0, "0")
        }

        var index = 0
        for (c in bit.toString().toCharArray()) {
            bits[index] = (if (c == '1') '1' else '0').code.toByte()
            index++
        }

        return bits
    }

    @JvmStatic
    fun getBits(value: Long): ByteArray {
        val bits = ByteArray(64)

        val bit = StringBuilder(java.lang.Long.toBinaryString(value))

        while (bit.length < 64) {
            bit.insert(0, "0")
        }

        var index = 0
        for (c in bit.toString().toCharArray()) {
            bits[index] = (if (c == '1') '1' else '0').code.toByte()
            index++
        }

        return bits
    }

    @JvmStatic
    fun getBits(value: Byte): ByteArray {
        val bits = ByteArray(8)

        val bit = StringBuilder(java.lang.Long.toBinaryString(value.toLong()))

        while (bit.length < 8) {
            bit.insert(0, "0")
        }

        var index = 0
        for (c in bit.toString().toCharArray()) {
            bits[index] = (if (c == '1') '1' else '0').code.toByte()
            index++
        }

        return bits
    }

    @JvmStatic
    fun getBits(value: Short): ByteArray {
        val bits = ByteArray(16)

        val bit = StringBuilder(java.lang.Long.toBinaryString(value.toLong()))

        while (bit.length < 16) {
            bit.insert(0, "0")
        }

        var index = 0
        for (c in bit.toString().toCharArray()) {
            bits[index] = (if (c == '1') '1' else '0').code.toByte()
            index++
        }

        return bits
    }

    @JvmStatic
    fun getDecimalPoints(n: Double): Double {
        return n - n.toInt()
    }

    @JvmStatic
    fun getMax(vararg ints: Int): Int {
        if (ints.isEmpty()) {
            return -1
        }
        var max = ints[0]

        for (i in 1 until ints.size) {
            max = max(max.toDouble(), ints[i].toDouble()).toInt()
        }

        return max
    }

    @JvmStatic
    fun getMin(vararg ints: Int): Int {
        if (ints.isEmpty()) {
            return -1
        }
        var min = ints[0]

        for (i in 1 until ints.size) {
            min = min(min.toDouble(), ints[i].toDouble()).toInt()
        }

        return min
    }

    /**
     * Creates an array of points, arranged in a circle normal to a vector.
     *
     * @param center The center of the circle.
     * @param normal A vector normal to the circle.
     * @param radius The radius of the circle.
     * @param points How many points to make up the circle.
     * @return An array of points of the form `double[point #][x=0, y=1, z=3]`.
     */
    @JvmStatic
    fun normalCircle(center: Location, normal: Vector, radius: Double, points: Int): Array<DoubleArray> {
        return normalCircle(center.toVector(), normal, radius, points)
    }

    /**
     * Creates an array of points, arranged in a circle normal to a vector.
     *
     * @param center The center of the circle.
     * @param normal A vector normal to the circle.
     * @param radius The radius of the circle.
     * @param points How many points to make up the circle.
     * @return An array of points of the form `double[point #][x=0, y=1, z=3]`.
     */
    @JvmStatic
    fun normalCircle(center: Vector, normal: Vector, radius: Double, points: Int): Array<DoubleArray> {
        val n = normal.clone().normalize()
        val a = n.clone().add(Vector(1, 1, 1)).crossProduct(n).normalize()
        val b = n.getCrossProduct(a).normalize()

        val data = Array(points) { DoubleArray(3) }

        val interval = TAU / points
        var theta = 0.0

        for (i in 0 until points) {
            data[i][0] = center.x + (radius * ((cos(theta) * a.x) + (sin(theta) * b.x)))
            data[i][1] = center.y + (radius * ((cos(theta) * a.y) + (sin(theta) * b.y)))
            data[i][2] = center.z + (radius * ((cos(theta) * a.z) + (sin(theta) * b.z)))
            theta += interval
        }

        return data
    }

    /**
     * Slightly randomize a location with a standard deviation of one.
     *
     * @param location The location to randomize.
     * @return The original location, now gaussian-randomized.
     */
    @JvmStatic
    fun gauss(location: Location): Location {
        return gauss(location, 1.0, 1.0, 1.0)
    }

    /**
     * Slightly randomize a vector with a standard deviation of one.
     *
     * @param vector The location to randomize.
     * @return The randomized vector, now gaussian-randomized.
     */
    @JvmStatic
    fun gauss(vector: Vector): Vector {
        return gauss(vector, 1.0, 1.0, 1.0)
    }

    /**
     * Slightly randomize a location with a standard deviation of one.
     *
     * **This method only accepts positive values for all of its arguments.**
     *
     *
     * A good parameter set for small offsets is (loc, 10, 10, 10).
     *
     * @param location The location to randomize.
     * @param x        A granularity control for the x-axis, higher numbers = less randomness
     * @param y        A granularity control for the y-axis, higher numbers = less randomness
     * @param z        A granularity control for the z-axis, higher numbers = less randomness
     * @return The original location, now gaussian-randomized
     */
    @JvmStatic
    fun gauss(location: Location, x: Double, y: Double, z: Double): Location {
        return location.clone().add(
            if (x <= 0) 0.0 else (ThreadLocalRandom.current().nextGaussian() / x),
            if (y <= 0) 0.0 else (ThreadLocalRandom.current().nextGaussian() / y),
            if (z <= 0) 0.0 else (ThreadLocalRandom.current().nextGaussian() / z)
        )
    }

    /**
     * Slightly randomize a vector with a standard deviation of one.
     *
     * **This method only accepts positive values for all of its arguments.**
     *
     *
     * A good parameter set for small offsets is (loc, 10, 10, 10).
     *
     * @param vector The location to randomize.
     * @param x      A granularity control for the x-axis, higher numbers = less randomness
     * @param y      A granularity control for the y-axis, higher numbers = less randomness
     * @param z      A granularity control for the z-axis, higher numbers = less randomness
     * @return The randomized vector, now gaussian-randomized
     */
    @JvmStatic
    fun gauss(vector: Vector, x: Double, y: Double, z: Double): Vector {
        return vector.clone().add(
            Vector(
                if (x <= 0) 0.0 else (ThreadLocalRandom.current().nextGaussian() / x),
                if (y <= 0) 0.0 else (ThreadLocalRandom.current().nextGaussian() / y),
                if (z <= 0) 0.0 else (ThreadLocalRandom.current().nextGaussian() / z)
            )
        )
    }
}
