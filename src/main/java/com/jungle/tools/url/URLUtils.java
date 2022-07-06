package com.jungle.tools.url;


import org.apache.commons.lang.StringUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class URLUtils {
    public static final String SPLIT = "/";
    public static final String PREFIX_SPLIT = "://";
    public static final String PORT_SPLIT = ":";
    private static List<URLType> supportTypeList = new ArrayList<>();

    static {
        supportTypeList.addAll(Arrays.asList(URLType.values()));
    }

    private URLUtils() {
    }


    public static String getHost(String url) {
        String host = "";
        if (!isUrl(url)) {
            return host;
        }
        int index = url.indexOf(PREFIX_SPLIT);
        String urlPath = url.substring(index + PREFIX_SPLIT.length());
        return urlPath.split(SPLIT)[0];
    }

    public static String changeHost(String baseUrl, String targetHost) {
        String host = getHost(baseUrl);
        if (StringUtils.isBlank(host)) {
            return baseUrl;
        }
        return baseUrl.replace(host, targetHost);
    }

    public static Optional<URLDefinition> parse(String url) {
        return isUrl(url) ? Optional.of(URLDefinition.build(url)) : Optional.empty();
    }

    public static Optional<URLDefinition> parse(URL url) {
        return parse(url.toString());
    }

    public static boolean isUrl(String url) {
        if (StringUtils.isBlank(url)) {
            return false;
        }
        return supportTypeList.parallelStream().anyMatch(data -> url.startsWith(data.key));
    }


}
