package com.wk.p3.greenmall.modules.msg.exception;

/**
 * Created by cc on 15-12-19.
 */
public class ErrorException extends SendException {

    public ErrorException() {
        super();
    }

    public ErrorException(String message) {
        super(message);
    }

    public ErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ErrorException(Throwable cause) {
        super(cause);
    }
}
