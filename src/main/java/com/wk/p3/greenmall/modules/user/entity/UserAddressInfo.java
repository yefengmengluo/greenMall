package com.wk.p3.greenmall.modules.user.entity;

import com.wk.p3.greenmall.common.persistence.DataEntity;

/**
 * Created by liujiabao on 2015/12/29 0029.
 */
public class UserAddressInfo extends DataEntity<UserAddressInfo>{
        private String provinceId;
        private String cityId;
        private String areaId;
        private String warehouse;
        private String detailArea;
        private String userId;//personId
        private String userName;
        private String telephone;
        private Integer isDefalt;
        private String postcode;
        private String type;
        private String provinceName;
        private String cityName;
        private String areaName;

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

        public String getWarehouse() {
            return warehouse;
        }

        public void setWarehouse(String warehouse) {
            this.warehouse = warehouse;
        }

        public String getDetailArea() {
            return detailArea;
        }

        public void setDetailArea(String detailArea) {
            this.detailArea = detailArea;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public Integer getIsDefalt() {
            return isDefalt;
        }

        public void setIsDefalt(Integer isDefalt) {
            this.isDefalt = isDefalt;
        }

        public String getPostcode() {
            return postcode;
        }

        public void setPostcode(String postcode) {
            this.postcode = postcode;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
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
}
