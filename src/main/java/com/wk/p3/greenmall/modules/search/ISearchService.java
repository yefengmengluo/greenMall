package com.wk.p3.greenmall.modules.search;

import java.util.List;

public interface ISearchService {
	/**
	 * 
	 * @param key 关键词
	 * @param type 类型 SearchType
	 * @param start 开始位置
	 * @param rows  返回条数
	 * @param orderby 排序规则
	 * @return
	 */
	 Object search(String key, String type,int start,int rows,String orderby);
	 
	  /**
	   * 
	   * @param id  供求信息id
	   * @param type 类型 SearchType
	   */
	 void del(String id,String type);
	 /**
	  * 
	  * @param list  供求信息的集合对象
	  * @param clazz Object.class
	  */
	 @SuppressWarnings("rawtypes")
	void add(List<Object>list,Class clazz);
}
