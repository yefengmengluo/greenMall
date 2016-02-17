package com.wk.p3.greenmall.modules.msg.exception;

/**
 * Created by cc on 15-12-19.
 */
public class FailException extends SendException {

    public FailException() {
        super();
    }

    public FailException(String message) {
        super(message);
    }

    public FailException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailException(Throwable cause) {
        super(cause);
    }
}
