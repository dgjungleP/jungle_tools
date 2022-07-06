package com.jungle.tools.clazz;

import com.jungle.tools.clazz.exception.ClazzNotSupportException;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ObjectUtils {

    private ObjectUtils() {
    }


    public static <V> V parse(String valueString, Class<V> targetClazz) throws IllegalAccessException, InvocationTargetException, InstantiationException, ClazzNotSupportException {
        if (targetClazz.getName().equals(Object.class.getName())) {
            return null;
        }
        if (StringUtils.isEmpty(valueString)) {
            return null;
        }
        Constructor<?>[] constructors = targetClazz.getConstructors();
        Constructor<?> constructor = Arrays.stream(constructors)
                .filter(cons -> cons.getParameterCount() == 1)
                .filter(cons -> cons.getParameterTypes()[0].equals(String.class))
                .findAny()
                .orElseThrow(() -> new ClazzNotSupportException(new Exception(), "Not Support the Clazz:" + targetClazz.getName()));
        return (V) constructor.newInstance(valueString);
    }
}
