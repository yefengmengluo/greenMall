package com.wk.p3.greenmall.modules.info.web;

import com.wk.p3.greenmall.common.utils.StringUtils;
import com.wk.p3.greenmall.common.web.BaseController;
import com.wk.p3.greenmall.modules.info.entity.Gcategory;
import com.wk.p3.greenmall.modules.info.entity.Gspec;
import com.wk.p3.greenmall.modules.info.entity.Gvalue;
import com.wk.p3.greenmall.modules.info.service.GcategoryService;
import com.wk.p3.greenmall.modules.info.service.GspecService;
import com.wk.p3.greenmall.modules.info.service.GvalueService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhuyanqing on 2015/12/14.
 */
@Controller
@RequestMapping(value = "${adminPath}/info/gspec")
public class GspecController extends BaseController {

    @Autowired
    GspecService gspecService;
    @Autowired
    GcategoryService gcategoryService;
    @Autowired
    GvalueService gvalueService;


    @ModelAttribute("gvalue")
    public Gvalue get(@RequestParam(required=false) String id) {
        if (StringUtils.isNotBlank(id)){
            try{
                return gvalueService.get(Integer.parseInt(id));
            }catch(Exception e){
                return new Gvalue();
            }
        }else{
            return new Gvalue();
        }
    }
    /**
     * 根据类别得到规格
     * @param gcategory
     * @param model
     * @return
     */
    @RequestMapping(value = "getSpecValByGcata")
    //@RequiresPermissions(value = {"info:gspec:view","info:gvalue:view"})
    public String getSpecValByGcata(Gcategory gcategory, Model model) {
        Gspec gspec = new Gspec();
        gspec.setGcategory(gcategory);
        Gcategory gcategory1 = gcategoryService.get(gcategory.getId());
        List<Gspec> list = gspecService.findList(gspec);
        StringBuffer sb = null;
        for(int i = 0;i<list.size();i++){
            List<Gvalue> gvalueList = gspecService.findValsByGspec(list.get(i));
            sb = new StringBuffer();
            for( int j = 0;j<gvalueList.size();j++){
                sb.append(gvalueList.get(j).getVal());
                if(j < gvalueList.size()-1){
                    sb.append(",");
                }
            }
            list.get(i).setSpecStrs(sb.toString());
        }
        model.addAttribute("specList",list);
        model.addAttribute("gcategory",gcategory1);
        return "modules/info/gcataToSpecVal";
    }


    /**
     * 根据类别得到规格
     * @param gcategory
     * @return
     */
    @RequestMapping(value = "getSpecValByGcataMap")
    @ResponseBody
    //@RequiresPermissions(value = {"info:gspec:view","info:gvalue:view"})
    public Map getSpecValByGcataMap(Gcategory gcategory) {
        Gspec gspec = new Gspec();
        gspec.setGcategory(gcategory);
        Gcategory gcategory1 = gcategoryService.get(gcategory.getId());
        List<Gspec> list = gspecService.findList(gspec);
        StringBuffer sb = null;
        StringBuffer sbId = null;
        for(int i = 0;i<list.size();i++){
            List<Gvalue> gvalueList = gspecService.findValsByGspec(list.get(i));
            sb = new StringBuffer();
            sbId = new StringBuffer();

            for( int j = 0;j<gvalueList.size();j++){
                sb.append(gvalueList.get(j).getVal());
                sbId.append(gvalueList.get(j).getId());
                if(j < gvalueList.size()-1){
                    sb.append(",");
                    sbId.append(",");
                }
            }
            list.get(i).setSpecStrs(sb.toString());
            list.get(i).setSpecStrsId(sbId.toString());
        }
        Map m = new HashMap();
        m.put("specList",list);
        m.put("gcategory",gcategory1);
        return m;
    }


    /**
     * 从商品类别处添加商品规格
     * @param model
     * @return
     */
    //@RequiresPermissions(value = {"info:gspec:edit"})
    @RequestMapping(value = "form")
    public String form(String id, Model model) {
//        if(gcategory.getId() != null){
//            gcategory = gcategoryService.get(gcategory.getId());
//        }
        //model.addAttribute("gcategory", gcategory);
        //model.addAttribute("gcategoryId",gcategory.getId());
        String name = gcategoryService.get(Integer.parseInt(id)).getName();
        model.addAttribute("gcategoryId",id);
        model.addAttribute("gcategoryName",name);
        return "modules/info/gspecForm";
    }

