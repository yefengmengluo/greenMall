package com.wk.p3.greenmall.modules.deal.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.wk.p3.greenmall.common.utils.DateUtils;
import com.wk.p3.greenmall.common.utils.IdGen;
import com.wk.p3.greenmall.modules.deal.entity.Details;
import com.wk.p3.greenmall.modules.deal.entity.SupplyDemandInfo;
import com.wk.p3.greenmall.modules.deal.entity.TradeOrder;
import com.wk.p3.greenmall.modules.deal.entity.TradeOrderSupplyDemand;
import com.wk.p3.greenmall.modules.deal.entity.Unit;
import com.wk.p3.greenmall.modules.info.service.InfoService;
import com.wk.p3.greenmall.modules.sys.entity.User;
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
public class TradeOrderSupplyDemandService extends CrudService<TradeOrderSupplyDemandDao, TradeOrderSupplyDemand>{
	private Log log = LogFactory.getLog(TradeOrderService.class);
	@Autowired
	private SupplyDemandInfoDao supplyDemandInfoDao;
	
	@Autowired
	private TradeOrderDao tradeOrderDao;
	@Autowired
	protected InfoService infoService;
	
	@Autowired
	protected TradeOrderSupplyDemandDao sddao;
	
	public TradeOrderSupplyDemand get(String id) {
		return super.get(id);
	}
	
	public List<TradeOrderSupplyDemand> findList(TradeOrderSupplyDemand tradeOrderSupplyDemand) {
		return super.findList(tradeOrderSupplyDemand);
	}
	
	public Page<TradeOrderSupplyDemand> findPage(Page<TradeOrderSupplyDemand> page, TradeOrderSupplyDemand tradeOrderSupplyDemand) {
		return super.findPage(page, tradeOrderSupplyDemand);
	}
	
