package com.wk.p3.greenmall.modules.info.entity;

import com.wk.p3.greenmall.common.persistence.DataEntity;
import com.wk.p3.greenmall.common.utils.DateUtils;
import com.wk.p3.greenmall.modules.sys.entity.User;
import com.wk.p3.greenmall.modules.user.entity.FrontUser;

import java.util.Date;
import java.util.List;

/**
 * Created by zhuyanqing on 2015/12/14.
 * 供求信息基本表
 */
public class Info extends DataEntity<Info>{

	private Integer isRecommend;//是否推荐

	private InfoMessage infoMessage;//纯信息的供求  审核前的信息
	private String infoMessageId;
	private InfoCheck infoCheck;//审核前的供求

	private String goodsName; 	//商品名称
	private String pgoodsAlias;	//商品别名
	private String goodsId;		//商品id（红富士）
	private String pgoodsId;	//父类id
	private String pgoodsName;	//父类名称（苹果）
	private String pids;		//所有父类id
	private Integer priceUnitId;//价格单元
	private Integer numberUnitId;//数量单元
	private String priceUnitValue;//价格单元
	private String numberUnitValue;//数量单元
	private Double number;			//数量
	private Double fromPerPrice;	//价格范围（起）
	private Double toPerPrice;		//价格范围（止）
	private String specs;			//手动填写的规格
	private Integer statue;			//状态(默认0：待审核，1：已审核通过，-1：审核未通过，-2：删除)
	private String statueIntro;		//审核说明
	private String type;			//供求类型（supply:供应，demand:求购）
	private Integer isEntrust;		//是否是委托（默认0：否，1：是）
	private String entrustSupplyDemandId;//委托的供求信息id  指的是本条信息所对应的信息（----------------------------比如：供应-对应-求购-----------------------------------------）
	private String entrustOrganizationId;//委托人公司id
	private String addressId;//收发货地址

	private Date validateStartDate;//消息有效开始时间(非委托必填)
	private Date validateEndDate;//消息有效结束时间（非委托必填）
	private String productionProvince;//生产省份
	private String productionCity;//生产城市
	private String productionArea;//生产地区
	 private String productionProvinceName;//生产省份
	private String productionCityName;//生产城市
	private String productionAreaName;//生产地区
	private String productionDetailArea;//生产详细地区
	private String orderUploadingId;//报价单历史表
/*	private User replacePublishUser;//代替发布人
	private User publishUser;//信息发布者
	private String publishUserId;//信息发布者
	private User replacePublishUser;//代替发布人*/
	private String publishUser;//信息发布者  PersonId
	private String remarks;
	private String checkedSpecs; //最終返回的規格    判斷方法：假如規格關聯表中有值那么查詢規格關聯表；否則查詢供求表中的specs字段
	private String checkedSpecsId; //最終返回的規格Id    关联表id

	private String provinceName;//收發貨省份名字
	private String cityName;//收發貨城市名字
	private String areaName;//收發貨地區名字

	public String getPublishUser() {
		return publishUser;
	}

	public void setPublishUser(String publishUser) {
		this.publishUser = publishUser;
	}

	private String provinceId;//收發貨省份名字
	private String cityId;//收發貨城市名字
	private String areaId;//收發貨地區名字
	private String detailArea;//詳細地區

	private String telephone; //聯系方式
	private String userName;//聯系人

	private String updateTime;//更新时间，用于显示当前时间和上次修改时间（updateDate）之间的时间差

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	private String organizationName;//采购供应组织
	private String organizationId;//采购供应组织id

	private String nonstandardNumberUnitName;
	private String nonstandardNumberUnitId;
	private String nonstandardNumberUnit;

	private Double nonstandardNumber;
	private String warehouse;
	private String postcode;

	public List<String> getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(List<String> imageUrls) {
		this.imageUrls = imageUrls;
	}

	private String checkRecordId;

	private Info entrustSupplyDemand;
	private List<String> imageUrls;

	public User getMiddleMan() {
		return middleMan;
	}

	public void setMiddleMan(User middleMan) {
		this.middleMan = middleMan;
	}

	private String ignoreIds;//不查询的ID

	private Boolean isFront;//是否是前端访问

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	private User middleMan;//当前跟进交易员

	private Integer source;//供求信息产生途径 1.PC前台 2.wx 3.PC后台  TODO 任何产生供求信息的地方都应该标明产生的途径

	//private Double min


	public Boolean getIsFront() {
		return isFront;
	}

	public void setIsFront(Boolean isFront) {
		this.isFront = isFront;
	}



	public String getInfoMessageId() {
		return infoMessageId;
	}

	public void setInfoMessageId(String infoMessageId) {
		this.infoMessageId = infoMessageId;
	}

	public String getIgnoreIds() {
		return ignoreIds;
	}

	public void setIgnoreIds(String ignoreIds) {
		this.ignoreIds = ignoreIds;
	}

	public InfoMessage getInfoMessage() {
		return infoMessage;
	}

	public void setInfoMessage(InfoMessage infoMessage) {
		this.infoMessage = infoMessage;
	}

