package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.Address;
import io.swagger.model.Organization;
import io.swagger.model.Product;
import java.util.*;

import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;


@ApiModel(description = "")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringMVCServerCodegen", date = "2016-02-04T16:26:04.369+08:00")
public class User  {
  
  private String id = null;
  private String username = null;
  public enum RoleTypeEnum {
     supply,  demand,  agent, 
  };
  private RoleTypeEnum roleType = null;
  private Address address = null;
  private Integer userStatus = null;
  private List<Product> products = new ArrayList<Product>();
  private Organization organization = null;

  
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
  @JsonProperty("username")
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }

  
  /**
   * 角色类型:supply为供应商,demand为求购商,经济人为agent
   **/
  @ApiModelProperty(value = "角色类型:supply为供应商,demand为求购商,经济人为agent")
  @JsonProperty("roleType")
  public RoleTypeEnum getRoleType() {
    return roleType;
  }
  public void setRoleType(RoleTypeEnum roleType) {
    this.roleType = roleType;
  }

  
  /**
   * 详细地址
   **/
  @ApiModelProperty(value = "详细地址")
  @JsonProperty("address")
  public Address getAddress() {
    return address;
  }
  public void setAddress(Address address) {
    this.address = address;
  }

  
  /**
   * 用户状态，是否审核通过等
   **/
  @ApiModelProperty(value = "用户状态，是否审核通过等")
  @JsonProperty("userStatus")
  public Integer getUserStatus() {
    return userStatus;
  }
  public void setUserStatus(Integer userStatus) {
    this.userStatus = userStatus;
  }

  
  /**
   * 主营产品
   **/
  @ApiModelProperty(value = "主营产品")
  @JsonProperty("products")
  public List<Product> getProducts() {
    return products;
  }
  public void setProducts(List<Product> products) {
    this.products = products;
  }

  
  /**
   * 组织机构
   **/
  @ApiModelProperty(value = "组织机构")
  @JsonProperty("organization")
  public Organization getOrganization() {
    return organization;
  }
  public void setOrganization(Organization organization) {
    this.organization = organization;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(id, user.id) &&
        Objects.equals(username, user.username) &&
        Objects.equals(roleType, user.roleType) &&
        Objects.equals(address, user.address) &&
        Objects.equals(userStatus, user.userStatus) &&
        Objects.equals(products, user.products) &&
        Objects.equals(organization, user.organization);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, roleType, address, userStatus, products, organization);
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class User {\n");
    
    sb.append("  id: ").append(id).append("\n");
    sb.append("  username: ").append(username).append("\n");
    sb.append("  roleType: ").append(roleType).append("\n");
    sb.append("  address: ").append(address).append("\n");
    sb.append("  userStatus: ").append(userStatus).append("\n");
    sb.append("  products: ").append(products).append("\n");
    sb.append("  organization: ").append(organization).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
