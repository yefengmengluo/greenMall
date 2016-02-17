package com.wk.p3.greenmall.modules.info.entity;

import com.wk.p3.greenmall.common.persistence.DataEntity;
import com.wk.p3.greenmall.modules.sys.entity.User;
import io.swagger.model.*;

/**
 * Created by zhuyanqing on 2016/1/29.
 */
public class BrowseLog extends DataEntity<BrowseLog>{

    private String id;
    private String ip;
    private String mapLongitude;//地图坐标
    private String mapLatitude;//地图坐标

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    private String accessType; //访问途径种类  COMPUTER("Computer"),MOBILE("Mobile"),TABLET("Tablet"),
    private Integer os; // Android 1、iOS 2、ipad 3 操作系统类型 (移动端 TODO 扩充pc端)
    private String browser;//浏览器
    private Integer targetType;//访问目标的类型 供求信息 1、
    private String targetId;//访问目标ID
    private String userId;//访问的用户ID
    private Integer userType;//用户类型 1、前台用户2、后台用户

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public Integer getTargetType() {
        return targetType;
    }

    public Integer getOs() {
        return os;
    }

    public void setOs(Integer os) {
        this.os = os;
    }

    public void setTargetType(Integer targetType) {
        this.targetType = targetType;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }


    public String getMapLongitude() {
        return mapLongitude;
    }

    public void setMapLongitude(String mapLongitude) {
        this.mapLongitude = mapLongitude;
    }

    public String getMapLatitude() {
        return mapLatitude;
    }

    public void setMapLatitude(String mapLatitude) {
        this.mapLatitude = mapLatitude;
    }


}
