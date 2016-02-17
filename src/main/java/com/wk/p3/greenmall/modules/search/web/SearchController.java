package com.wk.p3.greenmall.modules.search.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.wk.p3.greenmall.common.utils.DateUtils;
import com.wk.p3.greenmall.common.utils.Page;
import com.wk.p3.greenmall.modules.search.entry.SearchType;
import com.wk.p3.greenmall.modules.search.service.SearchService;

@Controller
@RequestMapping("/s")
public class SearchController {
	private static Logger logger = LoggerFactory.getLogger(SearchController.class);
	@Autowired
	SearchService searchService;
	
	@RequestMapping("/{type}/{words}")
	public ModelAndView search(@PathVariable("type")Integer type,@PathVariable("words")String key,HttpServletRequest request){
		try {
			Map<String,String>filtermap=new HashMap<String, String>();
			//默认分页数量10
			int pagesize=10;
			//当前页
			int currentpage=StringUtils.isNotBlank(request.getParameter("currentpagenum"))?Integer.valueOf(request.getParameter("currentpagenum")):1;
			String brandname=request.getParameter("brandname");
			String area=request.getParameter("area");
			String spuname=request.getParameter("spuname");
			String transtionalplace=request.getParameter("transtionalplace");
			String supplier=request.getParameter("supplier");
			String breed=request.getParameter("breed");
			String minprice=request.getParameter("minprice");
			String maxprice=request.getParameter("maxprice");
			String url=request.getRequestURI()+"?1=1";
			ModelAndView mv=null;
			if(type.toString().equals(SearchType.BUYERS.toString())){ mv=new ModelAndView("modules/search/buylist");key=key+" AND minprice:["+(StringUtils.isNotBlank(minprice) ? minprice:"*")+" TO *] AND maxprice:[* TO "+(StringUtils.isNotBlank(maxprice) ? maxprice:"*")+"]";}
			if(type.toString().equals(SearchType.SUPPLY.toString())){ mv=new ModelAndView("modules/search/supplylist");key=key+" AND price:["+(StringUtils.isNotBlank(minprice) ? minprice:"*")+" TO "+(StringUtils.isNotBlank(maxprice) ? maxprice:"*")+"]";}
			if(StringUtils.isNotBlank(spuname)){url=url+"&spuname="+spuname; key=key+" AND spuname:*"+spuname+"*";mv.addObject("spuname", spuname);filtermap.put("spuname", spuname);}
			if(StringUtils.isNotBlank(brandname)){url=url+"&brandname="+brandname; key=key+" AND brandname:*"+brandname+"*";mv.addObject("brandname", brandname);filtermap.put("brandname", brandname);}
			if(StringUtils.isNotBlank(area)){url=url+"&area="+area; key=key+" AND area:*"+area+"*";mv.addObject("area", area);filtermap.put("area", area);}
			if(StringUtils.isNotBlank(transtionalplace)){url=url+"&transtionalplace="+transtionalplace; key=key+" AND transtionalplace:\"*"+transtionalplace+"*\"";mv.addObject("transtionalplace", transtionalplace);filtermap.put("transtionalplace", transtionalplace);}
			if(StringUtils.isNotBlank(supplier)){url=url+"&supplier="+supplier; key=key+" AND supplier:\"*"+supplier+"*\"";mv.addObject("supplier", supplier);filtermap.put("supplier", supplier);}
			if(StringUtils.isNotBlank(breed)){url=url+"&breed="+breed; key=key+" AND breed:*"+breed+"*";mv.addObject("breed", breed);filtermap.put("breed", breed);}
			if(StringUtils.isNotBlank(minprice)){url=url+"&minprice="+minprice; mv.addObject("minprice", minprice);}
			if(StringUtils.isNotBlank(maxprice)){url=url+"&maxprice="+maxprice; mv.addObject("maxprice", maxprice);}
			JSONObject json=(JSONObject) searchService.search(key, String.valueOf(type),currentpage,pagesize,request.getParameter("orderby"));
			logger.info("SearchResult:"+json);
			mv.addObject("data", json);
			mv.addObject("page",new Page(json.getIntValue("count"), 10, currentpage));
			System.out.println(JSONObject.toJSON(new Page(json.getIntValue("count"), 10, currentpage)));
			mv.addObject("url",url);
			mv.addObject("isbrandname", StringUtils.isNotBlank(brandname)?true:false);
			mv.addObject("isarea", StringUtils.isNotBlank(area)?true:false);
			mv.addObject("filtermap", filtermap);
			mv.addObject("orderby", StringUtils.isNotBlank(request.getParameter("orderby")));
			
			logger.info(DateUtils.getDateTime()+"SUCCESS SEARCH [key:"+key+"] AND [type:"+type+"]  RESULT:"+json);
			return mv;
		} catch (Exception e) {
			
			e.printStackTrace();
			logger.error(DateUtils.getDateTime()+"ERROR  SEARCH [key:"+key+"] AND [type:"+type+"]"+e.getMessage());
			return null;
		}
	}

	@RequestMapping("sync")
	@ResponseBody
	public JSONObject sync(){
		JSONObject json=new JSONObject();
		try {
			searchService.sync();
			json.put("isSuccess", true);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			json.put("isSuccess",false);
			return json;
		}
		
		 
	}
	
}
