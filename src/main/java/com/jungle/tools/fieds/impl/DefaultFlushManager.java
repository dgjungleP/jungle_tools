package com.jungle.tools.fieds.impl;


import com.jungle.tools.fieds.FlushManager;
import com.jungle.tools.fieds.MetaManager;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class DefaultFlushManager<T> implements FlushManager<T> {
    MetaManager metaManager;

    @Override
    public void doFlush(T vo) {
        List<Field> fieldList = getFlushData(vo);
        try {
            doFlush(vo, fieldList);
        } catch (Exception e) {
        } finally {
        }
    }


    private List<Field> getFlushData(T vo) {
        Class<?> clazz = vo.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        List<Field> fieldList = Arrays.asList(declaredFields);
        return filterFieldList(fieldList, getType());
    }


    public abstract Class<?> getType();

    protected abstract Object flushDate(Method getMethod, T vo, Field field) throws Exception;


    protected void doFlush(T vo, List<Field> fieldList) throws Exception {
        for (Field field : fieldList) {
            Method getMethod = getMethod(vo.getClass(), MethodTYpe.GET, field);
            Method setMethod = getMethod(vo.getClass(), MethodTYpe.SET, field);
            setMethod.invoke(vo, flushDate(getMethod, vo, field));
        }
    }


    public Method getMethod(Class<?> clazz, MethodTYpe type, Field field) throws Exception {
        String name = field.getName();
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        name = type.name().toLowerCase() + name;
        return type.equals(MethodTYpe.GET) ?
                clazz.getDeclaredMethod(name) :
                clazz.getDeclaredMethod(name, field.getType());
    }

    public List<Field> filterFieldList(List<Field> fieldList, Class<?> annotationType) {
        List<Field> result = new ArrayList<>();
        for (Field field : fieldList) {
            Annotation[] annotations = field.getDeclaredAnnotations();
            List<Class<?>> annotationList = Arrays.stream(annotations).map(Annotation::annotationType).collect(Collectors.toList());
            if (annotationList.contains(annotationType)) {
                result.add(field);
            }
        }
        return result;

    }

    enum MethodTYpe {
        GET, SET
    }
}
