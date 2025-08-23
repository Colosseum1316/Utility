package colosseum.utility.function

import java.util.function.Function

class DefaultHashMap<K, V>(private val defaultPopulator: Function<K, V>) {
    private val map = HashMap<K, V>()

    fun get(key: K): V? {
        map.putIfAbsent(key, defaultPopulator.apply(key))
        return map[key]
    }

    fun put(key: K, value: V) {
        map[key] = value
    }

    fun remove(key: K) {
        map.remove(key)
    }
}
