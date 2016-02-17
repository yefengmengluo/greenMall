package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;


/**
 * 登陆凭证实体类
 **/
@ApiModel(description = "登陆凭证实体类")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringMVCServerCodegen", date = "2016-02-04T16:26:04.369+08:00")
public class LoginToken  {
  
  private String id = null;
  private String loginName = null;
  private String password = null;
  private String userType = null;

  
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
  @JsonProperty("loginName")
  public String getLoginName() {
    return loginName;
  }
  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }

  
  /**
   **/
  @ApiModelProperty(value = "")
  @JsonProperty("password")
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }

  
  /**
   * 用户登陆类型
   **/
  @ApiModelProperty(value = "用户登陆类型")
  @JsonProperty("userType")
  public String getUserType() {
    return userType;
  }
  public void setUserType(String userType) {
    this.userType = userType;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LoginToken loginToken = (LoginToken) o;
    return Objects.equals(id, loginToken.id) &&
        Objects.equals(loginName, loginToken.loginName) &&
        Objects.equals(password, loginToken.password) &&
        Objects.equals(userType, loginToken.userType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, loginName, password, userType);
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class LoginToken {\n");
    
    sb.append("  id: ").append(id).append("\n");
    sb.append("  loginName: ").append(loginName).append("\n");
    sb.append("  password: ").append(password).append("\n");
    sb.append("  userType: ").append(userType).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
