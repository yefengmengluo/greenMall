package com.wk.p3.greenmall.modules.search.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.wk.p3.greenmall.modules.user.UserService;
import com.wk.p3.greenmall.modules.user.entity.FrontUser;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.CommonParams;
import org.apache.solr.common.params.SpellingParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wk.p3.greenmall.common.utils.DateUtils;
import com.wk.p3.greenmall.common.utils.PinyinUtil;
import com.wk.p3.greenmall.common.utils.SpringContextHolder;
import com.wk.p3.greenmall.modules.info.dao.InfoDao;
import com.wk.p3.greenmall.modules.info.entity.Info;
import com.wk.p3.greenmall.modules.info.service.HomeService;
import com.wk.p3.greenmall.modules.search.ISearchService;
import com.wk.p3.greenmall.modules.search.client.SearchClient;
import com.wk.p3.greenmall.modules.search.entry.DemandInfo;
import com.wk.p3.greenmall.modules.search.entry.ProvideInfo;
import com.wk.p3.greenmall.modules.search.entry.SearchType;
import com.wk.p3.greenmall.modules.sys.entity.User;

/**
 * 搜索业务
 * 
 * @author Robin
 *
 */
@Service
public class SearchService implements ISearchService{
	@Autowired
	InfoDao infoDao;
	private static Logger logger = LoggerFactory.getLogger(SearchService.class);
	private static HomeService homeService = SpringContextHolder.getBean(HomeService.class);
	private static UserService userService = SpringContextHolder.getBean(UserService.class);

	/**
	 * 
	 * @param key
	 *            搜索词
	 * @param type
	 *            搜索类型 SearchType
	 * @return
	 */
	public Object search(String key, String type,int start,int rows,String orderby) {
		try {
			logger.info(DateUtils.getDateTime() + "Search key:[" + key + "] and type:[" + type + "]");
			Map<String, String> params = new HashMap<String, String>();
			params.put("sort", orderby);
			params.put("q", "keys:" + key+" AND stype:"+type);
			params.put("start", String.valueOf((start-1)*rows));
			params.put("rows", String.valueOf(rows));
		
			if (SearchType.SUPPLY.toString().equals(type))params.put("class", ProvideInfo.class.getName());
			if (SearchType.BUYERS.toString().equals(type))params.put("class", DemandInfo.class.getName());
			return SearchClient.search(params);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("ERROR:Search key[" + key + "] and type [" + type + "]" + e.getMessage());
			return null;
		}
	}

