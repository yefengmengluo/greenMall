
package com.wk.p3.greenmall.modules.deal.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.wk.p3.greenmall.common.persistence.DataEntity;

/**
 * 业务订单Entity
 * @author lf
 * @version 2015-12-25
 */
public class SupplyDemandInfo extends DataEntity<SupplyDemandInfo> {
	
	private static final long serialVersionUID = 1L;
	private String goodsName;		// 产品，gcategory表的name
	private String pgoodsAlias;		// 产品别名
	private String goodsId;		// 产品id，商品类型表外键
	private String pgoodsId;		// 产品上级id
	private String pgoodsName;		// 产品上级name
	private String pids;		// 产品pids 代码
	private String priceUnitId;		// 价格单元id
	
	//添加的字段
	private Double nonstandardNumber;
	private Integer  nonstandardNumberUnitId;
	private String nonstandardNumberUnitName;
	private String remarks;
	
	private String numberUnitId;		// 数量单元id
	private String priceUnitValue;		// 价格单元
	private String numberUnitValue;		// 数量单元
	private Double number;		// 数量
	private Double fromPerPrice;		// from_per_price
	private Double toPerPrice;		// to_per_price
	private String specs;		// 手动填写的规格
	
	private String statue;		// 状态(默认0：待审核，1：已审核通过，-1：审核未通过，-2：删除,2:正在洽谈,3：等待打款，4：交易成功)
	private String statueIntro;		// 审核说明
	private String type;		// 供求类型（supply:供应，demand:求购）
	private String isEntrust;		// 是否是委托（默认0：否，1：是）
	private String entrustSupplyDemandId;		// 委托的供求信息id
	private String entrustOrganizationId;		// 委托人公司id
	private String addressId;		// 地址ID
	private Date validateStartDate;		// 消息有效开始时间(非委托必填)
	private Date validateEndDate;		// 消息有效结束时间（非委托必填）
	private String productionProvince;		// 生产省份
	private String productionCity;		// 生产城市
	private String productionArea;		// 生产地区
	private String productionDetailArea;		// 生产详细地区
	private String orderUploadingId;		// 报价单历史表
	private String publishUser;		// 消息所属人员id
	private String checkRecordId;		// supply_demand_info_check审核记录id
	private String productionProvinceName;		// 生产省份名字
	private String productionCityName;		// 生产城市名字
	private String productionAreaName;		// 生产地区名字
	
	public SupplyDemandInfo() {
		super();
	}

	public SupplyDemandInfo(String id){
		super(id);
	}

	@Length(min=1, max=255, message="产品，gcategory表的name长度必须介于 1 和 255 之间")
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	@Length(min=0, max=255, message="产品别名长度必须介于 0 和 255 之间")
	public String getPgoodsAlias() {
		return pgoodsAlias;
	}

	public void setPgoodsAlias(String pgoodsAlias) {
		this.pgoodsAlias = pgoodsAlias;
	}
	
	@Length(min=0, max=64, message="产品id，商品类型表外键长度必须介于 0 和 64 之间")
	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	
	@Length(min=0, max=64, message="产品上级id长度必须介于 0 和 64 之间")
	public String getPgoodsId() {
		return pgoodsId;
	}

	public void setPgoodsId(String pgoodsId) {
		this.pgoodsId = pgoodsId;
	}
	
	@Length(min=1, max=255, message="产品上级name长度必须介于 1 和 255 之间")
	public String getPgoodsName() {
		return pgoodsName;
	}

	public void setPgoodsName(String pgoodsName) {
		this.pgoodsName = pgoodsName;
	}
	
	@Length(min=0, max=64, message="产品pids 代码长度必须介于 0 和 64 之间")
	public String getPids() {
		return pids;
	}

	public void setPids(String pids) {
		this.pids = pids;
	}
	
	@Length(min=0, max=10, message="价格单元id长度必须介于 0 和 10 之间")
	public String getPriceUnitId() {
		return priceUnitId;
	}

	public void setPriceUnitId(String priceUnitId) {
		this.priceUnitId = priceUnitId;
	}
	
	@Length(min=0, max=10, message="数量单元id长度必须介于 0 和 10 之间")
	public String getNumberUnitId() {
		return numberUnitId;
	}

	public void setNumberUnitId(String numberUnitId) {
		this.numberUnitId = numberUnitId;
	}
	
	@Length(min=0, max=64, message="价格单元长度必须介于 0 和 64 之间")
	public String getPriceUnitValue() {
		return priceUnitValue;
	}

	public void setPriceUnitValue(String priceUnitValue) {
		this.priceUnitValue = priceUnitValue;
	}
	
	@Length(min=0, max=64, message="数量单元长度必须介于 0 和 64 之间")
	public String getNumberUnitValue() {
		return numberUnitValue;
	}

	public void setNumberUnitValue(String numberUnitValue) {
		this.numberUnitValue = numberUnitValue;
	}	
	
	public Double getNumber() {
		return number;
	}

	public void setNumber(Double number) {
		this.number = number;
	}

	public Double getFromPerPrice() {
		return fromPerPrice;
	}

	public void setFromPerPrice(Double fromPerPrice) {
		this.fromPerPrice = fromPerPrice;
	}

	public Double getToPerPrice() {
		return toPerPrice;
	}

	public void setToPerPrice(Double toPerPrice) {
		this.toPerPrice = toPerPrice;
	}