    //@RequiresPermissions(value = {"info:gspec:edit"})todo保存
    @RequestMapping(value = "save")
    public String save(Gspec gspec, Model model) {
        gspecService.normalSave(gspec);
        int i = 0;
        if(gspec.getGcategory()!=null && gspec.getGcategory().getId()!=null){
            i = gspec.getGcategory().getId();
            return "redirect:" + adminPath + "/info/gspec/getSpecValByGcata?id="+i;
        }else {
            Gspec gspec1 = gspecService.get(gspec.getId());
            i=gspec1.getGcategory().getId();
            return "redirect:" + adminPath + "/info/gspec/getSpecValByGcata?id="+i;
        }
    }

//    //@RequiresPermissions(value = {"info:gspec:edit"})todo保存
//    @RequestMapping(value = "editSpecVal")
//    //public String editSpecVal(Gspec gspec, Model model) {
//    public String editSpecVal(String id, Model model) {
//        Gspec gspec = new Gspec();
//        gspec.setId(Integer.parseInt(id));
//        List<Gvalue> list = gspecService.findValsByGspec(gspec);
//        Gspec gspec1 = gspecService.get(gspec);
//        Gcategory gcategory = gcategoryService.get(gspec1.getGcategory().getId());
//        model.addAttribute("gspec", gspec1);
//        model.addAttribute("gcategory", gcategory);
//        model.addAttribute("list", list);
//        return "modules/info/gspecVals";
//    }

    /**
     * 从商品规格处添加规格值
     * @param gvalue
     * @return
     */
    //@RequiresPermissions(value = {"info:gspec:edit"})
    @RequestMapping(value ="saveGvalue")
    public String saveGvalue(Gvalue gvalue,RedirectAttributes redirectAttributes){
        Integer i = null;
        i = gvalueService.normalSave(gvalue);
        if(i ==1){
            addMessage(redirectAttributes, "保存规格值'" + gvalue.getVal() + "'成功");
        }else{
            addMessage(redirectAttributes, "保存失败");
        }
        return "redirect:" + adminPath + "/info/gspec/editSpecVal?id="+gvalue.getGspec().getId();
    }
    /**
     * 从商品规格处删除规格值
     * @param gvalue
     * @return
     */
    //@RequiresPermissions(value = {"info:gspec:edit"})
    @RequestMapping(value ="delGvalue")
    public String delGvalue(Gvalue gvalue,RedirectAttributes redirectAttributes){
        Integer i = null;
        i = gvalueService.delete(gvalue);
        if(i == 1){
            addMessage(redirectAttributes, "删除成功");
        }else{
            addMessage(redirectAttributes, "删除失败");
        }
        new Date().getTime();
        return "redirect:" + adminPath + "/info/gvalue/editSpecVal?id="+gvalue.getGspec().getId()+"&t="+new Date().getTime();
    }
    /**
     * 根据ID得到规格值
     * @param gvalue
     * @return
     */
    //@RequiresPermissions(value = {"info:gspec:edit"})
    @ResponseBody
    @RequestMapping(value ="getGvalue")
    public Gvalue getGvalue(Gvalue gvalue,Model model){
        System.out.print(gvalue);
        Gvalue g = gvalueService.get(gvalue.getId());
//        model.addAttribute("gcategoryId",g.getGcategory().getId());
//        model.addAttribute("gspecId",g.getGspec().getId());
        gvalue.setGcategory(g.getGcategory());
        gvalue.setGspec(g.getGspec());
        return gvalue;
    }

    /**
     * 从商品类别处添加商品规格
     * @param model
     * @return
     */
    //@RequiresPermissions(value = {"info:gspec:edit"})
    @RequestMapping(value = "form2")
    public String form2(String gspecId, Model model) {
        Gspec gspec = gspecService.get(Integer.parseInt(gspecId));
        model.addAttribute("gspec",gspec);
        Gcategory gcategory = gcategoryService.get(gspec.getGcategory().getId());
        model.addAttribute("gcategory", gcategory);
        return "modules/info/gspecForm";
    }

}
