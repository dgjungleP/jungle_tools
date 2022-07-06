package com.jungle.tools.clazz;

import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Constructor;

public class ObjectUtils {

    private ObjectUtils() {
    }


    public static <V> V parse(String valueString, Class<V> targetClazz) {
        if (StringUtils.isEmpty(valueString)) {
            return null;
        }
        Constructor<?>[] constructors = targetClazz.getConstructors();
        System.out.println(constructors);
        System.out.println(valueString);
        return null;
    }
}
