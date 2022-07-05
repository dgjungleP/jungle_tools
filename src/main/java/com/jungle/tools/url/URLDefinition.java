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
        buildType(definition, url);
        buildHost(definition, url);
        buildPort(definition, url);
        buildPath(definition, url);
        buildQueryString(definition, url);
        return definition;
    }

    private static void buildType(URLDefinition definition, String url) {
    }

    private static void buildHost(URLDefinition definition, String url) {
    }

    private static void buildPort(URLDefinition definition, String url) {
    }

    private static void buildPath(URLDefinition definition, String url) {
    }

    private static void buildQueryString(URLDefinition definition, String url) {
    }

}
