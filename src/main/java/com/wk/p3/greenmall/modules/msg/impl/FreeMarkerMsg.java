package com.wk.p3.greenmall.modules.msg.impl;

import com.wk.p3.greenmall.common.utils.FreeMarkers;
import com.wk.p3.greenmall.modules.msg.Msg;
import com.wk.p3.greenmall.modules.msg.Template;

import java.util.Map;

/**
 * Created by cc on 15-12-26.
 */
public class FreeMarkerMsg implements Msg {

    private Template template;

    private Map model;

    public FreeMarkerMsg(Template template, Map model) {
        this.template = template;
        this.model = model;
    }

    public FreeMarkerMsg() {}

    @Override
    public Template getTemplate() {
        return template;
    }

    @Override
    public void setTemplate(Template template) {
        this.template = template;
    }

    @Override
    public void setModel(Map model) {
        this.model =  model;
    }

    @Override
    public Map<String, ?> getModel() {
        return model;
    }

    @Override
    public String toMsg() {
        return FreeMarkers.renderString(template.getTemplate(),model);
    }


}
