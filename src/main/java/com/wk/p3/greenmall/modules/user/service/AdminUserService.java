package com.wk.p3.greenmall.modules.user.service;

import com.wk.p3.greenmall.common.persistence.Page;
import com.wk.p3.greenmall.modules.sys.entity.User;
import com.wk.p3.greenmall.modules.user.entity.AdminUser;

/**
 * @author zhaomeng
 * @description 后台用户管理
 * 2016年1月8日
 */
public interface AdminUserService {
	/**
	 * 后台根据用户类型查询用户列表
     * @param page
     * @param adminUser
     * @return
     */
    public Page<AdminUser> userList(Page<AdminUser> page, AdminUser adminUser);

    /**
     * 用户审核
     *
     * @param statue
     * @param userId
     * @param user
     * @return
     */
    String approveUser(String statue, String userId, User user);
}
