package com.jungle.tools.url;


import com.jungle.tools.url.exception.URLTypeNotSupportException;
import com.sun.istack.internal.NotNull;
import org.apache.commons.lang.StringUtils;

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
        if (StringUtils.isNotBlank(url)) {
            System.out.println(url);
        }
        return definition;
    }

    private static String buildType(URLDefinition definition, String url) {
        String[] split = url.split(URLUtils.PREFIX_SPLIT);
        String inputKey = split[0];
        URLType type = URLType.of(inputKey);
        if (URLType.NOT_SUPPORT.equals(type)) {
            throw new URLTypeNotSupportException("Bad url type with input key:" + inputKey + ".");
        }
        definition.type = type;
        return split[1];
    }

    private static String buildHost(URLDefinition definition, String url) {
        int hostEnd = url.indexOf(URLUtils.SPLIT);
        int portIndex = url.indexOf(URLUtils.PORT_SPLIT);
        int splitIndex = Math.min(hostEnd >= 0 ? hostEnd : Integer.MAX_VALUE, portIndex >= 0 ? portIndex : Integer.MAX_VALUE);
        System.out.println("url.substring(0,splitIndex) = " + url.substring(0, splitIndex));
        System.out.println("url.substring(splitIndex) = " + url.substring(splitIndex));
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

    @Override
    public String toString() {
        return "URLDefinition{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", path=" + path +
                ", queryString=" + queryString +
                ", type=" + type +
                '}';
    }
}
