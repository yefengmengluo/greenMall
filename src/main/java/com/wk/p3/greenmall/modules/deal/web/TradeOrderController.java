package com.wk.p3.greenmall.modules.deal.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.zxing.qrcode.decoder.Mode;
import com.wk.p3.greenmall.common.config.Global;
import com.wk.p3.greenmall.common.persistence.Page;
import com.wk.p3.greenmall.common.web.BaseController;
import com.wk.p3.greenmall.common.utils.DateUtils;
import com.wk.p3.greenmall.common.utils.StringUtils;
import com.wk.p3.greenmall.modules.deal.entity.Details;
import com.wk.p3.greenmall.modules.deal.entity.InfoVO;
import com.wk.p3.greenmall.modules.deal.entity.Invok;
import com.wk.p3.greenmall.modules.deal.entity.OppVO;
import com.wk.p3.greenmall.modules.deal.entity.SupplyDemandInfo;
import com.wk.p3.greenmall.modules.deal.entity.TradeOrder;
import com.wk.p3.greenmall.modules.deal.entity.TradeOrderSupplyDemand;
import com.wk.p3.greenmall.modules.deal.service.impl.SupplyDemandInfoService;
import com.wk.p3.greenmall.modules.deal.service.impl.TradeOrderService;
import com.wk.p3.greenmall.modules.deal.service.impl.TradeOrderSupplyDemandService;
import com.wk.p3.greenmall.modules.user.entity.UserAddressInfo;

/**
 * 业务订单Controller
 * @author lf
 * @version 2015-12-15
 */ 
@Controller
@RequestMapping(value = "${adminPath}/deal/tradeOrder")
public class TradeOrderController extends BaseController {
	private Log log = LogFactory.getLog(TradeOrderController.class);
	@Autowired
	private TradeOrderService tradeOrderService;
	@Autowired
	private SupplyDemandInfoService supplyDemandInfoService;
	@Autowired
	private TradeOrderSupplyDemandService tradeOrderSupplyDemandService;
		
