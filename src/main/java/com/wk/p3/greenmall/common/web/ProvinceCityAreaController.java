package com.wk.p3.greenmall.common.web;

import com.wk.p3.greenmall.modules.sys.entity.Area;
import com.wk.p3.greenmall.modules.sys.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 省市区 插件
 * Created by liujiabao on 2016/1/20 0020.
 */
@Controller
@RequestMapping(value = "sys/area")
public class ProvinceCityAreaController {
    @Autowired
    private AreaService areaService;


    /**
     * 得到province
     */
    @ResponseBody
    @RequestMapping(value = "ajaxProvince")
    public List<Area> ajaxProvince() {
        List<Area> provinces = areaService.ajaxAllProvince();
        return provinces;
    }

    /**
     * 根据provinceId 得到citys
     */
    @ResponseBody
    @RequestMapping(value = "ajaxCity")
    public List<Area> ajaxCity(String provinceId) {
        List<Area> citys = areaService.ajaxAllCityByPovinceId(provinceId);
        return citys;
    }
    /**
     * 根据cityId得到areas
     */
    @ResponseBody
    @RequestMapping(value = "ajaxArea")
    public List<Area> ajaxArea(String cityId) {
        List<Area> areas = areaService.ajaxAllAreaByCityId(cityId);
        return areas;
    }


    /**
     * 得到所有的省市县 数据
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getAllProvinceCityAndAreas")
    public List<Area> getAllProvinceCityAndAreas(){

        List<Area> areas = areaService.getAllProvinceCityAndAreas();
        return areas;
    }
}
