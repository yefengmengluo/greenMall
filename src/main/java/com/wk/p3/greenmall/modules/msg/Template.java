package com.wk.p3.greenmall.modules.msg;

import java.io.Serializable;

/**
 * 模板基类
 * Created by cc on 15-12-14.
 */
public interface Template extends Serializable{
    /**
     * 得到模板内容
     * @return
     */
    public String getTemplate();

    /**
     * 得到模板业务编号
     * @return
     */
    public String getCode();

    /**
     * 设置模板内容
     * @param content
     */
    void setTemplate(String content);
}
