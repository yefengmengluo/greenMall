package com.wk.p3.greenmall.modules.msg.exception;

import java.io.IOException;

/**
 * Created by cc on 15-12-14.
 */
public class SendException extends IOException {

    public SendException() {
        super();
    }

    public SendException(String message) {
        super(message);
    }

    public SendException(String message, Throwable cause) {
        super(message, cause);
    }

    public SendException(Throwable cause) {
        super(cause);
    }
}
