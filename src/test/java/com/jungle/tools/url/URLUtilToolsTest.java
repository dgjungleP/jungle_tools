package com.jungle.tools.url;


import org.junit.jupiter.api.Test;

import java.util.Optional;

public class URLUtilToolsTest {
    private String baseUrl = "http://hello.org.com/hhh/v2/39?k=a&b=c&q=1,2,3";


    @Test
    public void baseTest() {
        Optional<URLDefinition> parse = URLUtils.parse(baseUrl);
        parse.ifPresent(data -> System.out.println(data));
    }
}
