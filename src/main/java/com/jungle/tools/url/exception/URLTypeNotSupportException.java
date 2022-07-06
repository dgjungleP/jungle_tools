package com.jungle.tools.url.exception;

public class URLTypeNotSupportException extends RuntimeException {

    public URLTypeNotSupportException() {
        super();
    }

    public URLTypeNotSupportException(String message) {
        super(message);
    }

    public URLTypeNotSupportException(String message, Throwable cause) {
        super(message, cause);
    }


}
