package ru.job4j.nonblockingalgoritm;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

/**
 * This class is Non Blocking Cache.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class NonBlockingCache {
    /**
     * Cache.
     */
    private final ConcurrentHashMap<Integer, Base> cache;

    /**
     * Create new ConcurrentHashMap<>().
     */
    public NonBlockingCache() {
        this.cache = new ConcurrentHashMap<>();
    }

    /**
     * Add new model to cache.
     * @param model new model.
     */
    public Base add(Base model) {
        return this.cache.put(model.getId(), model);
    }

    /**
     * Do something with model in cache.
     * @param model model in cache.
     * @param func BiFunction.
     * @return model.
     */
    public Base update(Base model, BiFunction<Integer, Base, Base> func) {
        return cache.computeIfPresent(model.getId(), (k, v) -> {
            int version = v.getVersion();
            check(version, v.getVersion());
            v.setVersion(v.getVersion() + 1);
            func.apply(k, v);
            return v;
        });
    }

    /**
     * Delete model from this cache.
     * @param model model.
     * @return deleted model.
     */
    public Base delete(Base model) {
        return this.cache.remove(model.getId());
    }

    /**
     * Check versions. If version is not match - throw OptimisticException.
     * @param first first version.
     * @param second second version.
     */
    private void check(int first, int second) {
        if (first != second) {
            throw new OptimisticException("Versions do not match");
        }
    }

    /**
     * Get size.
     * @return size.
     */
    public int size() {
        return cache.size();
    }

}
