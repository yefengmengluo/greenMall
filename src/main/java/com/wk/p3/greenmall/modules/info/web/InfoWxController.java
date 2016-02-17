package com.wk.p3.greenmall.modules.info.web;

import com.alibaba.fastjson.JSONObject;
import com.wk.p3.greenmall.common.web.BaseController;
import com.wk.p3.greenmall.modules.advise.service.RecommendService;
import com.wk.p3.greenmall.modules.info.service.InfoUnitCategoryService;
import com.wk.p3.greenmall.modules.info.service.imp.InfoServiceImp;
import com.wk.p3.greenmall.modules.match.service.impl.MatchServiceImpl;
import com.wk.p3.greenmall.modules.restfull.WxCategoryServiceImpl;
import com.wk.p3.greenmall.modules.restfull.WxInfoServiceImp;
import com.wk.p3.greenmall.modules.sys.service.DictService;
import com.wk.p3.greenmall.modules.sys.service.SystemService;
import io.swagger.model.Gcategory;
import io.swagger.model.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zhuyanqing on 2016/1/28.
 */
@Controller
@RequestMapping(value = "wx/info")
public class InfoWxController extends BaseController{

    @Autowired
    InfoServiceImp infoServiceImp;
    @Autowired
    MatchServiceImpl matchServiceImpl;
    @Autowired
    RecommendService recommendService;
    @Autowired
    InfoUnitCategoryService infoUnitCategoryService;
    @Autowired
    SystemService systemService;
    @Autowired
    DictService dictService;
    @Autowired
    WxInfoServiceImp wxInfoServiceImp;
    @Autowired
    WxCategoryServiceImpl wxCategoryServiceImpl;





    @ResponseBody
    @RequestMapping(value = "infoDetail")
    public JSONObject infoDetail(String id){
        JSONObject jsonObject =new JSONObject();
        int code = 0;
        String msg = "";
        ResponseEntity<Info> entity=null;
        try{
            entity = wxInfoServiceImp.getInfoById(id);
        }catch (Exception e){
            e.printStackTrace();
            code=-1;
            msg="很抱歉，失败";
        }
        jsonObject.put("code",code);
        jsonObject.put("msg",msg);
        jsonObject.put("entity",entity);
        return jsonObject;
    }



    /*
    * 发布
    * */
    @ResponseBody
    @RequestMapping(value = "sendSelectNews")
    public JSONObject publishMsgInfo(){
        JSONObject jsonObject =new JSONObject();
        int code = 0;
        String msg = "";
        try{

        }catch (Exception e){
            e.printStackTrace();
            code=-1;
            msg="很抱歉，发布失败";
        }
        jsonObject.put("code",code);
        jsonObject.put("msg",msg);
        return jsonObject;
    }

    /*
    * 类别
    * */

    @ResponseBody
    @RequestMapping(value = "getCategoryByPid")
    public JSONObject getCategoryByPid(String pid){
        JSONObject jsonObject =new JSONObject();
        int code = 0;
        String msg = "";
        ResponseEntity<List<Gcategory>> entity=null;
        try{
            entity = wxCategoryServiceImpl.getCategoryByPid(pid);
        }catch (Exception e){
            e.printStackTrace();
            code=-1;
            msg="很抱歉，发布失败";
        }
        jsonObject.put("entity",entity);
        jsonObject.put("code",code);
        jsonObject.put("msg",msg);
        return jsonObject;
    }

  /*
    * 有yixi意向 * */

    @ResponseBody
    @RequestMapping(value = "addClickInfo")
    public JSONObject addClickInfo(String id,String id2){
        JSONObject jsonObject =new JSONObject();
        int code = 0;
        String msg = "";
        Info info = new Info();
        info.setId(id2);
        ResponseEntity<Void> entity=null;
        try{
            entity = wxInfoServiceImp.addClickInfo(id,info);
        }catch (Exception e){
            e.printStackTrace();
            code=-1;
            msg="很抱歉，失败";
        }
        jsonObject.put("entity",entity);
        jsonObject.put("code",code);
        jsonObject.put("msg",msg);
        return jsonObject;
    }




}
