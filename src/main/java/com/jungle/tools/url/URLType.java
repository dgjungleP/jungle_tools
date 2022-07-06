package com.jungle.tools.url;

import com.sun.org.apache.bcel.internal.generic.RETURN;

public enum URLType {
    HTTP("http"), HTTPS("https"), NOT_SUPPORT("");

    public final String key;

    URLType(String key) {
        this.key = key;
    }

    public static URLType of(String inputTypeKey) {
        for (URLType type : values()) {
            if (type.key.equals(inputTypeKey)) {
                return type;
            }
        }
        return NOT_SUPPORT;
    }
}
