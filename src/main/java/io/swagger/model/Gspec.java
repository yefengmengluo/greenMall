package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.Gvalue;
import java.util.*;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;


@ApiModel(description = "")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringMVCServerCodegen", date = "2016-02-04T16:26:04.369+08:00")
public class Gspec  {
  
  private Integer id = null;
  private String name = null;
  private List<Gvalue> gvalue = new ArrayList<Gvalue>();

  
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
  @JsonProperty("gvalue")
  public List<Gvalue> getGvalue() {
    return gvalue;
  }
  public void setGvalue(List<Gvalue> gvalue) {
    this.gvalue = gvalue;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Gspec gspec = (Gspec) o;
    return Objects.equals(id, gspec.id) &&
        Objects.equals(name, gspec.name) &&
        Objects.equals(gvalue, gspec.gvalue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, gvalue);
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Gspec {\n");
    
    sb.append("  id: ").append(id).append("\n");
    sb.append("  name: ").append(name).append("\n");
    sb.append("  gvalue: ").append(gvalue).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
