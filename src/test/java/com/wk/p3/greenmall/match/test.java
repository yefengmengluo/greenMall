package com.wk.p3.greenmall.match;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import com.wk.p3.greenmall.modules.info.entity.Info;

public class test {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void test() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		Info info=new Info();
		info.setGoodsId("aaaaa");
		Field f = info.getClass().getDeclaredField("goodsId");
		f.setAccessible(true);
		String object = (String) f.get(info);
		System.out.println(object); 
	}
	@Test
	public void test2() {
		Date date = new Date("Mon Dec 21 10:23:00 CST 2015");
		Long timeStemp = date.getTime();
		System.out.println(timeStemp);
		Date f = new Date("Mon Dec 21 10:23:00 CST 2015");
		String c = new SimpleDateFormat("yyyy-MM-dd").format(f);
		System.out.println(c);
	}
	

}
