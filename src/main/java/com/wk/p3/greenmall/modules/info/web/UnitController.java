package com.wk.p3.greenmall.modules.info.web;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wk.p3.greenmall.common.config.Global;
import com.wk.p3.greenmall.common.persistence.Page;
import com.wk.p3.greenmall.common.utils.StringUtils;
import com.wk.p3.greenmall.common.web.BaseController;
import com.wk.p3.greenmall.modules.info.entity.Unit;
import com.wk.p3.greenmall.modules.info.entity.UnitRelation;
import com.wk.p3.greenmall.modules.info.service.InfoUnitCategoryService;
import com.wk.p3.greenmall.modules.info.service.UnitRelationService;
import com.wk.p3.greenmall.modules.info.service.UnitService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by liujiabao on 2016/1/7 0007.
 */
@Controller
@RequestMapping(value = "${adminPath}/info/unit")
public class UnitController  extends BaseController {

    @Autowired
    private UnitService unitService;
    @Autowired
    private UnitRelationService unitRelationService;
    @Autowired
    private InfoUnitCategoryService infoUnitCategoryService;

    @ModelAttribute
    public Unit get(@RequestParam(required=false) String id) {
        if (StringUtils.isNotBlank(id)){
            return unitService.get(id);
        }else{
            return new Unit();
        }
    }

    @RequestMapping(value = {"list", ""})
    public String list(Unit unit, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Unit> page = unitService.findPage(new Page<Unit>(request, response), unit);
        List<Unit> units = page.getList();
        List<Unit> result = new ArrayList<Unit>();
        UnitRelation unitRelation = new UnitRelation();
        for(Unit u:units){
            unitRelation.setFromUnitCode(u.getCode());
            List<UnitRelation> unitRelations = unitRelationService.findList(unitRelation);
            u.setUnitRelations(unitRelations);
            result.add(u);
        }
        page.setList(result);

        //得到所有的可以标准化的单位
        Unit numberUnit = new Unit();
        numberUnit.setStatue(0);
        List<Unit> numberUnits = unitService.findList(numberUnit);

        model.addAttribute("page", page);
        model.addAttribute("numberUnits", numberUnits);
        return "modules/info/unit/unitList";
    }

    @RequestMapping(value = "form")
    public String form(Unit unit, Model model) {
        model.addAttribute("unit", unit);
        return "modules/info/unit/unitForm";
    }

    @RequestMapping(value = "save")//@Valid 
    public String save(Unit unit, Model model, RedirectAttributes redirectAttributes) {

        if (!beanValidator(model, unit)){
            return form(unit, model);
        }


        try {
            unitService.save(unit);
        }catch (Exception e){
            System.out.println(e.getCause());
            if(e.getCause().toString().contains("uk_code")){
                addMessage(redirectAttributes, "单位'" + unit.getCode() + "'重复");
            }else {
                addMessage(redirectAttributes, "保存单位'" + unit.getCode() + "'失败");
            }
            return "redirect:" + adminPath + "/info/unit/form";
        }
        addMessage(redirectAttributes, "保存单位'" + unit.getCode() + "'成功");
        return "redirect:" + adminPath + "/info/unit";
    }

    @RequestMapping(value = "delete")
    public String delete(Unit unit, RedirectAttributes redirectAttributes) {

        unitService.delete(unit);
        addMessage(redirectAttributes, "删除单位成功");
        return "redirect:" + adminPath + "/info/unit";
    }
    @RequestMapping(value = "deleteUnitRelation")
    public String deleteUnitRelation(UnitRelation unitRelation, RedirectAttributes redirectAttributes) {

        unitRelationService.delete(unitRelation);
        addMessage(redirectAttributes, "删除单位成功");
        return "redirect:" + adminPath + "/info/unit";
    }

    @ResponseBody
    @RequestMapping(value="saveNewUnitRelation")
    public String saveNewUnitRelation(String id,String multipliers,String toUnitIds, RedirectAttributes redirectAttributes){
        logger.info(id.toString());
        logger.info(multipliers);
        logger.info(toUnitIds);
        String message = "";
        Unit unit = unitService.get(id);
        String[] multipliersArray = multipliers.split(";");
        String[] toUnitIdsArray = toUnitIds.split(";");
        int i =0;
        for(String multiplier:multipliersArray){
             Unit toUnit = unitService.get(toUnitIdsArray[i]);
            Double m = Double.parseDouble(multiplier);
            UnitRelation unitRelation = new UnitRelation();
            unitRelation.setFromUnitCode(unit.getCode());
            unitRelation.setFromUnitName(unit.getName());
            unitRelation.setMultiplier(m);
            unitRelation.setToUnitCode(toUnit.getCode());
            unitRelation.setToUnitName(toUnit.getName());
            List<UnitRelation> unitRelations = unitRelationService.findList(unitRelation);
            if(unitRelations.size()==0) {
                try {
                    unitRelationService.save(unitRelation);
                }catch (Exception e){
                    logger.error(e.getMessage());
                    message = "保存"+toUnit.getName()+"失败！";
                }
            }

            i++;
        }


        return  message;
    }


}
