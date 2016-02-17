package com.wk.p3.greenmall.modules.user.dao;

import java.util.List;
import java.util.Map;

import com.wk.p3.greenmall.common.persistence.annotation.MyBatisDao;
import com.wk.p3.greenmall.modules.user.entity.AdminUser;


@MyBatisDao
public interface AdminUserDao {

	List<AdminUser> userList(AdminUser adminUser);

    void approveUser(Map<String, String> map);
}
