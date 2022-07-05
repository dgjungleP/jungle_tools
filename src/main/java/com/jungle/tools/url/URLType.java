package com.jungle.tools.url;

public enum URLType {
    HTTP("http"), HTTPS("https");

    public final String key;

    URLType(String key) {
        this.key = key;
    }
}
