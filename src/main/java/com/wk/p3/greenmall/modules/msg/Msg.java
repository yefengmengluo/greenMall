package com.wk.p3.greenmall.modules.msg;

import java.io.Serializable;
import java.util.Map;

/**
 * 通过模板和数据构造出消息对象
 * Created by cc on 15-12-14.
 */
public interface Msg extends Serializable {
    /**
     * 获得模板
     * @return
     */
    public Template getTemplate();
    /**
     * 设置模板
     * @param template
     */
    public void setTemplate(Template template);

    /**
     * 设置模型,即树形名值对
     * @param model
     */
    public void setModel(Map model);

    /**
     * 取得模型
     * @return
     */
    public Map<String,?> getModel();

    /**
     * 得到消息内容
     * @return
     */
    public String toMsg();


}
