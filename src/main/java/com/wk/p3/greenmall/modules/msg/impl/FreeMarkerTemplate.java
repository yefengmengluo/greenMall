package com.wk.p3.greenmall.modules.msg.impl;

import com.wk.p3.greenmall.modules.msg.Template;

/**
 * Created by cc on 16-1-11.
 */
public class FreeMarkerTemplate implements Template {

    private String code;
    private String content;

    public FreeMarkerTemplate(String code, String content) {
        this.code = code;
        this.content = content;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String getTemplate() {
        return content;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setTemplate(String content) {
        this.content = content;
    }
}
