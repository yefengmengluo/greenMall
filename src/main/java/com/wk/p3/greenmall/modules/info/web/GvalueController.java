package com.wk.p3.greenmall.modules.info.web;

import com.wk.p3.greenmall.common.utils.StringUtils;
import com.wk.p3.greenmall.common.web.BaseController;
import com.wk.p3.greenmall.modules.info.entity.Gcategory;
import com.wk.p3.greenmall.modules.info.entity.Gspec;
import com.wk.p3.greenmall.modules.info.entity.Gvalue;
import com.wk.p3.greenmall.modules.info.service.GcategoryService;
import com.wk.p3.greenmall.modules.info.service.GspecService;
import com.wk.p3.greenmall.modules.info.service.GvalueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhuyanqing on 2015/12/14.
 */
@Controller
@RequestMapping(value = "${adminPath}/info/gvalue")
public class GvalueController extends BaseController {

    @Autowired
    GspecService gspecService;
    @Autowired
    GcategoryService gcategoryService;
    @Autowired
    GvalueService gvalueService;


    //@RequiresPermissions(value = {"info:gspec:edit"})todo保存
    @RequestMapping(value = "editSpecVal")
    public String editSpecVal(String id, Model model) {
        Gspec gspec = new Gspec();
        gspec.setId(Integer.parseInt(id));
        List<Gvalue> list = gspecService.findValsByGspec(gspec);
        Gspec gspec1 = gspecService.get(gspec);
        Gcategory gcategory = gcategoryService.get(gspec1.getGcategory().getId());
        model.addAttribute("gspec", gspec1);
        model.addAttribute("gcategory", gcategory);
        model.addAttribute("list", list);
        return "modules/info/gspecVals";
    }


    @ResponseBody
    @RequestMapping(value = "saveGvalue")
    public String saveGvalue(Gvalue gvalue, Model model,HttpServletRequest request) {

        String gcategoryId= request.getParameter("gcategoryId");
        String gspecId= request.getParameter("gspecId");
        Gcategory gcategory = new Gcategory();
        Gspec gspec = new Gspec();
        try {
            gcategory.setId(Integer.parseInt(gcategoryId));
            gspec.setId(Integer.parseInt(gspecId));
        }catch (Exception e){
            gcategory.setId(null);
            gspec.setId(null);
        }
        gvalue.setGcategory(gcategory);
        gvalue.setGspec(gspec);
        gvalueService.normalSave(gvalue);

        return gspecId;
    }


}
