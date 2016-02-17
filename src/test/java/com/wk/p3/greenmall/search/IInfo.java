package com.wk.p3.greenmall.search;

import java.util.List;

import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import com.wk.p3.greenmall.common.persistence.annotation.MyBatisDao;

@MyBatisDao
public interface IInfo {
	@Select("select id from supply_demand_info")
	@ResultType(List.class)
	public List<String> listInfoIds();
	
}
