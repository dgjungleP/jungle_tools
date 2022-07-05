package com.jungle.tools.url;


import com.sun.istack.internal.NotNull;

import java.util.Map;

public class URLDefinition {
    private String host;
    private int port;
    private URLPath path;
    private Map<String, Object> queryString;
    private URLType type;

    private URLDefinition() {
    }

    protected static URLDefinition build(@NotNull String url) {
        URLDefinition definition = new URLDefinition();
        url = buildType(definition, url);
        url = buildHost(definition, url);
        url = buildPort(definition, url);
        url = buildPath(definition, url);
        url = buildQueryString(definition, url);
        return definition;
    }

    private static String buildType(URLDefinition definition, String url) {
        String[] split = url.split(URLUtils.PREFIX_SPLIT, 1);
        System.out.println(split);
        return url;
    }

    private static String buildHost(URLDefinition definition, String url) {
        return url;
    }

    private static String buildPort(URLDefinition definition, String url) {
        return url;
    }

    private static String buildPath(URLDefinition definition, String url) {
        return url;
    }

    private static String buildQueryString(URLDefinition definition, String url) {
        return url;
    }

}
