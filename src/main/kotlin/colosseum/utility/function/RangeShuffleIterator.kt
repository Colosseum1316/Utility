package colosseum.utility.function

import com.google.common.base.Preconditions
import kotlin.math.min
import java.util.*

/**
 * An Iterator that shuffles and provides integers from a specified range.
 * The shuffle strategy is based on the Fisher-Yates shuffle.
 *
 * Create a RangeShuffleIterator encompassing the specified range (inclusive)
 *
 * @param start The range lower bound, inclusive
 * @param end   The range upper bound, inclusive
 *
 * @see nextInts
 * @see nextInt
 */
class RangeShuffleIterator(start: Int, end: Int) : PrimitiveIterator.OfInt {
    private val remaining: IntSet
    private var remainingCount: Int

    init {
        Preconditions.checkArgument(start <= end)
        this.remaining = IntSet(start, end)
        this.remainingCount = end - start + 1
    }

    /**
     * Provide a specified number of integers in an int array. If the number
     * of elements remaining is fewer than `maxAmount`, return all
     * remaining elements.
     *
     * @param maxAmount The number of elements to retrieve
     * @return An array containing the retrieved elements
     */
    fun nextInts(maxAmount: Int): IntArray {
        val ret = IntArray(
            min(remainingCount.toDouble(), maxAmount.toDouble()).toInt()
        )
        for (i in ret.indices) {
            ret[i] = nextInt()
        }
        return ret
    }

    override fun nextInt(): Int {
        check(hasNext()) { "No remaining ranges to iterate" }

        var selectedPosition = Random().nextInt(remainingCount)

        val it = remaining.ranges().iterator()

        val selected: Int
        while (true) {
            val range = it.next()
            val span = range.value - range.key
            if (span < selectedPosition) {
                selectedPosition -= span + 1
            } else {
                selected = range.key + selectedPosition
                break
            }
        }

        remaining.remove(selected)
        --remainingCount

        return selected
    }

    override fun remove() {
        throw UnsupportedOperationException()
    }

    override fun hasNext(): Boolean {
        return remainingCount > 0
    }

    /**
     * A set of integers. The set is seeded by a single range, and the only
     * supported operation is int removal.
     *
     *
     * This implementation only exists for performance reasons.
     *
     *
     * Create an IntSet containing all numbers from `start` to
     * `end`, inclusive
     * @param start The range lower bound, inclusive
     * @param end   The range upper bound, inclusive
     */
    private class IntSet(start: Int, end: Int) {
        /**
         * A set of ranges representing the remaining integers in this set
         */
        private val ranges: NavigableMap<Int, Int> = TreeMap()

        init {
            ranges[start] = end
        }

        fun ranges(): Set<Map.Entry<Int, Int>> {
            return ranges.entries
        }

        /**
         * Remove an integer from this IntSet
         *
         * @param value The integer to remove
         */
        fun remove(value: Int) {
            val range = ranges.floorEntry(value)
            if (range == null || range.value < value) {
                return
            }

            val lower = range.key
            val upper = range.value

            if (upper > value) {
                reinsert(value + 1, upper)
            }
            reinsert(lower, min(upper, (value - 1)))
        }

        private fun reinsert(start: Int, end: Int) {
            if (end < start) {
                ranges.remove(start)
            } else {
                ranges[start] = end
            }
        }
    }
}