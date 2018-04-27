package ru.job4j.map;

import ru.job4j.set.SimpleHashTable;

import java.util.Iterator;

/**
 * This class is simple HashMap.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class SimpleHashMap<K, V> implements Iterable<SimpleHashMap.Bucket<K, V>> {
    /**
     * Simple Hash Table.
     */
    private SimpleHashTable<Bucket> data;
    /**
     * Size of map.
     */
    private int size = 0;

    public SimpleHashMap() {
        this.data = new SimpleHashTable<>();
    }

    /**
     * Return size of map.
     * @return size of map.
     */
    public int getSize() {
        return size;
    }

    /**
     * Add new element to map.
     * @param key - key.
     * @param value - value.
     * @return {@code true} if element added to map.
     */
    public boolean insert(K key, V value) {
        boolean insert = false;
        Bucket<K, V> bucket = new Bucket<>(key, value);
        if (!this.data.contains(bucket)) {
            data.add(bucket);
            ++size;
            insert = true;
        }
        return insert;
    }

    /**
     * Get value from map by key.
     * @param key - key.
     * @return value.
     */
    public V get(K key) {
        V result = null;
        Bucket<?, ?> bucket = data.get(new Bucket<K, V>(key, null));
        if (bucket != null) {
            result = (V) bucket.getValue();
        }
        return result;
    }

    /**
     * Delete bucket form map.
     * @param key - key.
     * @return {@code true} if bucket deleted.
     */
    public boolean delete(K key) {
        boolean deleted = false;
        Bucket<?, ?> bucket = data.get(new Bucket<K, V>(key, null));
        if (bucket != null) {
            data.remove(bucket);
            --size;
            deleted = true;
        }
        return deleted;
    }

    /**
     * Return iterator.
     * @return iterator.
     */
    @Override
    public Iterator<Bucket<K, V>> iterator() {
        return new Iterator<Bucket<K, V>>() {

            Iterator it = data.iterator();

            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public Bucket<K, V> next() {
                return (Bucket<K, V>) it.next();
            }
        };
    }

    static class Bucket<K, V> {
        private final K key;
        private V value;

        private Bucket(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }

        @Override
        public int hashCode() {
            return this.key.hashCode();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Bucket)) {
                return false;
            }
            Bucket<?, ?> bucket = (Bucket<?, ?>) o;
            return this.key.equals(bucket.key);
        }
    }
}
