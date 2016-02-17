package com.wk.p3.greenmall.modules.info.service;

import com.wk.p3.greenmall.common.persistence.Page;
import com.wk.p3.greenmall.modules.deal.entity.TradeOrder;
import com.wk.p3.greenmall.modules.info.entity.Info;
import com.wk.p3.greenmall.modules.info.entity.InfoMessage;

import java.util.List;
import java.util.Map;

/**
 * Created by cc on 15-12-14.
 */

public interface InfoService {
    /**
     * 通过条件获得供应信息列表
     * @param info
     * @return
     */
    public List<Info> getSupply(Info info);


    /**
     * 查询分页数据
     * @param page 分页对象
     * @param entity
     * @return
     */
    public Page<Info> getSupplyPage(Page<Info> page, Info entity);

    /**
     * 通过条件获得供应信息条数
     * @param info
     * @return
     */
    public Long getSupplyCount(Info info);

    /**
     * 通过条件获得求购信息列表
     * @param info
     * @return
     */
    public List<Info> getDemand(Info info);

    /**
     * 查询分页数据
     * @param page 分页对象
     * @param entity
     * @return
     */
    public Page<Info> getDemandPage(Page<Info> page, Info entity);
    /**
     * 查询分页数据
     * @param page 分页对象
     * @param entity
     * @return
     */
    public Page<InfoMessage> getInfoMessagePage(Page<InfoMessage> page, InfoMessage entity);


    /**
     * 通过条件获得求购信息列表
     * @param info
     * @return
     */
    public List<Info> getCheckedDemand(Info info);

    /**
     * 查询分页数据
     * @param page 分页对象
     * @param entity
     * @return
     */
    public Page<Info> getCheckedDemandPage(Page<Info> page, Info entity);

    /**
     * 通过条件获得求供应息列表
     * @param info
     * @return
     */
    public List<Info> getCheckedSupply(Info info);

    /**
     * 查询分页数据
     * @param page 分页对象
     * @param entity
     * @return
     */
    public Page<Info> getCheckedSupplyPage(Page<Info> page, Info entity);

    /**
     * 通过条件获得求购信息条数
     * @param info
     * @return
     */
    public Long getDemandCount(Info info);


    /**
     * 更新求购信息的状态
     * @param info
     * @return
     */
    public int updateDemandStatus(Info info);
    /**
     * 更新求购信息的状态
     * @param info
     * @return
     */
    public int updateSupplyStatus(Info info);

    /**
     * 更新求购信息的 状态和数量，根据订单状态和数量
     * demandId 需求id
     * orderStatue 订单目前的状态
     * orderNumber 订单的成交数量
     * orderUnitCode 订单的成交数量的单位唯一标示  如 kg
     * @return {code:1,message:xxx}
     * code 1:成功，0：失败
     * message:信息
     */
    public Map updateDemandStatueNumberByOrderStatueNumber(String demandId,int orderStatue,double orderNumber,String orderUnitCode);


    /**
     * 查询审核前的委托信息
     * 查询分页数据
     * @param page 分页对象
     * @param entity
     * @return
     */
    public Page<Info> getEntrustDemandPage(Page<Info> page, Info entity);


    /**
     * 查询供应信息
     * 查询分页数据
     * @param page 分页对象
     * @param entity
     * @return
     */
    public Page<Info> getEntrustSupplyPage(Page<Info> page, Info entity);


    /**
     * 更新纯信息供求的状态
     * @param infoMessage
     */
    public void updateInfoMessage(InfoMessage infoMessage);


    /**
     * 保存 纯信息化的供求
     * @param infoMessage
     */
    public void saveInfoMessage(InfoMessage infoMessage);

    /**
     * 通过条件查询信息个数
     *
     * @return
     */
    public Integer findSupplyAndDemandCount(Info info);

}
