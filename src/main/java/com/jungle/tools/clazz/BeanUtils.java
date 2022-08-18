package com.jungle.tools.clazz;

import com.alibaba.fastjson.JSONObject;
import com.jungle.tools.clazz.exception.BeanUtilsException;
import com.jungle.tools.json.JsonUtil;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.*;

public class BeanUtils {

    private BeanUtils() {
    }

    public static String replaceAllPoint(String str) {
        return str.replaceAll("\\.", "_");
    }


    public static <T> T deepTransform(Class<T> clazz, Object src) {
        if (src == null) {
            return null;
        }
        T instance;
        try {
            instance = clazz.newInstance();
        } catch (Exception e) {
            throw new BeanUtilsException(e);
        }
        return deepCopy(instance, src);
    }

    public static <T> T deepCopy(T target, Object source) {
        JSONObject targetJsonObject = JsonUtil.ObjToJsonObjectWithNull(target);
        JSONObject sourceJsonObject = JsonUtil.ObjToJsonObjectWithNull(source);
        JSONObject result = JsonUtil.copyJsonValue(targetJsonObject, sourceJsonObject);
        return (T) result.toJavaObject(target.getClass());
    }


    private static Field deepFindField(Class<?> clazz, String key) {
        Field field = null;
        while (!clazz.getName().equals(Object.class.getName())) {
            try {
                field = clazz.getDeclaredField(key);
                break;
            } catch (Exception e) {
                clazz = clazz.getSuperclass();
            }
        }
        return field;
    }


}
