package com.jungle.tools.clazz.exception;

import javax.management.ReflectionException;

public class ClazzNotSupportException extends ReflectionException {
    public ClazzNotSupportException(Exception e, String message) {
        super(e, message);
    }
}
