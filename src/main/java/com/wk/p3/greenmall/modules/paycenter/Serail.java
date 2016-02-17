package com.wk.p3.greenmall.modules.paycenter;

import java.util.Date;

import com.wk.p3.greenmall.common.utils.DateUtils;

public class Serail {

	/**
	 * 获得流水
	 */
	public static String getSerail(){
		Date date=new Date();
		return DateUtils.formatDate(date, "yyyyMMddHHmmss")+date.getTime();
	}
	
	public static void main(String[] args) {
		System.out.println(getSerail());
	}
}
