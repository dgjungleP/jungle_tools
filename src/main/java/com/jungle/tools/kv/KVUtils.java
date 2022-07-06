package com.jungle.tools.kv;

import com.jungle.tools.clazz.ObjectUtils;
import com.jungle.tools.clazz.exception.ClazzNotSupportException;

import java.lang.reflect.InvocationTargetException;

public class KVUtils {
    private KVUtils() {
    }

    public static KVPair<Object, Object> queryKV(String kvString, String split) throws IllegalAccessException, InvocationTargetException, InstantiationException, ClazzNotSupportException {
        return queryKV(kvString, split, Object.class, Object.class);
    }

    public static <K> KVPair<K, Object> queryKV(String kvString, String split, Class<K> keyClazz) throws IllegalAccessException, InvocationTargetException, InstantiationException, ClazzNotSupportException {
        return queryKV(kvString, split, keyClazz, Object.class);
    }


    public static <K, V> KVPair<K, V> queryKV(String kvString, String split, Class<K> keyClazz, Class<V> valueClazz) throws IllegalAccessException, InstantiationException, InvocationTargetException, ClazzNotSupportException {
        KVPair<K, V> result = new KVPair<>();
        String[] kvSplit = kvString.split(split);
        String key;
        String value;
        if (kvSplit.length < 1) {
            return result;
        } else if (kvSplit.length < 2) {
            key = kvSplit[0].trim();
            value = "";
        } else {
            key = kvSplit[0].trim();
            value = kvSplit[1].trim();
        }
        result.setKey(ObjectUtils.parse(key, keyClazz));
        result.setValue(ObjectUtils.parse(value, valueClazz));
        return result;
    }
}