	/**
	 * 
	 * @param id 供求信息的id
	 * @param type
	 */
	public void del(String id,String type){
		try {
			SolrQuery solrparams=new SolrQuery();
			if(type.equals(SearchType.SUPPLY.toString()))solrparams.set("infoid", id);
			if(type.equals(SearchType.BUYERS.toString()))solrparams.set("demandid", id);
			SearchClient.delDocument(solrparams);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @param list  供求信息的集合对象
	 * @param clazz Object.class
	 */
	@SuppressWarnings("rawtypes")
	public void add(List<Object>list,Class clazz){
		new syncThread(list, clazz).start();
	}

	/**
	 * 同步数据
	 */
	public void sync(){
		List<String>listinfo=infoDao.listInfoIds();
		List<Object>listProvide=new ArrayList<Object>();
		List<Object>listDemandInfo=new ArrayList<Object>();
		for (String id : listinfo) {
			Info info=infoDao.get(id);
			//supply:供应，demand:求购
			if("supply".equals(info.getType())){
				ProvideInfo provideInfo=new ProvideInfo();
				provideInfo.setInfoid(info.getId());
				provideInfo.setId(UUID.randomUUID().toString().replaceAll("-", "")+info.getId());
				provideInfo.setArea(info.getAreaName());
				provideInfo.setCategory(info.getPgoodsName());
				provideInfo.setInfoid(info.getId());
				provideInfo.setPrice(info.getFromPerPrice().floatValue());
				provideInfo.setProducePlace(info.getProductionArea());
				provideInfo.setSpec(info.getSpecs());
				provideInfo.setSpuName(info.getPgoodsName());
				provideInfo.setSupplier(info.getOrganizationName());
				provideInfo.setUpdateTime(new Date());
				provideInfo.setTranstionalPlace(info.getDetailArea());
				//品种
				provideInfo.setBreed(info.getGoodsName());
				listProvide.add(provideInfo);
			}
			
			if("demand".equals(info.getType())){
				DemandInfo demandInfo=new DemandInfo();
				demandInfo.setId(UUID.randomUUID().toString().replaceAll("-", "")+info.getId());
				demandInfo.setDemandId(info.getId());
				demandInfo.setArea(info.getDetailArea());
				demandInfo.setLocalPlace(info.getProductionArea());
				demandInfo.setMinPrice(info.getFromPerPrice().floatValue());
				demandInfo.setMaxPrice(info.getToPerPrice().floatValue());
				demandInfo.setSpec(info.getSpecs());
				//数量是double 类型
				demandInfo.setSpuName(info.getGoodsName());
				demandInfo.setStype(SearchType.BUYERS.toString());
				demandInfo.setTitle("采购"+info.getPgoodsName()+" "+info.getGoodsName()+" "+info.getNumber()+info.getNumberUnitValue());
                User user = userService.findBussinessUserByFrontUser(userService.getFrontUser().getId());
                demandInfo.setTransactionerName(user.getName());
				demandInfo.setTransactionerTel(user.getMobile());
				listDemandInfo.add(demandInfo);
			}
		}
		add(listProvide, ProvideInfo.class);
		add(listDemandInfo, DemandInfo.class);
	}
	
	/**
	 * 关键词搜索
	 */
	public Object suggest(String key){
		//关键词推荐
		SolrQuery query=new SolrQuery();
		query.set("q", "suggest:*"+key+"*");
		query.setSort("kwfreq", ORDER.desc);
		query.setStart(0);
		query.setRows(10);
		JSONArray array=(JSONArray) SearchClient.suggest(query);
		if(array.size()==0){
			//关键词容错
			return faultTolerant(key,false);
		}
		return array;
	}
	
	/**
	 * 关键词容错
	 */
	private Object faultTolerant(String key,Boolean is){
		SolrQuery query=new SolrQuery();
		query.set(CommonParams.QT, "/spell");
		query.set("spellcheck", true);
		if(is==false){
			query.set(SpellingParams.SPELLCHECK_RELOAD, true);
		}else{
			query.set(SpellingParams.SPELLCHECK_BUILD, true);
		}
		
		query.set(SpellingParams.SPELLCHECK_DICT, "file");
		query.set(SpellingParams.SPELLCHECK_COUNT, 1);
		query.set(SpellingParams.SPELLCHECK_Q, PinyinUtil.converterToSpell(key));
		JSONObject json=(JSONObject) SearchClient.faultTolerant(query);
		if(null==json&&false==is){
			faultTolerant(key, true);
		}
		return SearchClient.faultTolerant(query) ;
	}
	
	
	
	
	public class syncThread extends Thread{
		List<Object>list;
		@SuppressWarnings("rawtypes")
		Class clazz;
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public syncThread(List list,Class clazz){
			this.list=list;
			this.clazz=clazz;
		}
		public void run(){
			List<SolrInputDocument>listd=new ArrayList<SolrInputDocument>();
			if(clazz.equals(ProvideInfo.class)){
				for (Object object : list) {
					ProvideInfo info=(ProvideInfo) object;
					SolrInputDocument document=new SolrInputDocument();
					document.addField("id", UUID.randomUUID().toString().replaceAll("-", "")+info.getId());
					//id
					document.addField("infoid", info.getId());
					//地域
					document.addField("area", info.getArea());
					//品牌名称
					document.addField("brandname", info.getBrandName());
					//类目信息
					document.addField("category", info.getCategory());
					//最小价格
				    document.addField("price", info.getPrice());
				    //产地
				    document.addField("produceplace",info.getProducePlace());
				    //规格
				    document.addField("spec", info.getSpec());
					//name
					document.addField("spuname", info.getSpuName());
					//提供商
					document.addField("supplier", info.getSupplier());
					//交易地点
					document.addField("transtionalplace", info.getTranstionalPlace());
					//最后更新时间
					document.addField("updatetime", new Date());
					//类型
					document.addField("stype", SearchType.SUPPLY.toString());
					//品种
					document.addField("breed", info.getBreed());
					listd.add(document);
				}
			}
			if(clazz.equals(DemandInfo.class)){
				for (Object object : list) {
					DemandInfo info=(DemandInfo) object;
					SolrInputDocument document=new SolrInputDocument();
					document.setField("id", UUID.randomUUID().toString().replaceAll("-", "")+info.getId());
					//收购信息id
					document.setField("demandid",info.getId());
					//titile
					document.setField("title",info.getTitle());
					//商品名称
					document.setField("spuname",info.getSpuName());
					//数量
					document.setField("neednumber",info.getNeedNumber());
					//最小价格
					document.setField("minprice", info.getMinPrice());
					//最大价格
					document.setField("maxprice", info.getMaxPrice());
					//地方
					document.setField("localplace", info.getLocalPlace());
					//规格
					document.setField("spec",info.getSpec());
					//交易员姓名
					document.setField("transactionername",info.getTransactionerName());
					//交易员电话
					document.setField("transactionertel", info.getTransactionerTel());
					//地区
					document.setField("area", info.getArea());
					//品牌名称
					document.setField("brandname", info.getBrandName());
					document.setField("stype",SearchType.BUYERS);
					listd.add(document);
					
				}
			}
			SearchClient.addDocument(listd);
		}
		
		
	}
	
	
}
