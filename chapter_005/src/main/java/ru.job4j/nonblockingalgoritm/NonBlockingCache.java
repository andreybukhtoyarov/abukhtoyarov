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
        int currentVersion = cache.get(model.getId()).getVersion();
        return cache.computeIfPresent(model.getId(), (k, v) -> {
            func.apply(k, v);
            check(currentVersion, v.getVersion());
            v.setVersion(currentVersion + 1);
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
     * Get value by id.
     * @param id id.
     * @return value.
     */
    public Base get(int id) {
        return this.cache.get(id);
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
