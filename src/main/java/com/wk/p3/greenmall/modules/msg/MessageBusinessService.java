package com.wk.p3.greenmall.modules.msg;


import com.wk.p3.greenmall.modules.msg.exception.SendException;

import java.util.List;
import java.util.Map;

/**
 * 消息发送业务逻辑服务类
 * Created by cc on 16-1-11.
 */

public interface MessageBusinessService {

    /**
     * 短信发送数据(该接口用于1.0开发板)
     * @param code  业务类型编号,在数据字典中取得,比如
     *              取得求购业务编号: DictUtils.getDictValue("demand", "MsgCode", "demand")
     * @param ids   用户手机号
     * @param model 用户要发送的名值对
     */
    public void sendMatchSMSMessage(String code,List<String> ids,Map<String,?> model) throws SendException;


}
