package com.wk.p3.greenmall.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wk.p3.greenmall.modules.info.entity.Info;
import com.wk.p3.greenmall.modules.info.service.imp.InfoServiceImp;
import com.wk.p3.greenmall.modules.search.client.SearchClient;
import com.wk.p3.greenmall.modules.search.entry.ProvideInfo;
import com.wk.p3.greenmall.modules.search.entry.SearchType;
import com.wk.p3.greenmall.modules.search.service.SearchService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context.xml")
public class SearchTest {
	@Autowired
	InfoServiceImp infoServiceImp;
	@Autowired
	IInfo iInfo;
	@Autowired
	SearchService searchService;
	static Map<Integer,String>localmap=new HashMap<Integer, String>();
	static Map<Integer,String>areamap=new HashMap<Integer, String>();
	static Map<Integer,String>brandmap=new HashMap<Integer, String>();
	static Map<Integer,String>categorymap=new HashMap<Integer, String>();
	static Map<Integer,String>specmap=new HashMap<Integer, String>();
	static Map<Integer,String>transtionalermap=new HashMap<Integer, String>();
	static Map<Integer,String>breedmap=new HashMap<Integer, String>();
	static{
		//地区
		localmap.put(0, "济南");
		localmap.put(1, "滨州");
		localmap.put(2, "东营");
		localmap.put(3, "德州");
		localmap.put(4, "菏泽");
		localmap.put(5, "济宁");
		localmap.put(6, "莱芜");
		localmap.put(7, "临沂");
		localmap.put(8, "聊城");	
		localmap.put(9, "青岛");
		localmap.put(10, "日照");
		localmap.put(11, "泰安");
		localmap.put(12, "潍坊");
		localmap.put(13, "威海");
		localmap.put(14, "烟台");
		localmap.put(15, "淄博");
		localmap.put(16, "枣庄");

		//区县
		areamap.put(0, "东城区");
		areamap.put(1, "西城区 ");
		areamap.put(2, "海淀区");
		areamap.put(3, "朝阳区");
		areamap.put(4, "丰台区");
		areamap.put(5, "石景山区");
		areamap.put(6, "通州区");
		areamap.put(7, "顺义区");
		areamap.put(8, "房山区");
		areamap.put(9, "大兴区");
		areamap.put(10, "怀柔区");
		areamap.put(11, "平谷区");
		areamap.put(12, "门头沟区");
		areamap.put(13, "密云县");
		areamap.put(14, "东城区");
		areamap.put(15, "延庆县");
		
		
		//品牌
		brandmap.put(0, "北京新三号");
		brandmap.put(1, "91-12白菜");
		brandmap.put(2, "秋宝白菜  ");
		brandmap.put(3, "韩国黄心白菜");
		brandmap.put(4, "乾坤91-18白菜");
		brandmap.put(5, "抗70白菜");
		brandmap.put(6, "夏阳青杂三号 ");
		brandmap.put(7, "丰抗78白菜");
		brandmap.put(8, "凌峰金辉");
		brandmap.put(9, "津青75白菜");
		brandmap.put(10, "秋绿60白菜");
		
		//类目
		categorymap.put(0, "白菜");
		categorymap.put(1, "菠菜");
		categorymap.put(2, "香菜");
		categorymap.put(3, "芥菜");
		categorymap.put(4, "莴苣");
		categorymap.put(5, "芥蓝");
		categorymap.put(6, "油菜");
		categorymap.put(7, "芹菜");
		categorymap.put(8, "甘蓝");
		categorymap.put(9, "韭菜");
		categorymap.put(10, "西兰花");
		//规格
		specmap.put(0, "袋装");
		specmap.put(1, "瓶装");
		specmap.put(2, "绳装");
		specmap.put(3, "其他");
		//交易员
		transtionalermap.put(1, "张先生");
		transtionalermap.put(2, "王先生");
		transtionalermap.put(3, "李先生");
		transtionalermap.put(4, "赵先生");
		transtionalermap.put(5, "钱先生");
		transtionalermap.put(6, "孙先生");
		transtionalermap.put(7, "周先生");
		transtionalermap.put(8, "吴先生");
		transtionalermap.put(9, "郑先生");
		transtionalermap.put(10, "冯先生");
		transtionalermap.put(11, "陈先生");
		transtionalermap.put(12, "楮先生");
		transtionalermap.put(13, "卫先生");
		transtionalermap.put(14, "蒋先生");
		transtionalermap.put(15, "沈先生");
		transtionalermap.put(16, "杨先生");
		//品种
		breedmap.put(1, "结球白菜");
		breedmap.put(2, "不结球白菜");
		breedmap.put(3, "紫背天葵");
		breedmap.put(4, "抱子甘蓝");
		breedmap.put(5, "羽衣甘蓝");
		breedmap.put(6, "生菜");
		breedmap.put(7, "娃娃菜");
	}
	/**
	 * 测试添加数据信息到solr
	 * @test 暂时注释掉,用的时候打开
	 */
     @Test
	public void testAddToSolr(){
		 //提供者信息入库 
		  SearchClient.addDocument(listProvider());
		 //求购者信息
		  SearchClient.addDocument(listbuyer());
		 System.out.println("执行成功==========");
	}
	/**
	 * 测试搜索结果
	 */
	@Test
	public void testSearch(){
		//供货方
		System.out.println(searchService.search("富士", SearchType.SUPPLY.toString(),2,10,"price+asc"));
		//求购方
		System.out.println(searchService.search("富士", SearchType.BUYERS.toString(),2,10,"price+asc"));
	}
	
