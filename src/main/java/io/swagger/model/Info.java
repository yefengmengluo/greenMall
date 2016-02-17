package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.InfoDetail;
import io.swagger.model.User;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;


/**
 * 发布的供求信息
 **/
@ApiModel(description = "发布的供求信息")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringMVCServerCodegen", date = "2016-02-04T16:26:04.369+08:00")
public class Info  {
  
  private String id = null;
  public enum TypeEnum {
     supply,  demand, 
  };
  private TypeEnum type = null;
  private String value = null;
  private User user = null;
  private User agent = null;
  private InfoDetail infoDetail = null;

  
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
   * 角色类型:supply为供应商,demand为求购商,经济人为agent
   **/
  @ApiModelProperty(value = "角色类型:supply为供应商,demand为求购商,经济人为agent")
  @JsonProperty("type")
  public TypeEnum getType() {
    return type;
  }
  public void setType(TypeEnum type) {
    this.type = type;
  }

  
  /**
   * 发布的一条供求信息
   **/
  @ApiModelProperty(value = "发布的一条供求信息")
  @JsonProperty("value")
  public String getValue() {
    return value;
  }
  public void setValue(String value) {
    this.value = value;
  }

  
  /**
   * 发布人
   **/
  @ApiModelProperty(value = "发布人")
  @JsonProperty("user")
  public User getUser() {
    return user;
  }
  public void setUser(User user) {
    this.user = user;
  }

  
  /**
   * 中间人，可以为空
   **/
  @ApiModelProperty(value = "中间人，可以为空")
  @JsonProperty("agent")
  public User getAgent() {
    return agent;
  }
  public void setAgent(User agent) {
    this.agent = agent;
  }

  
  /**
   * 审核后的详细商机
   **/
  @ApiModelProperty(value = "审核后的详细商机")
  @JsonProperty("infoDetail")
  public InfoDetail getInfoDetail() {
    return infoDetail;
  }
  public void setInfoDetail(InfoDetail infoDetail) {
    this.infoDetail = infoDetail;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Info info = (Info) o;
    return Objects.equals(id, info.id) &&
        Objects.equals(type, info.type) &&
        Objects.equals(value, info.value) &&
        Objects.equals(user, info.user) &&
        Objects.equals(agent, info.agent) &&
        Objects.equals(infoDetail, info.infoDetail);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, value, user, agent, infoDetail);
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Info {\n");
    
    sb.append("  id: ").append(id).append("\n");
    sb.append("  type: ").append(type).append("\n");
    sb.append("  value: ").append(value).append("\n");
    sb.append("  user: ").append(user).append("\n");
    sb.append("  agent: ").append(agent).append("\n");
    sb.append("  infoDetail: ").append(infoDetail).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
