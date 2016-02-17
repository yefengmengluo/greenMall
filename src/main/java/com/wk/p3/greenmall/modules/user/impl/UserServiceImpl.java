package com.wk.p3.greenmall.modules.user.impl;

import com.wk.p3.greenmall.modules.security.Person;
import com.wk.p3.greenmall.modules.security.SecurityService;
import com.wk.p3.greenmall.modules.sys.dao.UserDao;
import com.wk.p3.greenmall.modules.sys.entity.User;
import com.wk.p3.greenmall.modules.user.UserService;
import com.wk.p3.greenmall.modules.user.dao.FrontUserDao;
import com.wk.p3.greenmall.modules.user.dao.UserRelationDao;
import com.wk.p3.greenmall.modules.user.entity.FrontUser;
import com.wk.p3.greenmall.modules.user.entity.UserRelation;
import com.wk.p3.greenmall.modules.user.service.UserRelationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cc on 15-12-8.
 */

@Service
public class UserServiceImpl implements UserService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRelationService userRelationService;

    @Autowired
    private UserRelationDao userRelationDao;

    @Autowired
    private FrontUserDao frontUserDao;

    @Autowired
    private UserDao userDao;



    @Override
    public List<String> relationUser(String relation, String id) {
        List<String> userIds = new ArrayList<String>();
        UserRelation userRelation = new UserRelation();
        userRelation.setOtherUser(id);
        userRelation.setRelationType(relation);
        for(UserRelation ur:userRelationService.findList(userRelation)){
            userIds.add(ur.getTheUser()) ;
        }
        return userIds;
    }

    @Override
    public FrontUser getUserByPersonId(String personId) {
        FrontUser frontUser = new FrontUser();
        frontUser.setPersonId(personId);
        FrontUser user = frontUserDao.getUserByCondition(frontUser);
        return user;
    }

    @Override
    public void registerFrontUser(FrontUser user) {
        frontUserDao.insert(user);
    }

    @Override
    public FrontUser getUserByUserId(String userId) {
        FrontUser frontUser = frontUserDao.get(userId);
        return frontUser;
    }

    @Autowired
    private SecurityService securityService;

    @Override
    public FrontUser getFrontUser() {
        Person person = securityService.currentPerson();
        if (person != null) {
            FrontUser currentUser = this.getUserByPersonId(person.getId());
            return currentUser;
        }
        return null;
    }

    @Override
    public Integer getUserByMobile(String mobile) {
        return frontUserDao.getUserByMobile(mobile);
    }

    @Override
    public FrontUser getFrontUserByOrganizationId(String organizationId) {
        FrontUser frontUser = new FrontUser();
        frontUser.setOrganizationId(organizationId);
        return frontUserDao.getUserByCondition(frontUser);
    }

    @Override
    public User findBussinessUserByFrontUser(String id) {
        String theUserId = userRelationDao.findBussinessUserByOtherId(id);
        User user = userDao.get(theUserId);
        return user;
    }

    @Override
    public Integer getCountUser(FrontUser frontUser) {
        return frontUserDao.getCountUser(frontUser);
    }
}