	/**
	 * 供货方
	 */
	private List<SolrInputDocument>listProvider(){
		List<String>listid=iInfo.listInfoIds();
		List<SolrInputDocument>list=new ArrayList<SolrInputDocument>();
		for (String id : listid) {
			Info info=infoServiceImp.get(id);
			ProvideInfo provideInfo=new ProvideInfo();
			SolrInputDocument document=new SolrInputDocument();
			document.addField("id", UUID.randomUUID().toString().replaceAll("-", "")+info.getId());
			//id
			provideInfo.setInfoid(info.getId());
			document.addField("infoid", info.getId());
			//地域
			provideInfo.setArea(localmap.get(new Random().nextInt(16)));
			document.addField("area", localmap.get(new Random().nextInt(16)));
			//品牌名称
			provideInfo.setBrandName(brandmap.get(new Random().nextInt(10)));
			document.addField("brandname", brandmap.get(new Random().nextInt(10)));
			//类目信息
			provideInfo.setCategory(categorymap.get(new Random().nextInt(10)));
			document.addField("category", categorymap.get(new Random().nextInt(10)));
			//最小价格
		    provideInfo.setPrice(info.getFromPerPrice().floatValue());
		    document.addField("price", info.getFromPerPrice());
		    //产地
		    provideInfo.setProducePlace(localmap.get(new Random().nextInt(16))+"基地");
		    document.addField("produceplace", localmap.get(new Random().nextInt(16))+"基地");
		    //规格
		    provideInfo.setSpec(specmap.get(new Random().nextInt(3)));
		    document.addField("spec", specmap.get(new Random().nextInt(3)));
			//name
			provideInfo.setSpuName(info.getGoodsName());
			document.addField("spuname", info.getGoodsName());
			//提供商
			provideInfo.setSupplier(localmap.get(new Random().nextInt(16))+"农副产品有限公司");
			document.addField("supplier", localmap.get(new Random().nextInt(16))+"农副产品有限公司");
			//交易地点
			provideInfo.setTranstionalPlace("交易地点"+localmap.get(new Random().nextInt(16)));
			document.addField("transtionalplace", "交易地点"+localmap.get(new Random().nextInt(16)));
			//最后更新时间
			provideInfo.setUpdateTime(new Date());
			document.addField("updatetime", new Date());
			//类型
			document.addField("stype", SearchType.SUPPLY.toString());
			//品种
			document.addField("breed", breedmap.get(new Random().nextInt(6)));
			
			list.add(document);
		}
		return list;
	}
	
	/**
	 * 求购方
	 */
	private List<SolrInputDocument>listbuyer(){
		List<String>listid=iInfo.listInfoIds();
		List<SolrInputDocument>list=new ArrayList<SolrInputDocument>();
		for (String id : listid) {
			Info info=infoServiceImp.get(id);
			SolrInputDocument document=new SolrInputDocument();
			document.setField("id", UUID.randomUUID().toString().replaceAll("-", "")+info.getId());
			document.setField("demandid",info.getId());
			document.setField("title","大量收购"+info.getGoodsName());
			document.setField("spuname", info.getGoodsName());
			document.setField("neednumber",new Random().nextInt(10));
			document.setField("minprice", info.getFromPerPrice());
			document.setField("maxprice", info.getToPerPrice());
			String area=areamap.get(new Random().nextInt(15));
			document.setField("localplace", localmap.get(new Random().nextInt(16))+""+area);
			document.setField("spec",info.getSpecs());
			document.setField("transactionername", transtionalermap.get(new Random().nextInt(16)));
			document.setField("transactionertel", "138000000"+new Random().nextInt(99));
			document.setField("area", area);
			document.setField("brandname", brandmap.get(new Random().nextInt(10)));
			document.setField("stype",SearchType.BUYERS);
			list.add(document);
		}
		return list;
	}

	
	
	/**
	 * 关键词推荐测试
	 */
	@Test
	public void testsuggest(){
		System.out.println(searchService.suggest("wd"));
		System.out.println(searchService.suggest("wandou"));
		System.out.println(searchService.suggest("豌豆"));
	}
	
	/**
	 * 测试纠错信息
	 */
	@Test
	public void testfaultTolerant(){
		System.out.println(searchService.suggest("万豆"));
	}
	
	/**
	 * 测试 品种+交易地点+供货商
	 * @throws IOException 
	 */
	@Test
	public void test1() throws IOException{
		String curLine="";
		String content="";
		URL url=new URL("http://localhost/greenMall/s/1/*?breed=抱子&transtionalplace=交易地点菏泽&supplier=莱芜农副产品有限公司");
		URLConnection connect = url.openConnection();
		InputStream stream = connect.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "utf-8"));
		while ((curLine = reader.readLine()) != null) {
			content = content + curLine + "\r\n";
		}
		content = content.trim();
		System.out.println(content);
	
	}



}
