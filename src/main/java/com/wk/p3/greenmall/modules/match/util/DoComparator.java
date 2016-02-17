package com.wk.p3.greenmall.modules.match.util;

import java.io.Serializable;
import java.util.Comparator;

/**
 * @author zhaomeng
 * @description 重写comparator接口,实现序列化 
 * 2015年12月25日
 */
public class DoComparator implements Serializable,Comparator<Double>{

	private static final long serialVersionUID = 1L;
	
	public int compare(Double o1, Double o2) {
		return -o1.compareTo(o2);
	}
}
