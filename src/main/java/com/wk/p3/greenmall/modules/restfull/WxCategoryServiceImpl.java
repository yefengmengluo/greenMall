package com.wk.p3.greenmall.modules.restfull;

import com.wk.p3.greenmall.common.utils.SpringContextHolder;
import com.wk.p3.greenmall.modules.info.service.GcategoryService;
import com.wk.p3.greenmall.modules.info.service.HomeService;
import com.wk.p3.greenmall.modules.info.utils.BasicCategory;
import io.swagger.model.Gcategory;
import io.swagger.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuyanqing on 2016/2/14.
 */
@Service
@Transactional(readOnly = true)
public class WxCategoryServiceImpl implements CategoryService {

    private static GcategoryService gcategoryService = SpringContextHolder.getBean(GcategoryService.class);
    private static HomeService homeService = SpringContextHolder.getBean(HomeService.class);

    public ResponseEntity<List<Gcategory>> getCategoryByPid(String pid) {
        List<com.wk.p3.greenmall.modules.info.entity.Gcategory> list = null;
        try {
            com.wk.p3.greenmall.modules.info.entity.Gcategory gcategory = gcategoryService.get(Integer.parseInt(pid));
            list = gcategoryService.getListByParent(gcategory);
        } catch (Exception e) {
            list = null;
        }
        if (list == null || list.size() == 0) {
            list = homeService.getCategories(BasicCategory.fruit);
        }
        List<Gcategory> list2 = new ArrayList<Gcategory>();
        for(int i=0;i<list.size();i++){
            list2.add(transferGcategory(list.get(i)));
        }
        return new ResponseEntity<List<Gcategory>>(list2, HttpStatus.OK);
    }

    public Gcategory transferGcategory(com.wk.p3.greenmall.modules.info.entity.Gcategory g){
        Gcategory gcategory = new Gcategory();
        gcategory.setId(g.getId());
        gcategory.setName(g.getName());
        gcategory.setCode(g.getCode());
        return gcategory;
    }




}
