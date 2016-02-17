package com.wk.p3.greenmall.modules.info.service;


import com.wk.p3.greenmall.modules.info.dao.GcategoryDao;
import com.wk.p3.greenmall.modules.info.utils.BasicCategory;
import com.wk.p3.greenmall.modules.info.entity.Gcategory;
import com.wk.p3.greenmall.modules.info.utils.InfoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuyanqing on 2015/12/12.
 */

@Service
@Transactional(readOnly = true)
public class GcategoryService  extends CataService<GcategoryDao, Gcategory> {



    public List<Gcategory> findAll(){
        return InfoUtils.getGcategoryAllList();
    }

    public List<Gcategory> findList(Boolean isAll){
        List<Gcategory> list = null;
        if (isAll != null && isAll){
            list = InfoUtils.getGcategoryAllList();
        }
        return list;
    }

    @Transactional(readOnly = true)
    public List<Gcategory> findList(Gcategory Gcategory){
        return dao.findByParentIdsLike(Gcategory);
    }
    @Transactional(readOnly = true)
    public List<Gcategory> getList(Gcategory Gcategory){
        return dao.getList(Gcategory);
    }
    @Transactional(readOnly = true)
    public List<Gcategory> getListByParent(Gcategory Gcategory){
        return dao.getListByParent(Gcategory);
    }

    @Transactional(readOnly = false)
    public Integer save(Gcategory Gcategory) {
       Integer i = super.save(Gcategory);
        InfoUtils.removeCache(InfoUtils.CACHE_GCATEGORY_ALL_LIST);
        return i;
    }

    @Transactional(readOnly = false)
    public String deleteGcategory(Gcategory gcategory){
        String message = "";
        List<Gcategory> gcategories = getListByParent(gcategory);
        for(Gcategory gcategory1:gcategories){
            deleteGcategory(gcategory1);
            delete(gcategory1);
        }
        delete(gcategory);
        InfoUtils.removeCache(InfoUtils.CACHE_GCATEGORY_ALL_LIST);
        return message;
    }

    @Transactional(readOnly = false)
    public Integer delete(Gcategory Gcategory) {
        super.delete(Gcategory);
        InfoUtils.removeCache(InfoUtils.CACHE_GCATEGORY_ALL_LIST);
        return null;
    }
    @Transactional(readOnly = true)
    public Gcategory getByCode(Gcategory gcategory) {
        return dao.getByCode(gcategory);
    }

    /**
     * 根据CODE枚举类得到子类别LIST
     *
     * @return
     */
    public List<Gcategory> getCategories(BasicCategory bc) {
        Gcategory gcategory = new Gcategory();
        gcategory.setCode(bc.getCode());
        Gcategory gy = getByCode(gcategory);
        Gcategory gy2 = new Gcategory();
        gy2.setParent(gy);
        List<Gcategory> list = getList(gy2);
        return list;
    }
    /**
     * 根据CODE枚举类得到类别Gcategory
     * @return
     */
    private Gcategory getCategory(BasicCategory bc) {
        Gcategory gcategory = new Gcategory();
        gcategory.setCode(bc.getCode());
        return getByCode(gcategory);
    }

    /**
     * 得到子类别
     *
     * @return
     */
    public List<Gcategory> getChildCategories(Gcategory gy) {
        Gcategory gy2 = new Gcategory();
        gy2.setParent(gy);
        List<Gcategory> list = getList(gy2);
        return list;
    }
    /**
     * 得到孙类别
     *
     * @return
     */
    public List<Gcategory> getGrandChildCategories(Gcategory gy) {
        List<Gcategory> grandList=new ArrayList<Gcategory>();
        Gcategory gy2 = new Gcategory();
        gy2.setParent(gy);
        List<Gcategory> list =getList(gy2);
        for(int i=0;i<list.size();i++){
            List<Gcategory> list2 = getChildCategories(list.get(i));
            for(int j=0;j<list2.size();j++){
                grandList.add(list2.get(j));
            }
        }
        return grandList;
    }


    /**
     * 根据产品id得到产品父类所在层所有信息
     * @param goodsId
     * @return
     */
    public List<Gcategory> getParentsSiblingsGcategorysByGoodsId(Integer goodsId){
        //得到本类
        Gcategory gcategory = get(goodsId);
        //得到父类的父类 的所有子类
        List<Gcategory> result = new ArrayList<Gcategory>();
        System.out.println(gcategory);
        System.out.println(gcategory.getParent());
        Gcategory g = get(gcategory.getParent().getId()).getParent();
        if(g!=null){
            result = getListByParent(g);
        }
        return result;
    }


}
