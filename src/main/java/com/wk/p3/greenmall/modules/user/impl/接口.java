package com.wk.p3.greenmall.modules.user.impl;

import com.wk.p3.greenmall.modules.sys.entity.User;
import com.wk.p3.greenmall.modules.user.entity.FrontUser;

/**
 * Created by zhaomeng on 2016/2/3.
 */
public interface 接口 {
    /**
     * 获取当前登录用户
     * @return
     */
    public FrontUser getFrontUser();
    /**
     * 根据前台用户userId获取前台用户
     * @param userId
     * @return
     */
    FrontUser getUserByUserId(String userId);
    /**
     * 通过组织机构id获取前台用户
     *
     * @param organizationId
     * @return
     */
    FrontUser getFrontUserByOrganizationId(String organizationId);
    /**
     * 通过前台用户id获取交易员信息
     *
     * @param frontUserId
     * @return
     */
    User findBussinessUserByFrontUser(String frontUserId);
}
