package com.wk.p3.greenmall.modules.msg.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ErrorHandler;

/**
 * Created by cc on 15-12-29.
 */
@Service
public class JmsHandler implements ErrorHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void handleError(Throwable t) {
        logger.error("Error in listener", t);
    }
}
