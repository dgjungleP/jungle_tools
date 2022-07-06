package com.jungle.tools.url;

import org.apache.commons.lang.StringUtils;

import java.util.Optional;

public class URLPath {
    private URLPath next;
    private String current;

    public static Optional<URLPath> build(String pathString) {
        if (StringUtils.isEmpty(pathString)) {
            return Optional.empty();
        }
        int endIndex = pathString.indexOf(URLUtils.SPLIT, 1);
        endIndex = endIndex > 0 ? endIndex : pathString.length();
        URLPath path = new URLPath();
        build(pathString.substring(endIndex)).ifPresent(path::setNext);
        path.current = pathString.substring(1, endIndex);
        return Optional.of(path);
    }


    public URLPath getNext() {
        return next;
    }

    public void setNext(URLPath next) {
        this.next = next;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    @Override
    public String toString() {
        return "URLPath{" +
                "next=" + next +
                ", current='" + current + '\'' +
                '}';
    }
}
