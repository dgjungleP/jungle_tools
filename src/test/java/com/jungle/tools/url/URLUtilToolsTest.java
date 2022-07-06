package com.jungle.tools.url;


import org.junit.jupiter.api.Test;

import java.util.Optional;

public class URLUtilToolsTest {
    private String baseUrl = "http://hello.org.com/hhh/v2/39?k=a&b=c&q=1,2,3";
    private String badUrl = "htt://hello.org.com/hhh/v2/39?k=a&b=c&q=1,2,3";
    private String portUrl = "http://hello.org.com:9965/hhh/v2/39?k=a&b=c&q=1,2,3";


    @Test
    public void baseTest() {
        checkDefinition(baseUrl);
    }

    @Test
    public void baseBadTest() {
        checkDefinition(badUrl);
    }

    @Test
    public void basePortTest() {
        checkDefinition(portUrl);
    }

    private void checkDefinition(String url) {
        Optional<URLDefinition> parse = URLUtils.parse(url);
        parse.ifPresent(System.out::println);
    }
}
