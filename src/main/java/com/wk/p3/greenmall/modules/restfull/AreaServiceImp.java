package com.wk.p3.greenmall.modules.restfull;



import com.wk.p3.greenmall.common.utils.excel.annotation.ExcelField;
import io.swagger.model.Area;
import io.swagger.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liujiabao on 2016/1/30.
 */
@Service
public class AreaServiceImp implements AreaService {

    @Autowired
    com.wk.p3.greenmall.modules.sys.service.AreaService areaService;
    @Override
    public ResponseEntity<List<Area>> getAreaListByPid(String s) {
        ResponseEntity entity = null;
        try {
            List<Area> areas = new ArrayList<Area>();
            List<com.wk.p3.greenmall.modules.sys.entity.Area> originOs = null;
            if (null == s || "".equals(s)) {
                originOs = areaService.ajaxAllProvince();

            } else {
                originOs = areaService.ajaxAllChildrenByParentId(s);

            }
            for (com.wk.p3.greenmall.modules.sys.entity.Area area : originOs) {
                Area area1 = new Area();
                area1.setId(area.getId());
                area1.setName(area.getName());
                area1.setCode(area.getCode());
                area1.setType(area.getType());
                areas.add(area1);
            }
            if(areas.size()==0){
                entity = new ResponseEntity(HttpStatus.NOT_FOUND);
            }else{
                entity = new ResponseEntity(areas,HttpStatus.OK);
            }
        }catch (Exception e){
            entity = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return entity;
    }
}