	public String getSpecs() {
		return specs;
	}

	public void setSpecs(String specs) {
		this.specs = specs;
	}
	
	@Length(min=0, max=1, message="状态(默认0：待审核，1：已审核通过，-1：审核未通过，-2：删除,2:正在洽谈,3：等待打款，4：交易成功)长度必须介于 0 和 1 之间")
	public String getStatue() {
		return statue;
	}

	public void setStatue(String statue) {
		this.statue = statue;
	}
	
	public String getStatueIntro() {
		return statueIntro;
	}

	public void setStatueIntro(String statueIntro) {
		this.statueIntro = statueIntro;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Length(min=0, max=1, message="是否是委托（默认0：否，1：是）长度必须介于 0 和 1 之间")
	public String getIsEntrust() {
		return isEntrust;
	}

	public void setIsEntrust(String isEntrust) {
		this.isEntrust = isEntrust;
	}
	
	@Length(min=0, max=64, message="委托的供求信息id长度必须介于 0 和 64 之间")
	public String getEntrustSupplyDemandId() {
		return entrustSupplyDemandId;
	}

	public void setEntrustSupplyDemandId(String entrustSupplyDemandId) {
		this.entrustSupplyDemandId = entrustSupplyDemandId;
	}
	
	@Length(min=0, max=64, message="委托人公司id长度必须介于 0 和 64 之间")
	public String getEntrustOrganizationId() {
		return entrustOrganizationId;
	}

	public void setEntrustOrganizationId(String entrustOrganizationId) {
		this.entrustOrganizationId = entrustOrganizationId;
	}
	
	@Length(min=0, max=64, message="地址ID长度必须介于 0 和 64 之间")
	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getValidateStartDate() {
		return validateStartDate;
	}

	public void setValidateStartDate(Date validateStartDate) {
		this.validateStartDate = validateStartDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getValidateEndDate() {
		return validateEndDate;
	}

	public void setValidateEndDate(Date validateEndDate) {
		this.validateEndDate = validateEndDate;
	}
	
	@Length(min=0, max=64, message="生产省份长度必须介于 0 和 64 之间")
	public String getProductionProvince() {
		return productionProvince;
	}

	public void setProductionProvince(String productionProvince) {
		this.productionProvince = productionProvince;
	}
	
	@Length(min=0, max=64, message="生产城市长度必须介于 0 和 64 之间")
	public String getProductionCity() {
		return productionCity;
	}

	public void setProductionCity(String productionCity) {
		this.productionCity = productionCity;
	}
	
	@Length(min=0, max=64, message="生产地区长度必须介于 0 和 64 之间")
	public String getProductionArea() {
		return productionArea;
	}

	public void setProductionArea(String productionArea) {
		this.productionArea = productionArea;
	}
	
	@Length(min=0, max=500, message="生产详细地区长度必须介于 0 和 500 之间")
	public String getProductionDetailArea() {
		return productionDetailArea;
	}

	public void setProductionDetailArea(String productionDetailArea) {
		this.productionDetailArea = productionDetailArea;
	}
	
	@Length(min=0, max=64, message="报价单历史表长度必须介于 0 和 64 之间")
	public String getOrderUploadingId() {
		return orderUploadingId;
	}

	public void setOrderUploadingId(String orderUploadingId) {
		this.orderUploadingId = orderUploadingId;
	}
	
	@Length(min=1, max=64, message="消息所属人员id长度必须介于 1 和 64 之间")
	public String getPublishUser() {
		return publishUser;
	}

	public void setPublishUser(String publishUser) {
		this.publishUser = publishUser;
	}
	
	@Length(min=0, max=64, message="supply_demand_info_check审核记录id长度必须介于 0 和 64 之间")
	public String getCheckRecordId() {
		return checkRecordId;
	}

	public void setCheckRecordId(String checkRecordId) {
		this.checkRecordId = checkRecordId;
	}
	
	@Length(min=0, max=255, message="生产省份名字长度必须介于 0 和 255 之间")
	public String getProductionProvinceName() {
		return productionProvinceName;
	}

	public void setProductionProvinceName(String productionProvinceName) {
		this.productionProvinceName = productionProvinceName;
	}
	
	@Length(min=0, max=255, message="生产城市名字长度必须介于 0 和 255 之间")
	public String getProductionCityName() {
		return productionCityName;
	}

	public void setProductionCityName(String productionCityName) {
		this.productionCityName = productionCityName;
	}
	
	@Length(min=0, max=255, message="生产地区名字长度必须介于 0 和 255 之间")
	public String getProductionAreaName() {
		return productionAreaName;
	}

	public void setProductionAreaName(String productionAreaName) {
		this.productionAreaName = productionAreaName;
	}

	public Double getNonstandardNumber() {
		return nonstandardNumber;
	}

	public void setNonstandardNumber(Double nonstandardNumber) {
		this.nonstandardNumber = nonstandardNumber;
	}

	public Integer getNonstandardNumberUnitId() {
		return nonstandardNumberUnitId;
	}

	public void setNonstandardNumberUnitId(Integer nonstandardNumberUnitId) {
		this.nonstandardNumberUnitId = nonstandardNumberUnitId;
	}

	public String getNonstandardNumberUnitName() {
		return nonstandardNumberUnitName;
	}

	public void setNonstandardNumberUnitName(String nonstandardNumberUnitName) {
		this.nonstandardNumberUnitName = nonstandardNumberUnitName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
}