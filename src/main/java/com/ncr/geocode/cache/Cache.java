package com.ncr.geocode.cache;

import java.util.List;

public interface Cache<K, V> {

    V get(K k);
    void put(K k, V v);
    List<V> list();
}
