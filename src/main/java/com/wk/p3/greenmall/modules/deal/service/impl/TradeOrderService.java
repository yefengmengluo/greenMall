package com.wk.p3.greenmall.modules.deal.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.wk.p3.greenmall.common.persistence.Page;
import com.wk.p3.greenmall.common.service.CrudService;
import com.wk.p3.greenmall.modules.deal.entity.Details;
import com.wk.p3.greenmall.modules.deal.entity.InfoVO;
import com.wk.p3.greenmall.modules.deal.entity.Invok;
import com.wk.p3.greenmall.modules.deal.entity.OrderData;
import com.wk.p3.greenmall.modules.deal.entity.OrderItem;
import com.wk.p3.greenmall.modules.deal.entity.SupplyDemandInfo;
import com.wk.p3.greenmall.modules.deal.entity.TradeOrder;
import com.wk.p3.greenmall.modules.deal.entity.TradeOrderSupplyDemand;
import com.wk.p3.greenmall.modules.deal.entity.Unit;
import com.wk.p3.greenmall.modules.deal.service.Order;
import com.wk.p3.greenmall.modules.info.service.InfoService;
import com.wk.p3.greenmall.modules.user.entity.UserAddressInfo;
import com.wk.p3.greenmall.modules.deal.dao.SupplyDemandInfoDao;
import com.wk.p3.greenmall.modules.deal.dao.TradeOrderDao;
import com.wk.p3.greenmall.modules.deal.dao.TradeOrderSupplyDemandDao;

/**
 * 业务订单Service
 * @author lf
 * @version 2015-12-15
 */
@Service
@Transactional(readOnly = true)
public class TradeOrderService extends CrudService<TradeOrderDao, TradeOrder>implements Order {  
	private Log log = LogFactory.getLog(TradeOrderService.class);
	
	@Autowired
	protected TradeOrderDao dao;
	
	@Autowired
	protected TradeOrderSupplyDemandDao sddao;
	
	@Autowired
	protected TradeOrderSupplyDemandService sdservice;		
	
	@Autowired
	protected InfoService infoService;
	
	@Autowired
	protected SupplyDemandInfoDao supplyDemandIfnoDao;
	
	public TradeOrder get(String id) {
		return super.get(id);
	}
	
	/**
	 * 根据订单的id在订单表中查出一条订单数据
	 * @param id
	 * @return
	 */
	public TradeOrder getOrderByOrderId(String id){
		return dao.getOrderByOrderId(id);
	}

	/**
	 * 返回不带分页的结果集
	 */
	public List<TradeOrder> findList(TradeOrder tradeOrder) {
		String len = tradeOrder.getRemarks();
		List<TradeOrder> list = super.findList(tradeOrder);
		if (Invok.orderinvok.containsKey(len.substring(0, 15))) {
			return list.subList(0, Integer.valueOf(len.substring(16)));
		}
		return list;
	}

    /**
     * 通过类别查询最近的15条订单信息
	 * 参数：String id (Gcategory)
	 * 返回 买家姓名+品牌+产品+数量+交易状态+更新时间
	 * 如：  张三  红富士  苹果   10吨    交易成功    时间
     * @param pids
     * @return
     */
	public List<String> queryOrderList(String pids){
		System.out.println(pids);
		List<String> listOrder = new ArrayList<String>();
		StringBuffer buf = null;
		String strpids = "";
		if(!pids.equals("0")){
			strpids = "," + pids + ",";
		}else {
			strpids =pids + ",";
		}	
		
		for(OrderData od:dao.queryOrder(strpids)){
			buf = new StringBuffer();
			buf.append(od.getDemandname());
			buf.append(",");
			buf.append(od.getGoodsname());
			buf.append(",");
			buf.append(od.getPgoodsname());
			buf.append(",");
			buf.append(od.getNumber());
			buf.append("吨");//这里待单位转化
			buf.append(",");
			buf.append(od.getStatue());
			buf.append(",");
			buf.append(od.getUpdateDate());
			listOrder.add(buf.toString());
		}
		return listOrder;
	}
	
	/**
	 * 带分页的结果集
	 */
	public Page<TradeOrder> findPage(Page<TradeOrder> page, TradeOrder tradeOrder) {
		return super.findPage(page, tradeOrder);
	}
	
	/**
	 * 获取待跟踪状态的订单
	 * @author lf
	 * @param page
	 * @param tradeOrder
	 * @return 分页对象
	 * @exception/throws [此处异常不做处理]
	 * @see   [类#方法]
	 * @since [果果正式版V1.0]
	 * create Date <创建日期，格式:February 14, 2016>
	 */
	public Page<TradeOrder> getOrderIng(Page<TradeOrder> page, TradeOrder tradeOrder) {
		    tradeOrder.setPage(page);
	        page.setList(dao.getOrderIng(tradeOrder));
	        return page;
	}
	
	
	
