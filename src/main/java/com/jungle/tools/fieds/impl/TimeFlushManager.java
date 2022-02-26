package com.jungle.tools.fieds.impl;


import com.jungle.tools.fieds.DemoClazz;
import com.jungle.tools.fieds.annotats.Time;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TimeFlushManager extends DefaultFlushManager<DemoClazz> {

    @Override
    public Class<?> getType() {
        return Time.class;
    }

    @Override
    protected Object flushDate(Method getMethod, DemoClazz vo, Field field) throws Exception {
        Time annotation = field.getDeclaredAnnotation(Time.class);
        String time = (String) getMethod.invoke(vo);
        return metaManager.flushDate(time, annotation.offset(), annotation.unit());
    }

}
