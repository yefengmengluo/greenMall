package com.wk.p3.greenmall.modules.msg.exception;

/**
 * Created by cc on 15-12-19.
 */
public class HttpSendException extends SendException {

    public HttpSendException() {
        super();
    }

    public HttpSendException(String message) {
        super(message);
    }

    public HttpSendException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpSendException(Throwable cause) {
        super(cause);
    }
}
