package com.wk.p3.greenmall.modules.security;

import java.util.List;

/**
 * 角色对象
 * Created by cc on 16-1-18.
 */
public interface Role {


    public String getId();

    /**
     *
     * @return
     */
    public String getEnname();

    /**
     * 角色对象中获得权限
     * @return
     */
    public List<String> getPermissions();


}
