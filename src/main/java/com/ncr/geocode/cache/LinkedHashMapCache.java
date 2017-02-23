package com.ncr.geocode.cache;

import com.ncr.geocode.exceptions.CacheMissException;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class LinkedHashMapCache<K, V> implements Cache<K, V>   {

    private final Map<K, V> linkedHashMap;

    public LinkedHashMapCache(int initialCapacity) {
        linkedHashMap = new LinkedHashMap<K, V>(initialCapacity + 1) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> entry) {
                return size() > initialCapacity;
            }
        };
    }

    @Override
    public V get(K key) {
        if (linkedHashMap.containsKey(key)) {
            return linkedHashMap.get(key);
        }

        throw new CacheMissException(Objects.toString(key, "<null>"));
    }

    @Override
    public synchronized void put(K key, V value) {
        linkedHashMap.put(key, value);
    }

    @Override
    public List<V> list() {
        return new LinkedList<>(linkedHashMap.values());
    }
}
