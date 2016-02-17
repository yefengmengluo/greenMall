package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.Gcategory;
import io.swagger.model.Info;
import io.swagger.model.Unit;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;


/**
 * 人工确认后的供求详情，包括精确的消息分类，规格等
 **/
@ApiModel(description = "人工确认后的供求详情，包括精确的消息分类，规格等")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringMVCServerCodegen", date = "2016-02-04T16:26:04.369+08:00")
public class InfoDetail  {
  
  private String id = null;
  private Info info = null;
  private Integer n = null;
  private Unit unit = null;
  private Gcategory gcategory = null;

  
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
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("Info")
  public Info getInfo() {
    return info;
  }
  public void setInfo(Info info) {
    this.info = info;
  }

  
  /**
   * Unit单位对应的数量
   **/
  @ApiModelProperty(value = "Unit单位对应的数量")
  @JsonProperty("n")
  public Integer getN() {
    return n;
  }
  public void setN(Integer n) {
    this.n = n;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("Unit")
  public Unit getUnit() {
    return unit;
  }
  public void setUnit(Unit unit) {
    this.unit = unit;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("gcategory")
  public Gcategory getGcategory() {
    return gcategory;
  }
  public void setGcategory(Gcategory gcategory) {
    this.gcategory = gcategory;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InfoDetail infoDetail = (InfoDetail) o;
    return Objects.equals(id, infoDetail.id) &&
        Objects.equals(info, infoDetail.info) &&
        Objects.equals(n, infoDetail.n) &&
        Objects.equals(unit, infoDetail.unit) &&
        Objects.equals(gcategory, infoDetail.gcategory);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, info, n, unit, gcategory);
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class InfoDetail {\n");
    
    sb.append("  id: ").append(id).append("\n");
    sb.append("  info: ").append(info).append("\n");
    sb.append("  n: ").append(n).append("\n");
    sb.append("  unit: ").append(unit).append("\n");
    sb.append("  gcategory: ").append(gcategory).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
