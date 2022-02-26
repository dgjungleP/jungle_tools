package com.jungle.tools.fieds.impl;


import com.jungle.tools.fieds.DemoClazz;
import com.jungle.tools.fieds.annotats.Application;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ApplicationFlushManager extends DefaultFlushManager<DemoClazz> {
    @Override
    public Class<?> getType() {
        return Application.class;
    }

    @Override
    protected Object flushDate(Method getMethod, DemoClazz vo, Field field) throws Exception {
        String application = (String) getMethod.invoke(vo);
        return metaManager.flushApplication(application);
    }
}
