package com.wk.p3.greenmall.modules.security;


import com.wk.p3.greenmall.modules.security.entity.SecurityRole;

import java.util.Set;

/**
 * Created by cc on 16-1-18.
 */
public interface Person {

    /**
     * 用户名id
     * @return
     */
    public String getId();

    /**
     * 用户名名称
     * @return
     */
    //public String getUserName();

    /**
     * 获得角色
     * @return
     */
    public Set<Role> getRoleSet();


}