	public Info getEntrustSupplyDemand() {
		return entrustSupplyDemand;
	}

	public void setEntrustSupplyDemand(Info entrustSupplyDemand) {
		this.entrustSupplyDemand = entrustSupplyDemand;
	}

	public String getCheckRecordId() {
		return checkRecordId;
	}

	public void setCheckRecordId(String checkRecordId) {
		this.checkRecordId = checkRecordId;
	}

	public String getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public Integer getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}

	public InfoCheck getInfoCheck() {
		return infoCheck;
	}

	public void setInfoCheck(InfoCheck infoCheck) {
		this.infoCheck = infoCheck;
	}

	public void setPriceUnitId(Integer priceUnitId) {
		this.priceUnitId = priceUnitId;
	}

	public void setNumberUnitId(Integer numberUnitId) {
		this.numberUnitId = numberUnitId;
	}

	public void setNumber(Double number) {
		this.number = number;
	}

	public void setFromPerPrice(Double fromPerPrice) {
		this.fromPerPrice = fromPerPrice;
	}

	public void setToPerPrice(Double toPerPrice) {
		this.toPerPrice = toPerPrice;
	}

	public void setValidateStartDate(Date validateStartDate) {
		this.validateStartDate = validateStartDate;
	}

	public void setValidateEndDate(Date validateEndDate) {
		this.validateEndDate = validateEndDate;
	}

	public String getCheckedSpecsId() {
		return checkedSpecsId;
	}

	public void setCheckedSpecsId(String checkedSpecsId) {
		this.checkedSpecsId = checkedSpecsId;
	}

	public Double getNonstandardNumber() {
		return nonstandardNumber;
	}

	public void setNonstandardNumber(Double nonstandardNumber) {
		this.nonstandardNumber = nonstandardNumber;
	}

	public String getNonstandardNumberUnit() {
		return nonstandardNumberUnit;
	}

	public void setNonstandardNumberUnit(String nonstandardNumberUnit) {
		this.nonstandardNumberUnit = nonstandardNumberUnit;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getOrganizationName() {
		return organizationName;
	}



	public String getNonstandardNumberUnitName() {
		return nonstandardNumberUnitName;
	}

	public void setNonstandardNumberUnitName(String nonstandardNumberUnitName) {
		this.nonstandardNumberUnitName = nonstandardNumberUnitName;
	}

	public String getNonstandardNumberUnitId() {
		return nonstandardNumberUnitId;
	}

	public void setNonstandardNumberUnitId(String nonstandardNumberUnitId) {
		this.nonstandardNumberUnitId = nonstandardNumberUnitId;
	}


	public String getProductionProvinceName() {
		return productionProvinceName;
	}

	public void setProductionProvinceName(String productionProvinceName) {
		this.productionProvinceName = productionProvinceName;
	}

	public String getProductionCityName() {
		return productionCityName;
	}

	public void setProductionCityName(String productionCityName) {
		this.productionCityName = productionCityName;
	}

	public String getProductionAreaName() {
		return productionAreaName;
	}

	public void setProductionAreaName(String productionAreaName) {
		this.productionAreaName = productionAreaName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDetailArea() {
		return detailArea;
	}

	public void setDetailArea(String detailArea) {
		this.detailArea = detailArea;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCheckedSpecs() {
		return checkedSpecs;
	}

	public void setCheckedSpecs(String checkedSpecs) {
		this.checkedSpecs = checkedSpecs;
	}

	@Override
	public String getRemarks() {
		return remarks;
	}

	@Override
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Info() {
	}
	public Info(String id){
		this.id=id;
	}

	public Info(String id, String goodsName, String pgoodsAlias, String goodsId, String pgoodsId, String pgoodsName,
			String pids, Integer priceUnitId, Integer numberUnitId, String priceUnitValue, String numberUnitValue,
			Double number, Double fromPerPrice, Double toPerPrice, String specs, Integer statue, String statueIntro,
			String type, Integer isEntrust, String entrustSupplyDemandId, String entrustOrganizationId,
			String addressId, Date validateStartDate, Date validateEndDate,
			String productionProvince, String productionCity, String productionArea, String productionDetailArea,
			String orderUploadingId, FrontUser publishUser) {
		this.goodsName = goodsName;
		this.pgoodsAlias = pgoodsAlias;
		this.goodsId = goodsId;
		this.pgoodsId = pgoodsId;
		this.pgoodsName = pgoodsName;
		this.pids = pids;
		this.priceUnitId = priceUnitId;
		this.numberUnitId = numberUnitId;
		this.priceUnitValue = priceUnitValue;
		this.numberUnitValue = numberUnitValue;
		this.number = number;
		this.fromPerPrice = fromPerPrice;
		this.toPerPrice = toPerPrice;
		this.specs = specs;
		this.statue = statue;
		this.statueIntro = statueIntro;
		this.type = type;
		this.isEntrust = isEntrust;
		this.addressId = addressId;
		this.entrustSupplyDemandId = entrustSupplyDemandId;
		this.entrustOrganizationId = entrustOrganizationId;
		this.validateStartDate = validateStartDate;
		this.validateEndDate = validateEndDate;
		this.productionProvince = productionProvince;
		this.productionCity = productionCity;
		this.productionArea = productionArea;
		this.productionDetailArea = productionDetailArea;
		this.orderUploadingId = orderUploadingId;
	}








	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getPgoodsAlias() {
		return pgoodsAlias;
	}

	public void setPgoodsAlias(String pgoodsAlias) {
		this.pgoodsAlias = pgoodsAlias;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getPgoodsId() {
		return pgoodsId;
	}

	public void setPgoodsId(String pgoodsId) {
		this.pgoodsId = pgoodsId;
	}

	public String getPgoodsName() {
		return pgoodsName;
	}

	public void setPgoodsName(String pgoodsName) {
		this.pgoodsName = pgoodsName;
	}

	public String getPids() {
		return pids;
	}

	public void setPids(String pids) {
		this.pids = pids;
	}

	public Integer getPriceUnitId() {
		return priceUnitId;
	}

	public void setPriceUnitId(String priceUnitId) {
		if(priceUnitId!=null && priceUnitId!="") {
			this.priceUnitId = Integer.parseInt(priceUnitId);
		}
	}

	public String getEntrustSupplyDemandId() {
		return entrustSupplyDemandId;
	}

	public void setEntrustSupplyDemandId(String entrustSupplyDemandId) {
		this.entrustSupplyDemandId = entrustSupplyDemandId;
	}

	public String getEntrustOrganizationId() {
		return entrustOrganizationId;
	}

	public void setEntrustOrganizationId(String entrustOrganizationId) {
		this.entrustOrganizationId = entrustOrganizationId;
	}

	public Integer getNumberUnitId() {
		return numberUnitId;
	}

	public void setNumberUnitId(String numberUnitId) {
		if(numberUnitId!=null && numberUnitId!="") {
			this.numberUnitId = Integer.parseInt(numberUnitId);
		}
	}

	public String getPriceUnitValue() {
		return priceUnitValue;
	}

	public void setPriceUnitValue(String priceUnitValue) {
		this.priceUnitValue = priceUnitValue;
	}

	public String getNumberUnitValue() {
		return numberUnitValue;
	}

	public void setNumberUnitValue(String numberUnitValue) {
		this.numberUnitValue = numberUnitValue;
	}

	public Double getNumber() {
		return number;
	}

	public void setNumber(String number) {
		if(number!=null && !"".equals(number)) {
			this.number = Double.parseDouble(number);
		}
//		info.setNumber(Double.parseDouble(number));
	}

	public Double getFromPerPrice() {
		return fromPerPrice;
	}

	public void setFromPerPrice(String fromPerPrice) {
		if(fromPerPrice!=null && fromPerPrice!="") {
			this.fromPerPrice = Double.parseDouble(fromPerPrice);
		}
	}

	public Double getToPerPrice() {
		return toPerPrice;
	}

	public void setToPerPrice(String toPerPrice) {
		if(toPerPrice!=null && toPerPrice!="") {
			this.toPerPrice = Double.parseDouble(toPerPrice);
		}
	}

	public String getSpecs() {
		return specs;
	}

	public void setSpecs(String specs) {
		this.specs = specs;
	}

	public Integer getStatue() {
		return statue;
	}

	public void setStatue(Integer statue) {
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

	public Integer getIsEntrust() {
		return isEntrust;
	}

	public void setIsEntrust(Integer isEntrust) {
		this.isEntrust = isEntrust;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}



	public Date getValidateStartDate() {
		return validateStartDate;
	}

	public void setValidateStartDate(String validateStartDate) {
		if(validateStartDate!=null && validateStartDate!="") {
			this.validateStartDate = DateUtils.fmtDate(validateStartDate, "yyyy-MM-dd");
		}
	}

	public Date getValidateEndDate() {
		return validateEndDate;
	}

	public void setValidateEndDate(String validateEndDate) {
		if(validateEndDate!=null && validateEndDate!="") {
			this.validateEndDate = DateUtils.fmtDate(validateEndDate, "yyyy-MM-dd");
		}
	}

	public String getProductionProvince() {
		return productionProvince;
	}

	public void setProductionProvince(String productionProvince) {
		this.productionProvince = productionProvince;
	}

	public String getProductionCity() {
		return productionCity;
	}

	public void setProductionCity(String productionCity) {
		this.productionCity = productionCity;
	}

	public String getProductionArea() {
		return productionArea;
	}

	public void setProductionArea(String productionArea) {
		this.productionArea = productionArea;
	}

	public String getProductionDetailArea() {
		return productionDetailArea;
	}

	public void setProductionDetailArea(String productionDetailArea) {
		this.productionDetailArea = productionDetailArea;
	}

	public String getOrderUploadingId() {
		return orderUploadingId;
	}

	public void setOrderUploadingId(String orderUploadingId) {
		this.orderUploadingId = orderUploadingId;
	}




}