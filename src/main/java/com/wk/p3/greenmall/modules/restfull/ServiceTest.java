package com.wk.p3.greenmall.modules.restfull;

import com.wk.p3.greenmall.common.utils.SpringContextHolder;
import io.swagger.model.Area;
import io.swagger.model.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by handsome on 2016/2/1.
 */
@Controller
@RequestMapping("/serviceTest")
public class ServiceTest {
    @Autowired
    AreaServiceImp areaServiceImp;

//    @Autowired
//    WxInfoServiceImp wxInfoServiceImp;
    private static WxInfoServiceImp wxInfoServiceImp = SpringContextHolder.getBean(WxInfoServiceImp.class);



    @RequestMapping(value = "getAreaListByPid")
    public ResponseEntity<List<Area>> getAreaListByPid(String s){
        ResponseEntity<List<Area>> o = areaServiceImp.getAreaListByPid(s);
        return o;
    }

    @RequestMapping(value = "addInfo")
    public ResponseEntity<Void> addInfo(){

        String message = "";

        Info info = new Info();
        info.setValue("test消息新增");
        info.setType(Info.TypeEnum.demand);
        io.swagger.model.User user = new io.swagger.model.User();
//        info.setRelationInfoId("1111");
        user.setId("1");
//        info.setAgent(user);
        info.setUser(user);
        ResponseEntity<Void> entity = wxInfoServiceImp.addInfo(info);
        return entity;

    }
    @RequestMapping(value="guessList")
    @ResponseBody
    public ResponseEntity<List<Info>> guessList(){
        ResponseEntity<List<Info>> r = wxInfoServiceImp.guessList();

        return  r;
    }


  /*  @RequestMapping(value = "addlinkedInfo")
    public ResponseEntity<Void> addlinkedInfo(){

        String message = "";

        Info info = new Info();
        info.setValue("test消息新增");
        info.setType(Info.TypeEnum.demand);
        io.swagger.model.User user = new io.swagger.model.User();
        user.setId("1");
        info.setAgent(user);
        info.setUser(user);
        ResponseEntity<Void> entity = wxInfoServiceImp.addlinkedInfo("111", info);
        return entity;

    }*/
    /*@RequestMapping(value = "getInfoListBySpecificInfoId")
    @ResponseBody
    public ResponseEntity<List<Info>> getInfoListBySpecificInfoId(){

        ResponseEntity<List<Info>> entity = wxInfoServiceImp.getInfoListBySpecificInfoId("44419543a0124903acebeb902e447d40");
        return entity;

    }*/

}
