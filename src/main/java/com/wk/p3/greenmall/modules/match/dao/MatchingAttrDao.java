package com.wk.p3.greenmall.modules.match.dao;

import java.util.List;

import com.wk.p3.greenmall.common.persistence.CrudDao;
import com.wk.p3.greenmall.common.persistence.annotation.MyBatisDao;
import com.wk.p3.greenmall.modules.match.entity.MatchingAttr;

/**
 * @author zhaomeng
 * @description  匹配字段Dao接口
 * 2015年12月22日
 */
@MyBatisDao
public interface MatchingAttrDao extends CrudDao<MatchingAttr>  {
	/**
	 * 通过供求类型查询匹配条件
	 * @param type
	 * @return
	 */
	List<MatchingAttr> findMathcingRelationByType(String type);
	
}
