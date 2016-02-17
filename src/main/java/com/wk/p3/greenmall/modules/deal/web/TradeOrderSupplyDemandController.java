/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.modules.deal.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wk.p3.greenmall.common.config.Global;
import com.wk.p3.greenmall.common.persistence.Page;
import com.wk.p3.greenmall.common.web.BaseController;
import com.wk.p3.greenmall.common.utils.DateUtils;
import com.wk.p3.greenmall.common.utils.StringUtils;
import com.wk.p3.greenmall.modules.deal.entity.TradeOrderSupplyDemand;
import com.wk.p3.greenmall.modules.deal.service.impl.TradeOrderSupplyDemandService;

/**
 * 业务订单Controller
 * @author lf
 * @version 2015-12-15
 */
@Controller
@RequestMapping(value = "${adminPath}/deal/tradeOrderSupplyDemand")
public class TradeOrderSupplyDemandController extends BaseController {

	@Autowired
	private TradeOrderSupplyDemandService tradeOrderSupplyDemandService;
	
	@ModelAttribute
	public TradeOrderSupplyDemand get(@RequestParam(required=false) String id) {
		TradeOrderSupplyDemand entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tradeOrderSupplyDemandService.get(id);
		}
		if (entity == null){
			entity = new TradeOrderSupplyDemand();
		}
		return entity;
	}
	
	@RequiresPermissions("deal:tradeOrderSupplyDemand:view")
	@RequestMapping(value = {"list", ""})
	public String list(TradeOrderSupplyDemand tradeOrderSupplyDemand, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TradeOrderSupplyDemand> page = tradeOrderSupplyDemandService.findPage(new Page<TradeOrderSupplyDemand>(request, response), tradeOrderSupplyDemand); 
		model.addAttribute("page", page);
		return "modules/deal/tradeOrderSupplyDemandList";
	}

	@RequiresPermissions("deal:tradeOrderSupplyDemand:view")
	@RequestMapping(value = "form")
	public String form(TradeOrderSupplyDemand tradeOrderSupplyDemand, Model model) {
		model.addAttribute("tradeOrderSupplyDemand", tradeOrderSupplyDemand);
		return "modules/deal/tradeOrderSupplyDemandForm";
	}

	@RequiresPermissions("deal:tradeOrderSupplyDemand:edit")
	@RequestMapping(value = "save")
	public String save(TradeOrderSupplyDemand tradeOrderSupplyDemand, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tradeOrderSupplyDemand)){
			return form(tradeOrderSupplyDemand, model);
		}
		tradeOrderSupplyDemandService.save(tradeOrderSupplyDemand);
		addMessage(redirectAttributes, "保存生成&quot;供求信息&quot;成功成功");
		return "redirect:"+Global.getAdminPath()+"/deal/tradeOrderSupplyDemand/?repage";
	}
	
	@RequiresPermissions("deal:tradeOrderSupplyDemand:edit")
	@RequestMapping(value = "delete")
	public String delete(TradeOrderSupplyDemand tradeOrderSupplyDemand, RedirectAttributes redirectAttributes) {
		tradeOrderSupplyDemandService.delete(tradeOrderSupplyDemand);
		addMessage(redirectAttributes, "删除生成&quot;供求信息&quot;成功成功");
		return "redirect:"+Global.getAdminPath()+"/deal/tradeOrderSupplyDemand/?repage";
	}
	
}