	@ModelAttribute
	public TradeOrder get(@RequestParam(required=false) String id,@RequestParam(required=false) String orderid) {
		TradeOrder entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tradeOrderService.get(id);
		}
		if (StringUtils.isNotBlank(orderid)){
			entity = tradeOrderService.getOrderByOrderId(orderid);
		}
		if (entity == null){
			entity = new TradeOrder();
		}
		return entity;
	}
	
	
	 /**
	  * 获取待跟踪订单的列表
	  * @author lf
	  * @param tradeOrder
	  * @param request
	  * @param response
	  * @param model
	  * @return
	  * @exception/throws [此处异常不做处理]
	  * @see   [类#方法]
	  * @since [果果正式版V1.0]
	  * create Date <创建日期，格式:February 14, 2016>
	  */
	//@RequiresPermissions("deal:tradeOrder:view")
		@RequestMapping(value = {"ordering"})
		public String ordering(TradeOrder tradeOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
			Page<TradeOrder> page = tradeOrderService.getOrderIng(new Page<TradeOrder>(request, response), tradeOrder); 		
			for(TradeOrder to:page.getList()){
				if (1 == to.getStatue()) {
		           to.setRemarks("撮合状态");
				} else if (2 == to.getStatue()) {
					to.setRemarks("订单确认");				
				} else if (3 == to.getStatue()) {
					to.setRemarks("等待付款");				
				} else if (4 == to.getStatue()) {
					to.setRemarks("等待发货");				
				} else if (5 == to.getStatue()) {
					to.setRemarks("交易完成");			
				} else if(0==to.getStatue()){
					to.setRemarks("交易取消");
				}
				else {
					to.setRemarks("未知状态");
				}
			}
			model.addAttribute("page", page);
			
			return "modules/deal/tradeOrderList";
		}
	
	/**
	 * 订单列表
	 * @param tradeOrder
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	//@RequiresPermissions("deal:tradeOrder:view")
	@RequestMapping(value = {"list", ""})
	public String list(TradeOrder tradeOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TradeOrder> page = tradeOrderService.findPage(new Page<TradeOrder>(request, response), tradeOrder); 		
		for(TradeOrder to:page.getList()){
			if (1 == to.getStatue()) {
	           to.setRemarks("撮合状态");
			} else if (2 == to.getStatue()) {
				to.setRemarks("订单确认");				
			} else if (3 == to.getStatue()) {
				to.setRemarks("等待付款");				
			} else if (4 == to.getStatue()) {
				to.setRemarks("等待发货");				
			} else if (5 == to.getStatue()) {
				to.setRemarks("交易完成");			
			} else if(0==to.getStatue()){
				to.setRemarks("交易取消");
			}
			else {
				to.setRemarks("未知状态");
			}
		}
		model.addAttribute("page", page);
		model.addAttribute("counts",tradeOrderService.getOrderIngCount(tradeOrder));
		return "modules/deal/tradeOrderList";
	}
  
	/**
	 * 保存订单流水
	 * 根据交易状态保存相应的数据
	 * @param id
	 * @param url
	 * @param tradeOrder
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @throws Exception 
	 */
	//@RequiresPermissions("deal:tradeOrder:edit")
	@RequestMapping(value = "orderfollow")
	public String orderfollowing(@RequestParam(required=false) String orderId,TradeOrder tradeOrder,@RequestParam(required=false) String url, Model model, RedirectAttributes redirectAttributes) throws Exception {
		if (StringUtils.isNotEmpty(url)) {
			if (Invok.orderstatus.containsKey(url)) {
				tradeOrder.setStatue(Integer.valueOf(url.substring(1)));
				tradeOrderService.save(tradeOrder);
				if ("s0".equals(url)) {//s0表示取消订单
					return "redirect:" + Global.getAdminPath() + "/deal/tradeOrder/list";
				}else if("s5".equals(url)){//s5表示订单交易完成
					return "redirect:" + Global.getAdminPath() + "/deal/tradeOrder/list";
				}				
				model.addAttribute("node", Invok.orderstatus.get(url).get(url));
				return this.tradeorderdetails(orderId,tradeOrder, model);//根据订单id转到订单详情页面
			}
		}
		return null;
	}
	
	//订单的删除
	//@RequiresPermissions("deal:tradeOrder:edit")
	@RequestMapping(value = "delete")
	public String delete(TradeOrder tradeOrder, RedirectAttributes redirectAttributes) {
		tradeOrder.setStatue(-1);
		tradeOrderService.save(tradeOrder);
		addMessage(redirectAttributes, "删除生成&quot;业务订单&quot;成功成功");
		return "redirect:"+Global.getAdminPath()+"/deal/tradeOrder/?repage";
	}
	
	/**
	 * 展示商机信息
	 * businessopportunity 商机
	 * @param supplyDemandInfo
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	//@RequiresPermissions("deal:tradeOrder:view")
	@RequestMapping(value = {"opp"})
	public String businessopportunity(OppVO oppVO,@RequestParam(required=false) String opp, HttpServletRequest request, HttpServletResponse response, Model model) {
		SupplyDemandInfo supplyDemandInfo = new SupplyDemandInfo();
		supplyDemandInfo.setGoodsName(oppVO.getGoodsName());
		if(StringUtils.isNotBlank(oppVO.getNumber()))supplyDemandInfo.setNumber(Double.valueOf(oppVO.getNumber()));
		if(StringUtils.isNoneBlank(oppVO.getStatue()))supplyDemandInfo.setStatue(oppVO.getStatue());
		supplyDemandInfo.setPriceUnitValue(oppVO.getPriceUnitValue());
		Page<SupplyDemandInfo> page = null;
		if("supply".equals(opp)){
			page = tradeOrderService.querySupplyInfo(new Page<SupplyDemandInfo>(request, response), supplyDemandInfo);
		}else if("demand".equals(opp)){
			page = tradeOrderService.queryDemandInfo(new Page<SupplyDemandInfo>(request, response), supplyDemandInfo);
		}
		//Page<SupplyDemandInfo> page = supplyDemandInfoService.findPage(new Page<SupplyDemandInfo>(request, response), supplyDemandInfo); 
		String path = "";		
		if("supply".equals(opp)){path="modules/deal/supplyInfoList";}		
		if("demand".equals(opp)){path="modules/deal/demandInfoList";}		
		model.addAttribute("page", page);
		return path;
	}
	
	/**
	 * 订单详情
	 * @param tradeOrder
	 * @param model
	 * @return
	 */
	//@RequiresPermissions("deal:tradeOrder:view")
	@RequestMapping(value = "view")
	public String view(@RequestParam(required=false) String orderId,TradeOrder tradeOrder, Model model) {
		
		 InfoVO demandInfo = tradeOrderService.returnDemandInfo(orderId);
	     UserAddressInfo supplyAddressInfo = tradeOrderService.returnSupplyAddress(orderId);
	     UserAddressInfo demandAddressInfo = tradeOrderService.returnDemandAddress(orderId);
	     TradeOrder order = tradeOrderService.get(orderId);
	     
		     //OrderItem supplyitem = tradeOrderService.findOrderSupplyDetails(orderId);	
		    //OrderItem demanditem = tradeOrderService.findOrderDemandDetails(orderId);
		    List<SupplyDemandInfo> supplyDemandInfo = tradeOrderService.findSupplyInfo(orderId);
		    TradeOrderSupplyDemand t  = new TradeOrderSupplyDemand();
		    t.setOrderId(orderId);
		    TradeOrderSupplyDemand tradeOrderSupplyDemand = tradeOrderSupplyDemandService.findSuppleyDemandByOrderId(orderId);
		    model.addAttribute("supplyDemandInfo",supplyDemandInfo);
			//model.addAttribute("supplyInfo",supplyInfo);//卖家信息
			model.addAttribute("demandInfo",demandInfo);//买家信息
			model.addAttribute("supplyAddressInfo",supplyAddressInfo);//发货地址
			//model.addAttribute("demandAddressInfo",demandAddressInfo);//收货地址
			//model.addAttribute("order",order);		//订单数据信息	
			model.addAttribute("tradeOrderSupplyDemand",tradeOrderSupplyDemand);//订单业务供求信息表		
			model.addAttribute("tradeOrder",order);
			model.addAttribute("orderId",orderId);
			model.addAttribute("demandAddressInfo",demandAddressInfo);
		return "modules/deal/tradeOrderView";
	}
	
	
	
	/**
	 * 订单状态修改表单
	 * @param action
	 * @param tradeOrder
	 * @param model
	 * @return
	 */
	//@RequiresPermissions("deal:tradeOrder:view")
	@RequestMapping(value = "form")
	public String form(@RequestParam(required=false) String action,TradeOrder tradeOrder, Model model) {
		//model.addAttribute("tradeOrder", tradeOrder);	
		if("create".equals(action)){
			return "modules/deal/tradeOrderForm";
		}else{
			String showview = null;		
			if(2==tradeOrder.getStatue()){showview = "orderpayment";}
			else if(3==tradeOrder.getStatue()){showview = "orderdeliver";}
			else if(4==tradeOrder.getStatue()){showview = "ordercomplete";}
			else if(5==tradeOrder.getStatue()){showview = "ordersuccess";}
			else if(0==tradeOrder.getStatue()){showview = "ordercancel";}
			else{showview = "orderconfirm";}	
			model.addAttribute("node","<script type=\"text/javascript\">$(\".orderflow\").setStep("+tradeOrder.getStatue()+");$(\"#"+showview+"\").show();</script>");
		}
		return "modules/deal/tradeOrderForm";
	}
	
	//@RequiresPermissions("deal:tradeOrder:edit")
	@RequestMapping(value = "upate")
   public String update(@RequestParam(required=false) String id,@RequestParam(required=false) String url,TradeOrder tradeOrder, Model model, RedirectAttributes redirectAttributes){
		tradeOrderService.save(tradeOrder);
	   return "";
   }
	
	/**
	 * 已经审核的供求信息或者匹配成功的供求信息
	 * 创建订单
	 * @param demandid
	 * @param supplyid
	 * @param tradeOrder
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	//@RequiresPermissions("deal:tradeOrder:edit")
	@RequestMapping(value = "create")
	@ResponseBody
   public Map<String,String> create(@RequestParam(required=false) String demandid,@RequestParam(required=false) String supplyid,TradeOrder tradeOrder, Model model, RedirectAttributes redirectAttributes){
		//TradeOrder to = null;
		Map<String,String> map = new HashMap<String,String>();
		try{
			if(StringUtils.isNotEmpty(demandid)&&StringUtils.isNotEmpty(supplyid)){
	        	TradeOrderSupplyDemand supplydemand=new TradeOrderSupplyDemand();        	
	        	supplydemand.setDemandId(demandid);
	        	supplydemand.setSupplyId(supplyid);
	        	tradeOrderSupplyDemandService.save(supplydemand);
	        	//to = tradeOrderService.getOrderByOrderId(supplydemand.getOrderId());	
	        	map.put("success","加入撮合单成功.");
			}else{
				map.put("success","加入撮合单失败--[cause]-请检查供求编号是否为空.");
			}
			
		}catch(Exception e){
			map.put("fail","加入撮合单失败--[cause]-" + e.getMessage());
		}
		return map;	
   }
	
	@RequestMapping(value = "createOrder")
	@ResponseBody
	public String createOrder(@RequestParam(required=false) String demandid,@RequestParam(required=false) String supplyid,TradeOrder tradeOrder, Model model, RedirectAttributes redirectAttributes){
	//Map<String,String> map =  new HashMap<String,String>();
		String string = "";
		try{
			if(StringUtils.isNotEmpty(demandid)&&StringUtils.isNotEmpty(supplyid)){
	        	TradeOrderSupplyDemand supplydemand=new TradeOrderSupplyDemand();        	
	        	supplydemand.setDemandId(demandid);
	        	supplydemand.setSupplyId(supplyid);
	        	tradeOrderSupplyDemandService.save(supplydemand);
	        	//to = tradeOrderService.getOrderByOrderId(supplydemand.getOrderId());	  
	        	string="1";
			}
		}catch(Exception e){
			string="0";
		}				
		return string;
	}
	
	
	/**
	 * 展示供求单详情
	 * @param supplyid
	 * @param demandid
	 * @param details
	 * @param request
	 * @param response
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	//@RequiresPermissions("deal:tradeOrder:view")
	@RequestMapping(value = "details")
	public String supplydemanddetails(@RequestParam(required=false) String param,@RequestParam(required=false) String flag,@RequestParam(required=false) String supplyid,@RequestParam(required=false) String demandid,Details details,HttpServletRequest request, HttpServletResponse response, Model model,RedirectAttributes redirectAttributes){				
		model.addAttribute("okshow","show");
		if(StringUtils.isNotBlank(flag)&&flag.equals("supply")){model.addAttribute("ok","supply");model.addAttribute("supplyId",supplyid);}else if(StringUtils.isNotBlank(flag)&&flag.equals("demand")){model.addAttribute("ok","demand");model.addAttribute("demandId",demandid);}
		if(StringUtils.isNotEmpty(supplyid)){details.setSupplyid(supplyid);model.addAttribute("supplyId",supplyid);
		}else if(StringUtils.isNotEmpty(demandid)){details.setDemandid(demandid);model.addAttribute("demandId",demandid);}else{return "modules/deal/supplydemanddetails";}		
		
		Page<Details>  supplydemandpage= tradeOrderSupplyDemandService.getDetails(new Page<Details>(request, response), details); 
		Page<Details>  orderpage= tradeOrderService.getDetails(new Page<Details>(request, response), details); 
		
		model.addAttribute("orderpage", orderpage);
		model.addAttribute("supplydemandpage", supplydemandpage);
		supplydemandpage.setFuncName("page1");
		return "modules/deal/supplydemanddetails";
	}
	
	
	
	/**
	 * 返回已确认的列表
	 * @param supplyid
	 * @param demandid
	 * @param details
	 * @param request
	 * @param response
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	//@RequiresPermissions("deal:tradeOrder:view")
	@RequestMapping(value = "detailsmatched")
	public String matched(@RequestParam(required=false) String pageSize,@RequestParam(required=false) String pageNo,@RequestParam(required=false) String param,@RequestParam(required=false) String flag,@RequestParam(required=false) String supplyid,@RequestParam(required=false) String demandid,Details details,HttpServletRequest request, HttpServletResponse response, Model model,RedirectAttributes redirectAttributes){				
		model.addAttribute("okshow","show");
		if(StringUtils.isNotBlank(flag)&&flag.equals("supply")){model.addAttribute("ok","supply");}else if(StringUtils.isNotBlank(flag)&&flag.equals("demand")){model.addAttribute("ok","demand");}
		if(StringUtils.isNotEmpty(supplyid)){details.setSupplyid(supplyid);
		}else if(StringUtils.isNotEmpty(demandid)){details.setDemandid(demandid);}else{return "modules/deal/supplydemanddetails";}		
		Page<Details>  orderpage= tradeOrderService.getDetails(new Page<Details>(request, response), details); 
		if(StringUtils.isNotBlank(pageNo.toString())) orderpage.setPageNo(Integer.valueOf(pageNo));
		if(StringUtils.isNotBlank(pageSize)) orderpage.setPageSize(Integer.valueOf(pageSize));
		model.addAttribute("orderpage", orderpage);
		return "modules/deal/matchedlist";
	}
	
	
	/**
	 * 返回正在确认的列表
	 * @param supplyid
	 * @param demandid
	 * @param details
	 * @param request
	 * @param response
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	//@RequiresPermissions("deal:tradeOrder:view")
	@RequestMapping(value = "detailsmatching")
	public String matching(@RequestParam(required=false) String pageSize,@RequestParam(required=false) String pageNo,@RequestParam(required=false) String param,@RequestParam(required=false) String flag,@RequestParam(required=false) String supplyid,@RequestParam(required=false) String demandid,Details details,HttpServletRequest request, HttpServletResponse response, Model model,RedirectAttributes redirectAttributes){				
		model.addAttribute("okshow","show");
		if(StringUtils.isNotBlank(flag)&&flag.equals("supply")){model.addAttribute("ok","supply");}else if(StringUtils.isNotBlank(flag)&&flag.equals("demand")){model.addAttribute("ok","demand");}
		if(StringUtils.isNotEmpty(supplyid)){details.setSupplyid(supplyid);
		}else if(StringUtils.isNotEmpty(demandid)){details.setDemandid(demandid);}else{return "modules/deal/supplydemanddetails";}		
		Page<Details>  supplydemandpage= tradeOrderSupplyDemandService.getDetails(new Page<Details>(request, response), details); 
		if(StringUtils.isNotBlank(pageNo.toString()))supplydemandpage.setPageNo(Integer.valueOf(pageNo));
		if(StringUtils.isNotBlank(pageSize))supplydemandpage.setPageSize(Integer.valueOf(pageSize));
		supplydemandpage.setFuncName("page1");
		model.addAttribute("supplydemandpage", supplydemandpage);
		System.out.println(supplydemandpage.getList().size());
		return "modules/deal/matchinglist";
	}
	
	
	/**
	 * 确认撮合即订单确认
	 * @param ordernumber 订单编号
	 * @param tosay 备注
	 * @param id  订单供求信息id
	 * @param oid 订单的id
	 * @param model  ordernumber="+ordernumber+"&tosay="+$("#tosay").text()+"&id="+sdid+"&oid="+idn
	 * @return
	 */
	@RequestMapping(value = "match")
	@ResponseBody
	public Map<String, String> match(@RequestParam(required = false) String ordernumber,
			@RequestParam(required = false) String tosay,@RequestParam(required = false) String id,@RequestParam(required = false) String oid, Model model) {
		TradeOrderSupplyDemand tradeOrderSupplyDemand = new TradeOrderSupplyDemand();
		TradeOrder tradeOrder  = new TradeOrder();		
		Map<String, String> map = new HashMap<String, String>();
		tradeOrder.setId(oid);//订单id		
		tradeOrderSupplyDemand.setRemarks(tosay);//卖家留言
		tradeOrderSupplyDemand.setId(id);//主键值
		try {
			tradeOrder.setUpdateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(DateUtils.formatDateTime(new Date())));
			tradeOrderSupplyDemandService.match(tradeOrder,tradeOrderSupplyDemand);
			map.put("success", "确认撮合成功.");
		} catch (Exception e) {
			map.put("false", "确认撮合失败.");
			e.printStackTrace();
		}
		return map;
	}
	
	/** 
	 * 展示订单交易状态详情
	 * @param orderid
	 * @return
	 */
	@RequestMapping(value = "orderdetails")
	@ResponseBody
	public Map<String,String> orderdetails(@RequestParam(required = false) String orderid) {
	     Map<String,String> map = new HashMap<String,String>();
		TradeOrder tradeOrder = new TradeOrder();
		tradeOrder.setOrderNumber(orderid);
		StringBuffer statusbuf = new StringBuffer();
		List<TradeOrder> orderstatuslist = tradeOrderService.getStatus(tradeOrder);
		for (TradeOrder order : orderstatuslist) {
			//System.out.println(order.getStatue());
			if (1 == order.getStatue()) {
				statusbuf.append("<tr class=\"tdcenter re\">");
				statusbuf.append("<td colspan=\"\" style=\"border-left:none;border-right:none;border-bottom:none;\">");
				statusbuf.append("阶段一：在洽谈中");//阶段状态要放入数据库字典
				statusbuf.append("</td>");
				statusbuf.append("<td colspan=\"\" style=\"border-left:none;border-right:none;border-bottom:none;\"></td>");
				statusbuf.append("<td colspan=\"\" style=\"border-left:none;border-right:none;border-bottom:none;\">");
				if(StringUtils.isNotEmpty(order.getRemarks())){statusbuf.append(order.getRemarks());}else{statusbuf.append("暂无说明。");}
				statusbuf.append("</td>");
				statusbuf.append("<td colspan=\"5\" style=\"border-left:none;border-right:none;border-bottom:none;\">");
				//statusbuf.append(order.getStatue());
				statusbuf.append("</td>");
				statusbuf.append("</tr>");
			}
			else if (2 == order.getStatue()) {
				statusbuf.append("<tr class=\"tdcenter re\">");
				statusbuf.append("<td colspan=\"\" style=\"border-left:none;border-right:none;border-bottom:none;\">");
				statusbuf.append("阶段二：订单确认");//
				statusbuf.append("</td>");
				statusbuf.append("<td colspan=\"\" style=\"border-left:none;border-right:none;border-bottom:none;\"></td>");
				statusbuf.append("<td colspan=\"\" style=\"border-left:none;border-right:none;border-bottom:none;\">");
				if(StringUtils.isNotEmpty(order.getRemarks())){statusbuf.append(order.getRemarks());}else{ statusbuf.append("暂无说明。");}
				statusbuf.append("</td>");
				statusbuf.append("<td colspan=\"5\" style=\"border-left:none;border-right:none;border-bottom:none;\">");
				//statusbuf.append(order.getStatue());
				statusbuf.append("</td>");
				statusbuf.append("</tr>");
			}	
			else if (3 == order.getStatue()) {
				statusbuf.append("<tr class=\"tdcenter re\">");
				statusbuf.append("<td colspan=\"\" style=\"border-left:none;border-right:none;border-bottom:none;\">");
				statusbuf.append("阶段三：等待付款");//
				statusbuf.append("</td>");
				statusbuf.append("<td colspan=\"\" style=\"border-left:none;border-right:none;border-bottom:none;\"></td>");
				statusbuf.append("<td colspan=\"\" style=\"border-left:none;border-right:none;border-bottom:none;\">");
				if(StringUtils.isNotEmpty(order.getRemarks())){statusbuf.append(order.getRemarks());	}else{statusbuf.append("暂无说明。");}
				statusbuf.append("</td>");
				statusbuf.append("<td colspan=\"5\" style=\"border-left:none;border-right:none;border-bottom:none;\">");
				//statusbuf.append(order.getStatue());
				statusbuf.append("</td>");
				statusbuf.append("</tr>");
			}	
			else if (4 == order.getStatue()) {
				statusbuf.append("<tr class=\"tdcenter re\">");
				statusbuf.append("<td colspan=\"\" style=\"border-left:none;border-right:none;border-bottom:none;\">");
				statusbuf.append("阶段四：等待发货");//
				statusbuf.append("</td>");
				statusbuf.append("<td colspan=\"\" style=\"border-left:none;border-right:none;border-bottom:none;\"></td>");
				statusbuf.append("<td colspan=\"\" style=\"border-left:none;border-right:none;border-bottom:none;\">");
				if(StringUtils.isNotEmpty(order.getRemarks())){statusbuf.append(order.getRemarks());	}else{statusbuf.append("暂无说明。");}
				statusbuf.append("</td>");
				statusbuf.append("<td colspan=\"5\" style=\"border-left:none;border-right:none;border-bottom:none;\">");
				//statusbuf.append(order.getStatue());
				statusbuf.append("</td>");
				statusbuf.append("</tr>");
			}	
			else if (5 == order.getStatue()) {
				statusbuf.append("<tr class=\"tdcenter re\">");
				statusbuf.append("<td colspan=\"\" style=\"border-left:none;border-right:none;border-bottom:none;\">");
				statusbuf.append("阶段五：交易完成");//阶段状态要放入数据库字典
				statusbuf.append("</td>");
				statusbuf.append("<td colspan=\"\" style=\"border-left:none;border-right:none;border-bottom:none;\"></td>");
				statusbuf.append("<td colspan=\"\" style=\"border-left:none;border-right:none;border-bottom:none;\">");
				if(StringUtils.isNotEmpty(order.getRemarks())){statusbuf.append(order.getRemarks());	}else{statusbuf.append("暂无说明。");}
				statusbuf.append("</td>");
				statusbuf.append("<td colspan=\"5\" style=\"border-left:none;border-right:none;border-bottom:none;\">");
				//statusbuf.append(order.getStatue());
				statusbuf.append("</td>");
				statusbuf.append("</tr>");
			}
			else if (0 == order.getStatue()) {
				statusbuf.append("<tr class=\"tdcenter re\">");
				statusbuf.append("<td colspan=\"\" style=\"border-left:none;border-right:none;border-bottom:none;\">");
				statusbuf.append("阶段五：交易取消");//阶段状态要放入数据库字典
				statusbuf.append("</td>");
				statusbuf.append("<td colspan=\"\" style=\"border-left:none;border-right:none;border-bottom:none;\"></td>");
				statusbuf.append("<td colspan=\"\" style=\"border-left:none;border-right:none;border-bottom:none;\">");
				if(StringUtils.isNotEmpty(order.getRemarks())){statusbuf.append(order.getRemarks());	}else{statusbuf.append("暂无说明。");}
				statusbuf.append("</td>");
				statusbuf.append("<td colspan=\"5\" style=\"border-left:none;border-right:none;border-bottom:none;\">");
				//statusbuf.append(order.getStatue());
				statusbuf.append("</td>");
				statusbuf.append("</tr>");
			}
		}
		map.put("success",statusbuf.toString());
		return map;
	}
	@RequestMapping(value = "queryOrder")
	public void queryOrderList(@RequestParam(required = false) String pids){
		for(String s:tradeOrderService.queryOrderList(pids)){
			System.out.println(s);
		}
	}
	
	/**
	 * 在订单列表页面点击跳转到详情页面（/tradeorderdetails），展示订单跟踪页面和订单详情页面
	 * @param orderId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "tradeorderdetails")
	public String tradeorderdetails(@RequestParam(required=false) String orderId,TradeOrder tradeOrder,Model model) {
		     //~新的获取信息的接口
		     //InfoVO supplyInfo = tradeOrderService.returnSupplyInfo(orderId);
		     InfoVO demandInfo = tradeOrderService.returnDemandInfo(orderId);
		     UserAddressInfo supplyAddressInfo = tradeOrderService.returnSupplyAddress(orderId);
		     UserAddressInfo demandAddressInfo = tradeOrderService.returnDemandAddress(orderId);
		     TradeOrder order = tradeOrderService.get(orderId);
		     //~--
		     
		    //:过时
		   // OrderItem supplyitem = tradeOrderService.findOrderSupplyDetails(orderId);	
		    //OrderItem demanditem = tradeOrderService.findOrderDemandDetails(orderId);
		    List<SupplyDemandInfo> supplyDemandInfo = tradeOrderService.findSupplyInfo(orderId);
		    //：过时
		    
		    
		    TradeOrderSupplyDemand tradeOrderSupplyDemand = tradeOrderSupplyDemandService.findSuppleyDemandByOrderId(orderId);//tradeOrderSupplyDemandService.findList(t).get(0);
		    
		    if(null!=order){tradeOrder = order;}else{
		    	tradeOrder = new TradeOrder();
		    	tradeOrder.setStatue(1);
		    }		    
			String showview = null;
			if(2==tradeOrder.getStatue()){showview = "orderpayment";}
			else if(3==tradeOrder.getStatue()){showview = "orderdeliver";}
			else if(4==tradeOrder.getStatue()){showview = "ordercomplete";}
			else if(5==tradeOrder.getStatue()){showview = "ordersuccess";}
			else if(0==tradeOrder.getStatue()){showview = "ordercancel";}
			else{showview = "orderconfirm";}	
			
			model.addAttribute("node","<script type=\"text/javascript\">$(\".orderflow\").setStep("+tradeOrder.getStatue()+");$(\"#"+showview+"\").show();</script>");	
			
			//过时
			//model.addAttribute("supplyitem",supplyitem);
			//model.addAttribute("demanditem",demanditem);			
			//model.addAttribute("supplyDemandInfo",supplyDemandInfo);
			//过时
			
			model.addAttribute("supplyDemandInfo",supplyDemandInfo);
			//model.addAttribute("supplyInfo",supplyInfo);//卖家信息
			model.addAttribute("demandInfo",demandInfo);//买家信息
			model.addAttribute("supplyAddressInfo",supplyAddressInfo);//发货地址
			//model.addAttribute("demandAddressInfo",demandAddressInfo);//收货地址
			//model.addAttribute("order",order);//订单数据信息	
			model.addAttribute("tradeOrderSupplyDemand",tradeOrderSupplyDemand);//订单业务供求信息表
						
			model.addAttribute("tradeOrder",tradeOrder);
			model.addAttribute("orderId",orderId);
			model.addAttribute("status",tradeOrder.getStatue());
			model.addAttribute("demandAddressInfo",demandAddressInfo);
		return "modules/deal/tradeOrderForm";
	}
 	
	/**
	 * 保存订单各自的状态数据
	 * @param orderId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "orderdesave")
	@ResponseBody
	public Map<String,String> orderdesave(
			@RequestParam(required=false) String remarks,//买家留言
			@RequestParam(required=false) String supplyremarks,//卖家留言
			@RequestParam(required=false) String goodstotalmoney,//产品总金额
			@RequestParam(required=false) String distributionfee,//配送费用
			@RequestParam(required=false) String poundage,//押金-支付手续费
			@RequestParam(required=false) String ordertotal,//订单总额
			@RequestParam(required=false) String tosay,//订单留言
			@RequestParam(required=false) String paymenttype,//付款类型
			@RequestParam(required=false) String payment,//支付方式
			@RequestParam(required=false) String distribution,//配送方式
			@RequestParam(required=false) String supplydemandId,//供求信息id
			@RequestParam(required=false) String param,//参数
			@RequestParam(required=false) String orderId,//订单id--
			@RequestParam(required=false) String number,//购买数量
			@RequestParam(required=false) String perPrice,//购买参考单价
			Model model) {
		Map<String,String> map = new HashMap<String,String>();
		TradeOrder tradeOrder  = new TradeOrder();
		if(StringUtils.isNotEmpty(goodstotalmoney)&&!"undefined".equals(goodstotalmoney)){tradeOrder.setGoodstotalmoney(Double.valueOf(goodstotalmoney));}
		if(StringUtils.isNotEmpty(distribution)&&!"undefined".equals(distribution)){tradeOrder.setDistribution(distribution);}
		if(StringUtils.isNotEmpty(poundage)&&!"undefined".equals(poundage)){tradeOrder.setPoundage(Double.valueOf(poundage));}
		if(StringUtils.isNotEmpty(ordertotal)&&!"undefined".equals(ordertotal)){tradeOrder.setOrdertotal(Double.valueOf(ordertotal));}
		if(StringUtils.isNotEmpty(tosay)&&!"undefined".equals(tosay)){tradeOrder.setRemarks(tosay);}
		if(StringUtils.isNotEmpty(paymenttype)&&!"undefined".equals(paymenttype)){tradeOrder.setPaymenttype(Integer.valueOf(paymenttype));}
		if(StringUtils.isNotEmpty(payment)&&!"undefined".equals(payment)){tradeOrder.setPayment(Integer.valueOf(payment));}
		if(StringUtils.isNotEmpty(orderId)&&!"undefined".equals(orderId)){tradeOrder.setId(orderId);}
		TradeOrderSupplyDemand tradeOrderSupplyDemand = new TradeOrderSupplyDemand();
		if(StringUtils.isNotBlank(perPrice)&&!"undefined".equals(perPrice)){tradeOrderSupplyDemand.setPerPrice(perPrice);}
		if(StringUtils.isNotBlank(number)&&!"undefined".equals(number)){tradeOrderSupplyDemand.setNumber(number);}
		if(StringUtils.isNotEmpty(remarks)&&!"undefined".equals(remarks)){tradeOrderSupplyDemand.setRemarks(remarks);}
		if(StringUtils.isNotEmpty(supplyremarks)&&!"undefined".equals(supplyremarks)){tradeOrderSupplyDemand.setSupplyRemarks(supplyremarks);}
		if(StringUtils.isNotEmpty(supplydemandId)&&!"undefined".equals(supplydemandId)){tradeOrderSupplyDemand.setId(supplydemandId);}				
		try{
			tradeOrderService.updateOrderDetails(tradeOrderSupplyDemand, tradeOrder);
			map.put("msg","更新数据成功.");
		}catch(Exception e){
			map.put("msg","更新数据失败,请联系管理员");		
			log.info("[fail]-更新数据失败,请联系管理员"+e.getMessage());
		}
		return map;
	}
	
	/**
	 * 
	 * @param id 进货单id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "deletejhds")
	@ResponseBody
	public Map<String,String> deleteJhds(@RequestParam(required = false) String id, Model model){
		System.out.println("=========="+id+"ity================");
		Map<String,String> map =new HashMap<String,String>();
		TradeOrderSupplyDemand tradeOrderSupplyDemand = new TradeOrderSupplyDemand();		
		try {
			System.out.println("==========start-----hellokity================");
			tradeOrderSupplyDemand.setDelFlag("-1");
			tradeOrderSupplyDemand.setId(id);
			tradeOrderSupplyDemand.setUpdateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(DateUtils.formatDateTime(new Date())));
			tradeOrderSupplyDemandService.deleteJhds(tradeOrderSupplyDemand);
			map.put("msg","删除进货单成功.");
		} catch (ParseException e) {
			map.put("msg","删除进货单失败.");
		}
		System.out.println("==========hellokity================");
		return map;
	}
	
}