package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.Gcategory;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;


/**
 * 主营产品
 **/
@ApiModel(description = "主营产品")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringMVCServerCodegen", date = "2016-02-04T16:26:04.369+08:00")
public class Product  {
  
  private String id = null;
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
   * 主营产品的分类
   **/
  @ApiModelProperty(value = "主营产品的分类")
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
    Product product = (Product) o;
    return Objects.equals(id, product.id) &&
        Objects.equals(gcategory, product.gcategory);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, gcategory);
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Product {\n");
    
    sb.append("  id: ").append(id).append("\n");
    sb.append("  gcategory: ").append(gcategory).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