	/**
	 * 创建订单保存数据信息
	 */
	@Transactional(readOnly = false)
	public void save(TradeOrderSupplyDemand tradeOrderSupplyDemand){	
		List<TradeOrderSupplyDemand> tradeOrderSupplyDemandList = sddao.findAllList();
		boolean ok = false;
		for(TradeOrderSupplyDemand t:tradeOrderSupplyDemandList){
			if(tradeOrderSupplyDemand.getSupplyId().equals(t.getSupplyId())&&tradeOrderSupplyDemand.getDemandId().equals(tradeOrderSupplyDemand.getDemandId())){
				ok = true;
			}
		}
		if(!ok){
		//供求信息表
		try{SupplyDemandInfo supply=supplyDemandInfoDao.get(tradeOrderSupplyDemand.getSupplyId());
		SupplyDemandInfo demand=supplyDemandInfoDao.get(tradeOrderSupplyDemand.getDemandId());
		TradeOrder tradeOrder = new TradeOrder();
		tradeOrder.setOrderNumber(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
		try {
			tradeOrder.setAddDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(DateUtils.formatDateTime(new Date())));
		} catch (ParseException e1) {}
		tradeOrder.setStatue(1);
		tradeOrder.setId(IdGen.uuid());
		if(null!=supply&&StringUtils.isNotEmpty(supply.getProductionProvinceName())){tradeOrder.setReceiveProvince(supply.getProductionProvinceName());}
		if(null!=supply&&StringUtils.isNotEmpty(supply.getProductionCityName())){tradeOrder.setReceiveCity(supply.getProductionCityName());}
		if(null!=supply&&StringUtils.isNotEmpty(supply.getProductionDetailArea())){tradeOrder.setReceiveDetailArea(supply.getProductionDetailArea());}		
		//tradeOrder.setCashpledge(300.99);
		tradeOrder.setDistribution("快递");
		tradeOrder.setPayment(1);
		tradeOrder.setPaymenttype(2);
		tradeOrder.setPoundage(200.00);//手续费
		tradeOrder.setDistributionfee(20.00);
		//tradeOrder.setSupplycommission(234.65);
		tradeOrder.setTransactiontype(1);
		User supplyuser = tradeOrderDao.supplyDemandUserInfo(supply.getPublishUser());
		User demanduser = tradeOrderDao.supplyDemandUserInfo(supply.getPublishUser());
		tradeOrder.setSupplyUserName(supplyuser.getName());
		tradeOrder.setDemandName(demanduser.getName());
		        
		
        TradeOrderSupplyDemand tradesupplydemand= new TradeOrderSupplyDemand();
        
        
        Unit demandUnit = new Unit();
        demandUnit.setpGoodsId(demand.getPgoodsId());
        Unit demandunitCode = null;//求购产品的默认单位code
        if(StringUtils.isNotBlank(demandUnit.getpGoodsId())){demandunitCode=dao.unitCateGory(demandUnit);}else{demandunitCode=new Unit();}
        //List<String> demandpriceunitCode = dao.unitCateGory(null,demand.getPriceUnitId(),null);//求购信息的价格单位code
        demandUnit.setpGoodsId(null);
        if(null!=demand.getNumberUnitId())demandUnit.setNumberUnitId(String.valueOf(demand.getNumberUnitId()));
        Unit demandnumberunitCode = null;//求购信息的数量单位code
        if(StringUtils.isNotBlank(demandUnit.getNumberUnitId())){demandnumberunitCode = dao.unitCateGory(demandUnit);}else{demandnumberunitCode = new Unit();}               
        
        Unit supplyUnit = new Unit();
        supplyUnit.setpGoodsId(supply.getPgoodsId());
        
        Unit supplyunitCode = null;//供应产品的默认单位code
        if(StringUtils.isNoneBlank(supplyUnit.getpGoodsId())){dao.unitCateGory(supplyUnit);}else{supplyunitCode = new Unit();}
        supplyUnit.setpGoodsId(null);
        if(null!=supply.getPriceUnitId()) supplyUnit.setPriceUnitId(String.valueOf(supply.getPriceUnitId()));
        Unit supplypriceUnitCode =  null;//供应信息的价格单位code
        if(StringUtils.isNoneBlank(supplyUnit.getPriceUnitId())){supplypriceUnitCode= dao.unitCateGory(supplyUnit);}else{}
        supplyUnit.setPriceUnitId(null);
        if(null!=supply.getNumberUnitId())supplyUnit.setNumberUnitId(String.valueOf(supply.getNumberUnitId()));        
        Unit supplynumberUnitCode = null;//供应数量信息的单位code
        if(StringUtils.isNotBlank(supplyUnit.getNumberUnitId())){supplynumberUnitCode = dao.unitCateGory(supplyUnit);}else{supplynumberUnitCode = new Unit();}
        
        if(null!=supply&&StringUtils.isNotEmpty(supply.getPriceUnitValue())){tradesupplydemand.setPriceUnitValue(supply.getPriceUnitValue());}
		if(null!=supply&&StringUtils.isNotEmpty(supply.getId())){tradesupplydemand.setSupplyId(supply.getId());}				
		if(null!=demand&&StringUtils.isNotEmpty(demand.getId())){tradesupplydemand.setDemandId(demand.getId());}			
		if(null!=supply&&StringUtils.isNotEmpty(supply.getSpecs())){tradesupplydemand.setSpec(supply.getSpecs());}	//这里是手动设置的规格
		
		if(null!=supply&&null!=supplyunitCode&&!supplyunitCode.getUnitCode().equals(supplypriceUnitCode.getUnitCode())){//如果不等于默认值这里需要进行单位转换，同种产品的单价单位和数量单位的默认单位是一样的
        	 String pricemultiplier = null;
        	 if(StringUtils.isNotBlank(supplypriceUnitCode.getUnitCode())&&StringUtils.isNotBlank(supplyunitCode.getUnitCode())){pricemultiplier = dao.unitRelation(supplypriceUnitCode.getUnitCode(), supplyunitCode.getUnitCode());}
        	 //设置单价
        	 tradesupplydemand.setPerPrice(String.valueOf((supply.getToPerPrice()-supply.getFromPerPrice()/2)*Double.valueOf(pricemultiplier))); 
        	 if(StringUtils.isNotBlank(supplyunitCode.getId())){tradesupplydemand.setPriceUnitId(supplyunitCode.getId());}
        	 if(StringUtils.isNotBlank(supplyunitCode.getUnitName())){tradesupplydemand.setPriceUnitValue("元/"+supplyunitCode.getUnitName());}
        }else{      	
        	if(null!=supply){
        	//添加单价  目前价格是个区间，需要交易员或者交易双方自己商定，这里的单价取得是供应商产品报价的总和的一半,只是作为参考，暂不作为计算产品总价的依据
        	if(null!=supply.getToPerPrice()&&null!=supply.getFromPerPrice())tradesupplydemand.setPerPrice(String.valueOf((supply.getToPerPrice()-supply.getFromPerPrice())/2));
        	if(null!=supply.getPriceUnitId()) tradesupplydemand.setPriceUnitId(String.valueOf(supply.getPriceUnitId()));
        	if(StringUtils.isNotBlank(supply.getPriceUnitValue()))tradesupplydemand.setPriceUnitValue(supply.getPriceUnitValue());
        	}
        }
         //设置数量
	   	 if(null!=supply&&null!=supplyunitCode&&!supplyunitCode.getUnitCode().equals(supplynumberUnitCode.getUnitCode())){//需要进行单位转换
	   		 String supplynumbermultiplier = dao.unitRelation(supplynumberUnitCode.getUnitCode(), supplyunitCode.getUnitCode());             
	   		 Double supplynumber = Double.valueOf(supply.getNumber())*Double.valueOf(supplynumbermultiplier);
	   		Double demandnumber = null;
	   		 if(null!=demand&&null!=demandunitCode&&!demandunitCode.getUnitCode().equals(demandnumberunitCode.getUnitCode())){
	   			String demandnumbermultiplier = null; 
	   			if(StringUtils.isNotBlank(demandnumberunitCode.getUnitCode())&&StringUtils.isNotBlank(demandunitCode.getUnitCode())){demandnumbermultiplier = dao.unitRelation(demandnumberunitCode.getUnitCode(), demandunitCode.getUnitCode());}
	   			if(StringUtils.isNotBlank(demandnumbermultiplier))demandnumber = supply.getNumber()*Double.valueOf(demandnumbermultiplier);
	   			if(supplynumber>demandnumber){//
	   				if(null!=demandnumber)tradesupplydemand.setNumber(String.valueOf(demandnumber));
	   				tradesupplydemand.setNumberUnitId(demandnumberunitCode.getId());
	   				tradesupplydemand.setNumberUnitValue(demandnumberunitCode.getUnitName());	   				
	   			}else{//
	   				if(null!=supplynumber)tradesupplydemand.setNumber(String.valueOf(supplynumber));
	   				tradesupplydemand.setNumberUnitId(supplynumberUnitCode.getId());
	   				tradesupplydemand.setNumberUnitValue(supplynumberUnitCode.getUnitName());
	   			}
	   		 }else{
	   			 if(null!=demand.getNumber()&&supplynumber>demand.getNumber()){//
	   				if(null!=demand.getNumber())tradesupplydemand.setNumber(String.valueOf(demand.getNumber()));
	   				if(null!=demand.getNumberUnitId())tradesupplydemand.setNumberUnitId(String.valueOf(demand.getNumberUnitId()));
	   				tradesupplydemand.setNumberUnitValue(demand.getNumberUnitValue());
	   			 }else{//
	   				if(null!=supplynumber)tradesupplydemand.setNumber(String.valueOf(supplynumber));
	   				tradesupplydemand.setNumberUnitId(supplynumberUnitCode.getId());
	   				tradesupplydemand.setNumberUnitValue(supplynumberUnitCode.getUnitName());
	   			 }
	   		 }
	   	 }else{
	   		if(null!=demand&&null!=demandunitCode&&!demandunitCode.getUnitCode().equals(demandnumberunitCode.getUnitCode())){
	   			String demandnumbermultiplier = dao.unitRelation(demandnumberunitCode.getUnitCode(), demandunitCode.getUnitCode()); 
	   			Double demandnumber = null;
	   			if(null!=supply.getNumber()&&null!=demandnumbermultiplier)demandnumber = Double.valueOf(supply.getNumber())*Double.valueOf(demandnumbermultiplier);
	   			if(null!=supply.getNumber()&&supply.getNumber()>demandnumber){//
	   				if(null!=demandnumber)tradesupplydemand.setNumber(String.valueOf(demandnumber));
	   				tradesupplydemand.setNumberUnitId(demandnumberunitCode.getId());
	   				tradesupplydemand.setNumberUnitValue(demandnumberunitCode.getUnitName());
	   			}else{//
	   				if(null!=supply.getNumber())tradesupplydemand.setNumber(String.valueOf(supply.getNumber()));
	   				if(null!=supply.getNumberUnitId())tradesupplydemand.setNumberUnitId(String.valueOf(supply.getNumberUnitId()));
	   				tradesupplydemand.setNumberUnitValue(supply.getNumberUnitValue());
	   			}
	   		 }else{
	   			 if(null!=supply.getNumber()&&supply.getNumber()>demand.getNumber()){//
	   				if(null!=demand.getNumber())tradesupplydemand.setNumber(String.valueOf(demand.getNumber()));
	   				if(null!=demand.getNumberUnitId())tradesupplydemand.setNumberUnitId(String.valueOf(demand.getNumberUnitId()));
	   				tradesupplydemand.setNumberUnitValue(demand.getNumberUnitValue());
	   			 }else{//
	   				if(null!=supply.getNumber())tradesupplydemand.setNumber(String.valueOf(supply.getNumber()));
	   				if(null!=supply.getNumberUnitId())tradesupplydemand.setNumberUnitId(String.valueOf(supply.getNumberUnitId()));
	   				tradesupplydemand.setNumberUnitValue(supply.getNumberUnitValue());
	   			 }
	   		 }
	   	 } 
	    //设置总价
	   	//System.out.println(tradesupplydemand.getPerPrice()+"=====*====="+tradesupplydemand.getNumber());
		if(StringUtils.isNotBlank(tradesupplydemand.getPerPrice())&&StringUtils.isNotBlank(tradesupplydemand.getNumber()))tradesupplydemand.setTotalPrice(String.valueOf(Double.valueOf(tradesupplydemand.getPerPrice())*Double.valueOf(tradesupplydemand.getNumber())));
		tradesupplydemand.setDelFlag("0");				
		tradesupplydemand.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(DateUtils.formatDateTime(new Date())));
		tradesupplydemand.setOrderId(tradeOrder.getId());
		tradesupplydemand.setId(IdGen.uuid());
		if(null!=supply){tradesupplydemand.setGoodsName(supply.getGoodsName());}
		if(null!=supply){tradesupplydemand.setPgoodsName(supply.getPgoodsName());}
		
		tradeOrder.setGoodsAmount(tradesupplydemand.getTotalPrice());//总价钱
		if(StringUtils.isNotBlank(tradesupplydemand.getTotalPrice())) tradeOrder.setGoodstotalmoney(Double.valueOf(tradesupplydemand.getTotalPrice()));//产品总额
		if(StringUtils.isNotBlank(tradeOrder.getGoodsAmount()))tradeOrder.setOrdertotal(Double.valueOf(tradeOrder.getGoodsAmount())+tradeOrder.getPoundage());//订单总额		
		tradeOrderDao.insert(tradeOrder);//保存订单
		//~-：新添加的功能		
		//TradeOrderSupplyDemand to = sddao.get(tradesupplydemand);
		TradeOrderSupplyDemand to=tradesupplydemand;
		//if(null!=to&&StringUtils.isNotBlank(to.getDemandId())) demandId = to.getDemandId();else log.info("【TradeOrderSupplyDemand】为null或者id为空，请核实");			
		//orderNumber = Double.valueOf(to.getNumber());
		String orderUnitCode = null;
		Unit unit = new Unit();
		unit.setNumberUnitId(to.getNumberUnitId());
		if(null!=sddao.unitCateGory(unit)) orderUnitCode = sddao.unitCateGory(unit).getUnitCode();else orderUnitCode = "";
		Double orderNumber = Double.valueOf(tradesupplydemand.getNumber());
		infoService.updateDemandStatueNumberByOrderStatueNumber(tradesupplydemand.getDemandId(), tradeOrder.getStatue(),orderNumber, orderUnitCode);//经过沟通，这里的orderNumber是每条供求信息的产品数量
		//System.out.println("===============" + map.get("code"));
		//System.out.println("===============" + map.get("message"));
       //~：
		tradeOrderDao.addOrderFlow(tradeOrder);//保存到历史表
		dao.insert(tradesupplydemand);
		}catch(Exception e){
			e.printStackTrace();
		}
		}else{
			log.info("["+tradeOrderSupplyDemand.getSupplyId()+"]与"+"["+tradeOrderSupplyDemand.getDemandId()+"]已经下单");
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(TradeOrderSupplyDemand tradeOrderSupplyDemand) {
		super.delete(tradeOrderSupplyDemand);
	}
	
	/**
	 * @param tradeOrderSupplyDemand
	 * @return
	 */
	public Page<Details> getDetails(Page<Details> page,Details Details){
		Details.setPage(page);
		page.setList(dao.getDetails(Details));
		return page;
	} 
	
	/**
	 * 本接口的作用是更新撮合转态完成时的订单和业务订单信息表
	 * @param tradeOrderSupplyDemand
	 */
	@Transactional(readOnly = false)
	public void match(TradeOrder tradeOrder,TradeOrderSupplyDemand tradeOrderSupplyDemand){	
		tradeOrder.setStatue(2);		
		tradeOrderDao.updateData(tradeOrder);//更新订单状态
		TradeOrder to = tradeOrderDao.get(tradeOrder.getId());
		to.setId(IdGen.uuid());		
		tradeOrderDao.addOrderFlow(to);
		
		tradeOrderSupplyDemand.setDelFlag("1");
		dao.update(tradeOrderSupplyDemand);
	}
	
	/**
	 * 删除进货单
	 * @param tradeOrderSupplyDemand
	 */
	@Transactional(readOnly = false)
	public void deleteJhds(TradeOrderSupplyDemand tradeOrderSupplyDemand){
		dao.deleteJhds(tradeOrderSupplyDemand);
	}
	public TradeOrderSupplyDemand findSuppleyDemandByOrderId(String orderId){
		return dao.findSuppleyDemandByOrderId(orderId);
	}
}