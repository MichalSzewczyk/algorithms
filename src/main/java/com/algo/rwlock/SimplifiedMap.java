package com.algo.rwlock;

public interface SimplifiedMap<K, V> {
    void put(K key, V value);

    V get(K key);
}
