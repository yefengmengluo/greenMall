package com.wk.p3.greenmall.modules.info.web;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wk.p3.greenmall.common.config.Global;
import com.wk.p3.greenmall.common.utils.StringUtils;
import com.wk.p3.greenmall.common.web.BaseController;
import com.wk.p3.greenmall.modules.info.entity.Gcategory;
import com.wk.p3.greenmall.modules.info.service.GcategoryService;
import com.wk.p3.greenmall.modules.info.utils.InfoUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhuyanqing on 2015/12/11.
 * 商品类别管理
 */
@Controller
@RequestMapping(value = "${adminPath}/info/gcategory")
public class GcategoryController extends BaseController {


    @Autowired
    GcategoryService gcategoryService;


    @RequiresPermissions("info:gcategory:view")
    @RequestMapping(value = {""})
    public String index() {
        return "modules/info/gcategoryIndex";
    }

    @RequiresPermissions("info:gcategory:view")
    @RequestMapping(value = {"list"})
    public String list(Gcategory gcategory, Model model) {
        model.addAttribute("list", gcategoryService.findList(gcategory));
        return "modules/info/gcategoryList";
    }


    /**
     * 获取商品类别JSON数据。
     * @param response
     * @return
     */
    @RequiresPermissions("user")
    @ResponseBody
    @RequestMapping(value = "treeData")
    public List<Map<String, Object>> treeData(@RequestParam(required=false) Boolean isAll, HttpServletResponse response,Gcategory gcategory) {
        boolean flag  = true ;
        List<Map<String, Object>> mapList = Lists.newArrayList();
        //if
        List<Gcategory> list = null;
        if(gcategory.getId() != null){

        }else{
            list =  gcategoryService.findList(flag);
        }
        if(list.size()>0){
            for (int i=0; i<list.size(); i++){
                Gcategory g = list.get(i);
                Map<String, Object> map = Maps.newHashMap();
                map.put("id",g.getId());
                map.put("pId", g.getParentId());
                map.put("pIds", g.getParentIds());
                map.put("name", g.getName());
                mapList.add(map);
            }
        }
        return mapList;
    }
    
    /**
     * 跳转到添加商品类别页面。
     * @param g
     * @return
     */
    @RequiresPermissions("info:gcategory:edit")
	@RequestMapping(value = "form")
	public String form(Gcategory g, Model model) {
        if(g.getParent()!= null){
            g.setParent(gcategoryService.get(g.getParent().getId()));
        }
        if(g.getId() != null){
            g = gcategoryService.get(g.getId());
            g.setParent(gcategoryService.get(g.getParentId()));
        }
		model.addAttribute("gcategory", g);
		return "modules/info/gcategoryForm";
	}

    /**
     * 保存商品类别
     * @param gcategory
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("info:gcategory:edit")
    @RequestMapping(value = "save")
    public String save(Gcategory gcategory, Model model, RedirectAttributes redirectAttributes) {
        if(Global.isDemoMode()){
            addMessage(redirectAttributes, "演示模式，不允许操作！");
            return "redirect:" + adminPath + "/info/gcategory/";
        }
        if (!beanValidator(model, gcategory)){
            return form(gcategory, model);
        }
        gcategoryService.save(gcategory);
        addMessage(redirectAttributes, "保存类别'" + gcategory.getName() + "'成功");
        Integer id = 0==gcategory.getParentId() ? 1 : gcategory.getParentId();
        return "redirect:" + adminPath + "/info/gcategory/list?id="+id+"&parentIds="+gcategory.getParentIds();
    }

    /**
     * 根据产品id得到产品父类所在层所有信息
     * @param goodsId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getGcategorysByPgoodsId")
    public  Map<String,List<Gcategory>> getGcategorysByPgoodsId(Integer goodsId){

        Map<String,List<Gcategory>> result = new HashMap<String, List<Gcategory>>();
        List<Gcategory> lists = gcategoryService.getParentsSiblingsGcategorysByGoodsId(goodsId);
        result.put("parentSiblings", lists);//父类同级
        if(lists.size()>0) {
            Gcategory g = lists.get(0);
            List<Gcategory> childrens = gcategoryService.getChildCategories(g);
            result.put("siblings",childrens);//同级
        }else{
            result.put("siblings",null);
        }
        return result;
    }
    @RequestMapping(value = "delete")
    public String delete(Gcategory gcategory, RedirectAttributes redirectAttributes) {

        String message = "删除成功";
        try {
            message += gcategoryService.deleteGcategory(gcategory);
        }catch (Exception e){
            logger.error(e.getMessage());
            message = "删除失败";
        }
        addMessage(redirectAttributes, message);
//        return "redirect:" + adminPath + "/info/gcategory";
        Gcategory oldGcategory = gcategoryService.get(gcategory.getId());
        Integer id = 0==oldGcategory.getParentId() ? 1 : oldGcategory.getParentId();
        return "redirect:" + adminPath + "/info/gcategory/list?id="+id+"&parentIds="+oldGcategory.getParentIds();
    }
    /**
     * 根据父类取得子类
     * @param g
     * @return
     */
    @ResponseBody
    @RequestMapping(value="getChildCategories")
    public List<Gcategory> getChildCategories(Gcategory g){
        List<Gcategory> result = new ArrayList<Gcategory>();
        result = gcategoryService.getChildCategories(g);
        return result;
    }

    //判断所传入ID（gcategory）是否有之类
    @ResponseBody
    @RequestMapping(value = "ifHasSon")
    public String ifHasSon(String id ,Model model) {
        String message = "";
        Gcategory g = null;
        List<Gcategory> result=null;
        try {
            g = gcategoryService.get(Integer.parseInt(id));
        }catch (Exception e){
            message="-1";
        }
        if(g!=null){
            result=gcategoryService.getChildCategories(g);
        }
        if(result != null && result.size() != 0){
            message="1";
        }else{
            message="0";
        }
        return message;
    }

    /**
     * 根据类别名称自动获取拼音-ajax
     *
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "autoCode", method = RequestMethod.POST)
    @ResponseBody
    public String autoCode(Gcategory g) throws IOException {
        if (StringUtils.isBlank(g.getName())) {
            return null;
        }

        final String pinyin = InfoUtils.getPingYin(g.getName());
        String _pinyin = pinyin;
        for (int i = 1; true; i++) {
            Gcategory gy = new Gcategory();
            gy.setCode(_pinyin);
            gy = gcategoryService.getByCode(gy);

            if (gy == null) {
                return _pinyin;
            } else {
                _pinyin = pinyin + i;
            }
        }
    }

    /**
     * 判断code是否已经存在
     *
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "ifExistCode")
    @ResponseBody
    public String ifExistCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = request.getParameter("code");
        String idStr = request.getParameter("id");
        if (StringUtils.isBlank(code)) {
            return "false";
        }
        Gcategory gy = null;
        if(!"".equals(idStr) && idStr!=null){
            Integer id = Integer.parseInt(idStr);
            gy = gcategoryService.get(id);
        }else{
            gy = new Gcategory();
        }

        gy.setCode(code);
        gy = gcategoryService.getByCode(gy);

        if (gy == null) {
            return "true";
        } else {
            return "false";
        }

    }

}
