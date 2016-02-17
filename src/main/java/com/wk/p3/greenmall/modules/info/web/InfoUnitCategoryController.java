package com.wk.p3.greenmall.modules.info.web;

import com.wk.p3.greenmall.common.persistence.Page;
import com.wk.p3.greenmall.common.utils.StringUtils;
import com.wk.p3.greenmall.common.web.BaseController;
import com.wk.p3.greenmall.modules.info.entity.Gcategory;
import com.wk.p3.greenmall.modules.info.entity.InfoUnitCategory;
import com.wk.p3.greenmall.modules.info.entity.Unit;
import com.wk.p3.greenmall.modules.info.service.InfoUnitCategoryService;
import com.wk.p3.greenmall.modules.info.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by liujiabao on 2016/1/7 0007.
 */
@Controller
@RequestMapping(value = "${adminPath}/info/infoUnitCategory")
public class InfoUnitCategoryController extends BaseController {

    @Autowired
    private InfoUnitCategoryService infoUnitCategoryService;
    @Autowired
    private UnitService unitService;

    @ModelAttribute
    public InfoUnitCategory get(@RequestParam(required=false) String id) {
        if (StringUtils.isNotBlank(id)){
            return infoUnitCategoryService.get(id);
        }else{
            return new InfoUnitCategory();
        }
    }

    @RequestMapping(value = {"getInfoUnitCategorysByGcategory", ""})
    public String list(InfoUnitCategory infoUnitCategory, HttpServletRequest request, HttpServletResponse response, Model model) {

        Page<InfoUnitCategory> page = infoUnitCategoryService.getInfoUnitCategorysByGcategory(new Page<InfoUnitCategory>(request, response),infoUnitCategory);
        model.addAttribute("page", page);
        return "modules/info/unit/infoUnitCategoryList";
    }

    @RequestMapping(value = "form")
    public String form(InfoUnitCategory infoUnitCategory, Model model) {
        model.addAttribute("unit", infoUnitCategory);
        List<Unit> units = unitService.findAllExceptUnitIds(infoUnitCategory);
        model.addAttribute("units",units);
        return "modules/info/unit/infoUnitCategoryForm";
    }

    @RequestMapping(value = "save")//@Valid 
    public String save(InfoUnitCategory infoUnitCategory, Model model, RedirectAttributes redirectAttributes) {

        if (!beanValidator(model, infoUnitCategory)){
            return form(infoUnitCategory, model);
        }


        try {
            Integer unitId = infoUnitCategory.getUnitId();
            Unit unit = unitService.get(unitId.toString());
            infoUnitCategory.setStatue(unit.getStatue());
            infoUnitCategory.setUnitName(unit.getName());
            infoUnitCategory.setUnitCode(unit.getCode());
            if(infoUnitCategory.getIsDefault()==1) {
                //查询此类别下是否已经有默认单位了
                InfoUnitCategory defaultUnitCategory = new InfoUnitCategory();
                defaultUnitCategory.setGcategoryId(infoUnitCategory.getGcategoryId());
                defaultUnitCategory.setIsDefault(1);
                List<InfoUnitCategory> defaultR = infoUnitCategoryService.getInfoUnitCategorysByGcategory(defaultUnitCategory);
                if (defaultR.size() > 0) {
                    addMessage(redirectAttributes, "保存单位'" + infoUnitCategory.getUnitCode() + "'失败,此类型下已经存在默认单位了，默认单位只能设置一个！");
                    String s = "redirect:" + adminPath + "/info/infoUnitCategory/form?gcategoryId=" + infoUnitCategory.getGcategoryId() ;
                    if(infoUnitCategory.getId()!=null){
                        s += "&id=" + infoUnitCategory.getUnitId();
                    }
                    return s;
                }
            }
            infoUnitCategoryService.save(infoUnitCategory);
        }catch (Exception e){

            e.printStackTrace();
                addMessage(redirectAttributes, "保存单位'" + infoUnitCategory.getUnitCode() + "'失败");

            return "redirect:" + adminPath + "/info/infoUnitCategory/form?gcategoryId="+infoUnitCategory.getGcategoryId()+"&id="+infoUnitCategory.getUnitId();
        }
        addMessage(redirectAttributes, "保存单位'" + infoUnitCategory.getUnitCode() + "'成功");
        return "redirect:" + adminPath + "/info/infoUnitCategory?gcategoryId="+infoUnitCategory.getGcategoryId();
    }

    @RequestMapping(value = "delete")
    public String delete(InfoUnitCategory unit, RedirectAttributes redirectAttributes) {

        infoUnitCategoryService.delete(unit);
        addMessage(redirectAttributes, "删除字典成功");
        return "redirect:" + adminPath + "/info/infoUnitCategory/getInfoUnitCategorysByGcategory?gcategoryId="+unit.getGcategoryId();
    }


}
