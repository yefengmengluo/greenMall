package com.wk.p3.greenmall.modules.info.dao;

import java.util.List;

import com.wk.p3.greenmall.modules.info.entity.InfoImage;
import com.wk.p3.greenmall.modules.info.entity.InfoMessage;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import com.wk.p3.greenmall.common.persistence.CrudDao;
import com.wk.p3.greenmall.common.persistence.annotation.MyBatisDao;
import com.wk.p3.greenmall.modules.info.entity.Info;

/**
 * Created by liujiabao on 2015/12/18 0018.
 */
@MyBatisDao
public interface InfoDao extends CrudDao<Info> {

    public int updateInfoStatus(Info info);
    public List<Info> getInfos(Info info);
    public List<InfoMessage> getInfoMessages(InfoMessage infoMessage);
    public List<Info> getCheckedInfos(Info info);
    public List<Info> getEntrustDemandPage(Info info);
    public List<Info> getEntrustSupplyPage(Info info);
    public List<Info> findInfoTable(Info info);
    public long findInfoCount(Info info);
    public void recordBeforeCheckedData(Info info);
	@Select("select id from supply_demand_info")
	@ResultType(List.class)
	public List<String> listInfoIds();

    public void deleteInfoMessage(InfoMessage infoMessage);
    //更新状态
    public void updateInfoMessage(InfoMessage infoMessage);
    public void insertInfoMessage(InfoMessage infoMessage);

    /**
     * 获取单条数据
     * @param id
     * @return
     */
    public InfoMessage getInfoMessage(String id);
    public void saveInfoImage(InfoImage infoImage);

    /**
     * 获取匹配条件的供求信息数量
     *
     * @param info
     * @return
     */
    Integer findSupplyAndDemandCount(Info info);
    List<String> getImagesByInfoId(String id);
}