	/**
	 * 返回待跟踪订单的数量
	 * @author lf
	 * @param tradeOrder
	 * @return 分页对象
	 * @exception/throws [此处异常不做处理]
	 * @see   [类#方法]
	 * @since [果果正式版V1.0]
	 * create Date <创建日期，格式:February 15, 2016>
	 */
	public Integer getOrderIngCount(TradeOrder tradeOrder){
		return dao.getOrderIng(tradeOrder).size();
	}
	
	
	/**
	 * 订单更新
	 * 软删除也，只要更新订单的状态statue为0即可。
	 * @param tradeOrder
	 */
	@Transactional(readOnly = false)
	public void update(TradeOrder tradeOrder){
		dao.updateData(tradeOrder);
	}
	 
	/**
	 *增加流水,增加流水的时候，执行两个任务，第一个就是更新当前的订单最新数据，第二个就是在流水表中增加一条流水作为日志记录
	 */
	@Transactional(readOnly = false)
	public void save(TradeOrder tradeOrder){
		dao.updateData(tradeOrder);
		TradeOrderSupplyDemand sd = new TradeOrderSupplyDemand();
		if(2==tradeOrder.getStatue()){sd.setDelFlag("1");sddao.update(sd);}//更新订单供求信息s的状态为1表示确认撮合即订单确认			
		/**更新求购信息的 状态和数量，根据订单状态和数量 * demandId 需求id * orderStatue 订单目前的状态 * orderNumber 订单的成交数量 * 
		 * orderUnitCode 订单的成交数量的单位唯一标示  如 kg * @return {code:1,message:xxx} * code 1:成功，0：失败 * message:信息 */
		TradeOrderSupplyDemand  tradeOrderSupplyDemand  = new TradeOrderSupplyDemand();
		
		String demandId = null;
		Integer orderStatue = tradeOrder.getStatue();
		Double orderNumber = 0.00;
		String orderUnitCode = null;
		
		tradeOrderSupplyDemand.setOrderId(tradeOrder.getId());
		//List<TradeOrderSupplyDemand> tradeOrderSupplyDemandList = sddao.findList(tradeOrderSupplyDemand);
		TradeOrderSupplyDemand to = sddao.get(tradeOrderSupplyDemand);
		//for(TradeOrderSupplyDemand to:tradeOrderSupplyDemandList){
		   if(null!=to&&StringUtils.isNotBlank(to.getDemandId())) demandId = to.getDemandId();else log.info("【TradeOrderSupplyDemand】为null或者id为空，请核实");			
			orderNumber = Double.valueOf(to.getNumber());
			Unit unit = new Unit();
			unit.setNumberUnitId(to.getNumberUnitId());
			if(null!=sddao.unitCateGory(unit)) orderUnitCode = sddao.unitCateGory(unit).getUnitCode();else orderUnitCode = "";
			//这里的产品数量和产品状态以及单位代码表示的一种产品的规格
			Map map= infoService.updateDemandStatueNumberByOrderStatueNumber(demandId, orderStatue, orderNumber, orderUnitCode);//经过沟通，这里的orderNumber是每条供求信息的产品数量
			//System.out.println("===============" + map.get("code"));
			//System.out.println("===============" + map.get("message"));
		//}		
		tradeOrder.setIsNewRecord(true);
		tradeOrder.setId(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
		dao.addOrderFlow(tradeOrder);		
	}
	
	@Transactional(readOnly = false)
	public void delete(TradeOrder tradeOrder) {
		super.delete(tradeOrder);
		TradeOrderSupplyDemand tradeOrderSupplyDemand = new TradeOrderSupplyDemand();
		tradeOrderSupplyDemand.setOrderId(tradeOrder.getOrderNumber());
		sdservice.delete(tradeOrderSupplyDemand);
	}

	/**
	 * 参数为订单对象
	 * return 订单对象
	 */
	@Override
	public TradeOrder getOrder(TradeOrder t) throws Exception {
		TradeOrder trade = null;
		try {
			trade = dao.getOrderByOrderId(t.getOrderNumber());
			if (null != trade) {
				trade.setRemarks("调用成功.");
				return trade;
			} else {
				trade = new TradeOrder();
				trade.setRemarks("返回值为空。或者参数错误，请检查。");
				log.info("[error]返回值为空。或者参数错误，请检查。");
			}
		} catch (Exception e) {
			trade = new TradeOrder();
			trade.setRemarks(e.getMessage());
			log.info("[error]"+e.getMessage());
		}
		return trade;
	}

	 /**
	     *  请求规范，
	     *  TradeOrder 参数为订单对象
	     *  count 要返回的记录数
	     *  return 返回订单对象列表
	     */
	@Override
	public List<TradeOrder> resultList(TradeOrder t, String count) throws Exception {
		List<TradeOrder> list  = null;
		TradeOrder trade = new TradeOrder();
		try {			
			list = super.findList(t);
			if (StringUtils.isNotEmpty(count)) {
				if (null != list) {
					log.info("参数："+t.toString()+"-数量："+count);
					return list.subList(0, Integer.valueOf(count));
				} else {
					list = new ArrayList<TradeOrder>();
					trade.setRemarks("返回值为空。或者参数错误，请检查。");
					log.info("参数："+t.toString()+"-数量："+count);
					list.add(trade);
				}
			}else{
				list = new ArrayList<TradeOrder>();
				trade.setRemarks("请填写要返回的数量");
				list.add(trade);
			}
		} catch (Exception e) {
			list = new ArrayList<TradeOrder>();
			trade.setRemarks(e.getMessage());
			list.add(trade);
		}
		return list;
	}
   
	@Override
	public Page<Details> getDetails(Page<Details> page,Details Details) {
		Details.setPage(page);
		List<Details> details = dao.getDetails(Details);
		page.setList(details);
		return page;
	}
	
	public List<TradeOrder> getStatus(TradeOrder tradeOrder){		
		return dao.getStatus(tradeOrder);		
	}
	
	/**
	 * 卖家角度
	 * 返回订单的详情对象
	 * @param orderId
	 * @return
	 */
	public OrderItem findOrderSupplyDetails(String orderId){		
		return dao.findOrderSupplyDetails(orderId);
	}
	
	/**
	 * 买家角度
	 * 返回订单的详情对象
	 * @param orderId
	 * @return
	 */
	public OrderItem findOrderDemandDetails(String orderId){		
		return dao.findOrderDemandDetails(orderId);
	}
	
	/**
	 * 返回采购单信息
	 * @param orderId
	 * @return
	 */
	public List<SupplyDemandInfo> findSupplyInfo(String orderId){		
		return dao.findSupplyInfo(orderId);
	}
	
	
	//新增查询供求信息接口
	/**
	 * 返回供应的信息
	 * @param orderId
	 * @return
	 */
	public Page<SupplyDemandInfo> querySupplyInfo(Page<SupplyDemandInfo> page,SupplyDemandInfo supplyDemandInfo){		
		supplyDemandInfo.setPage(page);
		page.setList(supplyDemandIfnoDao.querySupplyInfo(supplyDemandInfo));
		return page;
	}
	
	/**
	 * 返回采购信息
	 * @param orderId
	 * @return
	 */
	public Page<SupplyDemandInfo> queryDemandInfo(Page<SupplyDemandInfo> page,SupplyDemandInfo supplyDemandInfo) {
		supplyDemandInfo.setPage(page);
		page.setList(supplyDemandIfnoDao.queryDemandInfo(supplyDemandInfo));
		return page;
	}
	
	//~下面是新增的接口
	
	public InfoVO returnSupplyInfo(String orderid){
		return dao.returnSupplyInfo(orderid);
	}//返回卖家信息
	public InfoVO returnDemandInfo(String orderid){
		return dao.returnDemandInfo(orderid);
	}//返回买家信息
	public UserAddressInfo returnDemandAddress(String orderid){
		return dao.returnDemandAddress(orderid);
	}//返回收货地址
	public UserAddressInfo returnSupplyAddress(String orderid){
		return dao.returnSupplyAddress(orderid);
	}//返回发货地址
	
	//~--
	
	/**
	 * 更新订单详情数据
	 * @param tradeOrderSupplyDemand
	 * @param tradeOrder
	 */
	@Transactional(readOnly = false)
	public void updateOrderDetails(TradeOrderSupplyDemand tradeOrderSupplyDemand,TradeOrder tradeOrder){
		if(null!=tradeOrder){
			if(null!=tradeOrder.getId()){
				if(StringUtils.isNotBlank(tradeOrderSupplyDemand.getNumber())&&StringUtils.isNotBlank(tradeOrderSupplyDemand.getPerPrice())){
					tradeOrder.setGoodsAmount(String.valueOf(Double.valueOf(tradeOrderSupplyDemand.getNumber())*Double.valueOf(tradeOrderSupplyDemand.getPerPrice())));
				    tradeOrder.setGoodstotalmoney(Double.valueOf(tradeOrder.getGoodsAmount()));	
				}
				else{tradeOrder.setGoodsAmount(String.valueOf(tradeOrder.getGoodstotalmoney()));}
                tradeOrder.setOrdertotal(tradeOrder.getGoodstotalmoney()+tradeOrder.getPoundage());               
				dao.updateData(tradeOrder);
			}else{
				log.info("tradeOrder.id == null,请核实");
				System.out.println("tradeOrder.id == null,请核实");
			}			
		}else{
			log.info("tradeOrder=null,请核实");
			System.out.println("tradeOrder=null,请核实");
		}
		if(null!=tradeOrderSupplyDemand){
			if(null!=tradeOrderSupplyDemand.getId()){			   
				sddao.update(tradeOrderSupplyDemand);
		    }else{
		    	log.info("tradeOrderSupplyDemand.id == null,请核实");
		    	System.out.println("tradeOrderSupplyDemand.id == null,请核实");
		    }			
		}else{
			log.info("tradeOrderSupplyDemand=null,请核实");
			System.out.println("tradeOrderSupplyDemand=null,请核实");
		}				
	}
}