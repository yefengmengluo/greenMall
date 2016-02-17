package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.Area;
import java.util.*;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;


@ApiModel(description = "")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringMVCServerCodegen", date = "2016-02-04T16:26:04.369+08:00")
public class Area  {
  
  private String id = null;
  private String name = null;
  private String code = null;
  private String type = null;
  private List<Area> childArea = new ArrayList<Area>();

  
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
  @JsonProperty("name")
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("code")
  public String getCode() {
    return code;
  }
  public void setCode(String code) {
    this.code = code;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("type")
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }

  
  /**
   * 子区域，比如国家的子区域是各个省
   **/
  @ApiModelProperty(value = "子区域，比如国家的子区域是各个省")
  @JsonProperty("childArea")
  public List<Area> getChildArea() {
    return childArea;
  }
  public void setChildArea(List<Area> childArea) {
    this.childArea = childArea;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Area area = (Area) o;
    return Objects.equals(id, area.id) &&
        Objects.equals(name, area.name) &&
        Objects.equals(code, area.code) &&
        Objects.equals(type, area.type) &&
        Objects.equals(childArea, area.childArea);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, code, type, childArea);
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Area {\n");
    
    sb.append("  id: ").append(id).append("\n");
    sb.append("  name: ").append(name).append("\n");
    sb.append("  code: ").append(code).append("\n");
    sb.append("  type: ").append(type).append("\n");
    sb.append("  childArea: ").append(childArea).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
