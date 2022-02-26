package com.jungle.tools.fieds.impl;


import com.jungle.tools.fieds.DemoClazz;
import com.jungle.tools.fieds.annotats.User;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class UserFlushManager extends DefaultFlushManager<DemoClazz> {

    @Override
    public Class<?> getType() {
        return User.class;
    }

    @Override
    protected Object flushDate(Method getMethod, DemoClazz vo, Field field) throws Exception {
        String userName = (String) getMethod.invoke(vo);
        return metaManager.flushName(userName);
    }
}
