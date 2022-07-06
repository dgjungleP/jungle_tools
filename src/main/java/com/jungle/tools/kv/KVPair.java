package com.jungle.tools.kv;

public class KVPair<K, V> {
    private K Key;
    private V Value;

    public K getKey() {
        return Key;
    }

    public V getValue() {
        return Value;
    }

    public void setKey(K key) {
        Key = key;
    }

    public void setValue(V value) {
        Value = value;
    }
}
