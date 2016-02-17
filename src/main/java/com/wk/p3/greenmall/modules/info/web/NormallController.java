package com.wk.p3.greenmall.modules.info.web;

import com.wk.p3.greenmall.common.web.BaseController;
import com.wk.p3.greenmall.modules.info.entity.InfoMessage;
import com.wk.p3.greenmall.modules.info.service.imp.InfoServiceImp;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhuyanqing on 2016/1/23.
 */
@Controller
@RequestMapping(value = "${frontPath}")
public class NormallController extends BaseController {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(NormallController.class);

    @Autowired
    InfoServiceImp infoServiceImp;
    /**
     * 发布 纯信息info
     * code=0错误 code=1成功
     * message 信息
     * [code:0,message:xxx]
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value="info/saveInfoMessage")
    public Map<String,Object> saveInfoMessage(InfoMessage infoMessage){

        int code = 1;
        String message = "";
        try {
            infoServiceImp.saveInfoMessage(infoMessage);
        }catch (Exception e){
            logger.error(e.getMessage());
            code = 0;
            message = "保存出错!";
        }
        Map<String,Object> result = new HashMap<String, Object>();
        result.put("code",code);
        result.put("message",message);
        return result;
    }
}
