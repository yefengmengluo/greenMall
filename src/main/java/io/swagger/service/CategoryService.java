package io.swagger.service;

import io.swagger.model.Gcategory;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Created by cc on 16-2-4.
 */
public interface CategoryService {

    /**
     * 通过parentId得到对应的gcategory
     * 如果为空则获得所有分类信息
     * @param pid
     * @return
     */
    public ResponseEntity<List<Gcategory>> getCategoryByPid(String pid);


}
