/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wk.p3.greenmall.modules.deal.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wk.p3.greenmall.common.config.Global;
import com.wk.p3.greenmall.common.persistence.Page;
import com.wk.p3.greenmall.common.web.BaseController;
import com.wk.p3.greenmall.common.utils.StringUtils;
import com.wk.p3.greenmall.modules.deal.entity.TradeOrderFlow;
import com.wk.p3.greenmall.modules.deal.service.impl.TradeOrderFlowService;

/**
 * 业务订单Controller
 * @author lf
 * @version 2015-12-15
 */
@Controller
@RequestMapping(value = "${adminPath}/deal/tradeOrderFlow")
public class TradeOrderFlowController extends BaseController {

	@Autowired
	private TradeOrderFlowService tradeOrderFlowService;
	
	@ModelAttribute
	public TradeOrderFlow get(@RequestParam(required=false) String id) {
		TradeOrderFlow entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tradeOrderFlowService.get(id);
		}
		if (entity == null){
			entity = new TradeOrderFlow();
		}
		return entity;
	}
	
	//@RequiresPermissions("deal:tradeOrderFlow:view")
	@RequestMapping(value = {"list", ""})
	public String list(TradeOrderFlow tradeOrderFlow, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TradeOrderFlow> page = tradeOrderFlowService.findPage(new Page<TradeOrderFlow>(request, response), tradeOrderFlow); 
		model.addAttribute("page", page);
		return "modules/deal/tradeOrderFlowList";
	}

	//@RequiresPermissions("deal:tradeOrderFlow:view")
	@RequestMapping(value = "form")
	public String form(TradeOrderFlow tradeOrderFlow, Model model) {
		model.addAttribute("tradeOrderFlow", tradeOrderFlow);
		return "modules/deal/tradeOrderFlowForm";
	}

	//@RequiresPermissions("deal:tradeOrderFlow:edit")
	@RequestMapping(value = "save")
	public String save(TradeOrderFlow tradeOrderFlow, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tradeOrderFlow)){
			return form(tradeOrderFlow, model);
		}
		tradeOrderFlowService.save(tradeOrderFlow);
		addMessage(redirectAttributes, "保存生成&quot;业务订单流水&quot;成功成功");
		return "redirect:"+Global.getAdminPath()+"/deal/tradeOrderFlow/?repage";
	}
	
	//@RequiresPermissions("deal:tradeOrderFlow:edit")
	@RequestMapping(value = "delete")
	public String delete(TradeOrderFlow tradeOrderFlow, RedirectAttributes redirectAttributes) {
		tradeOrderFlowService.delete(tradeOrderFlow);
		addMessage(redirectAttributes, "删除生成&quot;业务订单流水&quot;成功成功");
		return "redirect:"+Global.getAdminPath()+"/deal/tradeOrderFlow/?repage";
	}

}