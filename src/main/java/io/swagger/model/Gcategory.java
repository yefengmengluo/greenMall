package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.Gcategory;
import io.swagger.model.Gspec;
import java.util.*;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;


/**
 * 分类，树形结构
 **/
@ApiModel(description = "分类，树形结构")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringMVCServerCodegen", date = "2016-02-04T16:26:04.369+08:00")
public class Gcategory  {
  
  private Integer id = null;
  private String name = null;
  private String code = null;
  private List<Gspec> gspec = new ArrayList<Gspec>();
  private List<Gcategory> child = new ArrayList<Gcategory>();

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("id")
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }

  
  /**
   * 规格分类名称
   **/
  @ApiModelProperty(value = "规格分类名称")
  @JsonProperty("name")
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  
  /**
   * 规格分类值
   **/
  @ApiModelProperty(value = "规格分类值")
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
  @JsonProperty("gspec")
  public List<Gspec> getGspec() {
    return gspec;
  }
  public void setGspec(List<Gspec> gspec) {
    this.gspec = gspec;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("child")
  public List<Gcategory> getChild() {
    return child;
  }
  public void setChild(List<Gcategory> child) {
    this.child = child;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Gcategory gcategory = (Gcategory) o;
    return Objects.equals(id, gcategory.id) &&
        Objects.equals(name, gcategory.name) &&
        Objects.equals(code, gcategory.code) &&
        Objects.equals(gspec, gcategory.gspec) &&
        Objects.equals(child, gcategory.child);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, code, gspec, child);
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Gcategory {\n");
    
    sb.append("  id: ").append(id).append("\n");
    sb.append("  name: ").append(name).append("\n");
    sb.append("  code: ").append(code).append("\n");
    sb.append("  gspec: ").append(gspec).append("\n");
    sb.append("  child: ").append(child).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
