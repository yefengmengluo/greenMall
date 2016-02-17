package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.Area;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;


/**
 * 详细地址信息
 **/
@ApiModel(description = "详细地址信息")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringMVCServerCodegen", date = "2016-02-04T16:26:04.369+08:00")
public class Address  {
  
  private String id = null;
  private String address = null;
  private Area province = null;
  private Area city = null;
  private Area area = null;

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("id")
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  
  /**
   * 地址详情字符串
   **/
  @ApiModelProperty(value = "地址详情字符串")
  @JsonProperty("address")
  public String getAddress() {
    return address;
  }
  public void setAddress(String address) {
    this.address = address;
  }

  
  /**
   * 省
   **/
  @ApiModelProperty(value = "省")
  @JsonProperty("province")
  public Area getProvince() {
    return province;
  }
  public void setProvince(Area province) {
    this.province = province;
  }

  
  /**
   * 市
   **/
  @ApiModelProperty(value = "市")
  @JsonProperty("city")
  public Area getCity() {
    return city;
  }
  public void setCity(Area city) {
    this.city = city;
  }

  
  /**
   * 区
   **/
  @ApiModelProperty(value = "区")
  @JsonProperty("area")
  public Area getArea() {
    return area;
  }
  public void setArea(Area area) {
    this.area = area;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Address address = (Address) o;
    return Objects.equals(id, address.id) &&
        Objects.equals(address, address.address) &&
        Objects.equals(province, address.province) &&
        Objects.equals(city, address.city) &&
        Objects.equals(area, address.area);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, address, province, city, area);
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Address {\n");
    
    sb.append("  id: ").append(id).append("\n");
    sb.append("  address: ").append(address).append("\n");
    sb.append("  province: ").append(province).append("\n");
    sb.append("  city: ").append(city).append("\n");
    sb.append("  area: ").append(area).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
