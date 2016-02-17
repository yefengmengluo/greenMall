package com.wk.p3.greenmall.modules.advise.entry;

import java.util.Date;
import java.util.UUID;

import org.apache.ibatis.annotations.MapKey;

/**
 * 推荐信息类
 * @author Robin
 *
 */
public class Recommend{
	@SuppressWarnings("unused")
	private String id;
	private Date createDate;
	//推荐人
	private String userId;
	//状态
	private Integer status;
	//推荐类型
	private String  objectType;
	//推荐id
	private String objectId;
	//开始时间
	private Date startTime;
	//结束时间
	private Date endTime;
	//推荐类型
	private String recommendType;
	//商品类型
	private String goodsType;
	
	public String getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getObjectId() {
		return objectId;
	}
	/**
	 * @param objectId  推荐信息id
	 */
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getObjectType() {
		return objectType;
	}
	/**
	 * @param objectType  Recommend.ObjectType
	 */
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	public String getUserId() {
		return userId;
	}
	@MapKey("recommend_user")
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getId() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public String getRecommendType() {
		return recommendType;
	}
	public void setRecommendType(String recommendType) {
		this.recommendType = recommendType;
	}

	public enum ObjectType{
		MESSAGE(0),SUPPLIER(1),BUYERS(2) ;
		private ObjectType(Integer i){
			this.type=i;
		}
	    public String toString(){
             return this.type.toString();
        } 
	    private Integer type;
	}
	
	public enum RecommendType{
		PLATFORM(0),COMPANY(1);
		private RecommendType(Integer i){
			this.type=i;
		}
	    public String toString(){
             return this.type.toString();
        } 
	    private Integer type;
	}


}
