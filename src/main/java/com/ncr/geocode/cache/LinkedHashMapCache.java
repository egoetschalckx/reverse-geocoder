package com.ncr.geocode.cache;

import com.ncr.geocode.models.Address;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class LinkedHashMapCache<K, V> implements Cache<K, V>   {

    private final LinkedHashMap<K, V> linkedHashMap;

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

        throw new NoSuchElementException();
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
