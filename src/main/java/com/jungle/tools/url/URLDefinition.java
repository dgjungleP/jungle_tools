package com.jungle.tools.url;


import com.jungle.tools.kv.KVPair;
import com.jungle.tools.kv.KVUtils;
import com.jungle.tools.url.exception.URLTypeNotSupportException;
import com.sun.istack.internal.NotNull;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
        String[] split = url.trim().split(URLUtils.PREFIX_SPLIT);
        String inputKey = split[0];
        URLType type = URLType.of(inputKey);
        if (URLType.NOT_SUPPORT.equals(type)) {
            throw new URLTypeNotSupportException("Bad url type with input key:" + inputKey + ".");
        }
        definition.type = type;
        return split[1].trim();
    }

    private static String buildHost(URLDefinition definition, String url) {
        int pathStart = url.indexOf(URLUtils.SPLIT);
        int portIndex = url.indexOf(URLUtils.PORT_SPLIT);
        int splitIndex = Math.min(pathStart >= 0 ? pathStart : Integer.MAX_VALUE, portIndex >= 0 ? portIndex : Integer.MAX_VALUE);
        definition.host = url.substring(0, splitIndex);
        return url.substring(splitIndex).trim();
    }

    private static String buildPort(URLDefinition definition, String url) {
        int pathStart = url.indexOf(URLUtils.SPLIT);
        int portIndex = url.indexOf(URLUtils.PORT_SPLIT);
        if (portIndex < 0 || portIndex > pathStart) {
            return url.trim();
        }
        String port = url.substring(portIndex + 1, pathStart);
        definition.port = Integer.parseInt(port);
        return url.substring(pathStart).trim();
    }

    private static String buildPath(URLDefinition definition, String url) {
        int index = url.indexOf(URLUtils.PARAM_SPLIT);
        if (index >= 0) {
            String pathString = url.substring(0, index);
            Optional<URLPath> pathOptional = URLPath.build(pathString);
            pathOptional.ifPresent(definition::setPath);
        }
        return index < 0 ? "" : url.substring(index).trim();
    }

    private static String buildQueryString(URLDefinition definition, String url) {
        if (StringUtils.isEmpty(url)) {
            return url.trim();
        }
        if (URLUtils.PARAM_SPLIT.equals(url.trim())) {
            return "";
        }
        url = url.substring(url.indexOf(URLUtils.PARAM_SPLIT) + 1);
        String[] split = url.split(URLUtils.QUERY_STRING_SPLIT);
        Map<String, Object> queryStringMap = new HashMap<>();
        for (String queryString : split) {
            KVPair<String, Object> pair = KVUtils.queryKV(queryString, URLUtils.KEY_VALUE_SPLIT, String.class);
            queryStringMap.putIfAbsent(pair.getKey(), pair.getValue());
        }
        definition.setQueryString(queryStringMap);
        return "";
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

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public URLPath getPath() {
        return path;
    }

    public void setPath(URLPath path) {
        this.path = path;
    }

    public Map<String, Object> getQueryString() {
        return queryString;
    }

    public void setQueryString(Map<String, Object> queryString) {
        this.queryString = queryString;
    }

    public URLType getType() {
        return type;
    }

    public void setType(URLType type) {
        this.type = type;
    }
}
