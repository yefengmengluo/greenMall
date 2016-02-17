package com.wk.p3.greenmall.modules.user.service.impl;

import com.wk.p3.greenmall.modules.sys.dao.UserDao;
import com.wk.p3.greenmall.modules.sys.entity.User;
import com.wk.p3.greenmall.modules.user.UserService;
import com.wk.p3.greenmall.modules.user.dao.UserRelationDao;
import com.wk.p3.greenmall.modules.user.entity.FrontUser;
import com.wk.p3.greenmall.modules.user.entity.UserRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wk.p3.greenmall.common.persistence.Page;
import com.wk.p3.greenmall.modules.user.dao.AdminUserDao;
import com.wk.p3.greenmall.modules.user.entity.AdminUser;
import com.wk.p3.greenmall.modules.user.service.AdminUserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhaomeng
 * @description 机构接口实现类
 * 2015年12月29日
 */
@Service
@Transactional(readOnly = true)
public class AdminUserServiceImpl implements AdminUserService{

    @Autowired
    private AdminUserDao adminUserDao;

    @Autowired
    private UserRelationDao userRelationDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @Override
    public Page<AdminUser> userList(Page<AdminUser> page, AdminUser adminUser) {
        adminUser.setPage(page);
        page.setList(adminUserDao.userList(adminUser));
        return page;
    }

    @Override
    @Transactional(readOnly = false)
    public String approveUser(String statue, String userId, User user) {
        try {
            UserRelation userRelation = new UserRelation();
            if (statue.equals("2")) {
                String roleId = userDao.finRoleIdByRoleName("交易员");
                List<String> bussinessIds = userDao.finUserByRoleId(roleId);
                Integer result = 0;
                String theUser = null;
                for (int i = 0; i < bussinessIds.size(); i++) {
                    if (i == 0) {
                        result = userRelationDao.findCountUserByTheId(bussinessIds.get(i));
                        theUser = bussinessIds.get(i);
                    } else {
                        Integer result2 = userRelationDao.findCountUserByTheId(bussinessIds.get(i));
                        if (result > result2) {
                            result = result2;
                            theUser = bussinessIds.get(i);
                        }
                    }
                }
                FrontUser userByUserId = userService.getUserByUserId(userId);
                userRelationDao.deleteRelationByOtherUser(userByUserId.getPersonId());
                userRelation.preInsert();
                userRelation.setCreateBy(user);
                userRelation.setUpdateBy(user);
                userRelation.setTheUser(theUser);
                userRelation.setRelationType("bussiness");
                userRelation.setOtherUser(userByUserId.getPersonId());
                userRelationDao.insert(userRelation);
            } else {
                userRelationDao.deleteRelationByOtherUser(userId);
            }
            Map<String, String> map = new HashMap();
            map.put("statue", statue);
            map.put("userId", userId);
            adminUserDao.approveUser(map);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }
}
