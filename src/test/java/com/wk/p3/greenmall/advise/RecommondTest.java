package com.wk.p3.greenmall.advise;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wk.p3.greenmall.modules.advise.entry.Recommend;
import com.wk.p3.greenmall.modules.advise.service.RecommendService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context.xml")
public class RecommondTest {
    @Autowired
	RecommendService recommendService;
    /**
     * 推荐信息插入
     * @throws InterruptedException 
     */
    @Test
    public void testinsert() throws InterruptedException{
    	Recommend recommend=new Recommend();
    	recommend.setCreateDate(new Date());
    	recommend.setStartTime(new Date());
    	recommend.setEndTime(new Date());
    	recommend.setObjectId("test1000");
    	recommend.setObjectType(Recommend.ObjectType.MESSAGE.toString());
    	recommend.setRecommendType(Recommend.RecommendType.COMPANY.toString());
    	recommend.setUserId("10000");
    	recommend.setStatus(-1);
    	recommend.setGoodsType("test");
    	recommendService.insert(recommend);
    	Thread.currentThread().sleep(100000);
    }
    /**
     * 推荐信息更新
     */
    @Test
    public void testupdate(){
    	//推荐信息
    	recommendService.update(Recommend.ObjectType.BUYERS.toString(), Recommend.RecommendType.COMPANY.toString(), true, "test1000","test");
    	//取消推荐
    	recommendService.update(Recommend.ObjectType.BUYERS.toString(), Recommend.RecommendType.COMPANY.toString(), false, "test1000","test");
    }
    /**
     * 查询推荐信息
     */
    @Test
    public void testselect(){
    	List<Recommend>list=recommendService.list(Recommend.ObjectType.BUYERS.toString(), Recommend.RecommendType.COMPANY.toString(),"test1000", "10000", "2015-12-21 18:53:44", null,"test");
    	System.out.println(list);
    	
    }
  	
}
